package org.codefx.courses.java8.monad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exercise_01 {

	/*
	 * NOTE: This test class has a `@Disabled` annotation that you need to remove.
	 *
	 * TASK: `Optional` is an abstraction over present/absent; `Stream` is an
	 *       abstraction over multitudes. In this task you have to create an
	 *       abstraction over initialization.
	 *
	 *       It is common to defer expensive initialization until a resource is
	 *       actually needed. This is usually implemented ad-hoc with a nullable
	 *       field and a getter that checks whether the field is `null` and,
	 *       if so, initializes it before returning it. This is cumbersome and,
	 *       particularly if concurrency plays a role, error-prone.
	 *
	 *       In this task, fill out the type `Lazy` (in the main source tree) by
	 *       adding two methods:
	 *
	 *        * one that creates a `Lazy` instance with a recipe to initialize
	 *          the resource, but without doing so yet
	 *        * one that gets the resource
	 *
	 *       (Have a look at `Optional` and `Stream` for fitting method names.)
	 *
	 *       The logical flow is straightforward:
	 *
	 *        * the first call to get the resource initializes it
	 *        * further calls do not lead to repeated initialization
	 *
	 *       (I mentioned concurrency, but don't spend too much time on that.
	 *       If the respective test fails, disable it with `@Disabled`).
	 *
	 *       To test your implementation, implement the two wrapper methods
	 *       `create` and `getResource` below. Then you can run the tests.
	 */

	// API WRAPPER

	private static <T> Lazy<T> create(Supplier<T> generator) {
		return Lazy.of(generator);
	}

	private static <T> T getResource(Lazy<T> lazy) {
		return lazy.get();
	}

	// TESTS

	private static final String SUPPLIER_RESULT = "result";
	private final LongAdder supplierExecutions = new LongAdder();

	@BeforeEach
	void resetExecutionCounter() {
		supplierExecutions.reset();
	}

	@Test
	void createWithNullSupplier() {
		assertThrows(
				NullPointerException.class,
				() -> create(null),
				"A null supplier should not be legal");
	}

	@Test
	void createWithNullReturningSupplier() {
		Lazy<String> lazy = create(() -> null);

		assertThrows(
				NullPointerException.class,
				() -> getResource(lazy),
				"A supplier that creates/returns null should not be legal");
	}

	@Test
	void creationDoesNotExecuteSupplier() {
		Supplier<String> supplier = createSupplier();

		create(supplier);

		assertThat(supplierExecutions)
				.describedAs("The supplier should not be executed during creation")
				.extracting(LongAdder::sum)
				.isEqualTo(0L);
	}

	@Test
	void getReturnsResult() {
		Lazy<String> lazy = create(createSupplier());

		String result = getResource(lazy);

		assertThat(result)
				.describedAs("Getting a resource should return the supplier's result")
				.isEqualTo(SUPPLIER_RESULT);
	}

	@Test
	void getExecutesSupplierOnce() {
		Lazy<String> lazy = create(createSupplier());

		getResource(lazy);

		assertThat(supplierExecutions)
				.describedAs("The first call to get the resource should execute the supplier once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	@Test
	void repeatedGetExecutesSupplierOnce() {
		Lazy<String> lazy = create(createSupplier());

		for (int i = 0; i < 8; i++)
			getResource(lazy);

		assertThat(supplierExecutions)
				.describedAs("Repeatedly getting the resource should not execute the supplier more than once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	@Test
	void multiThreadedGetExecutesSupplierOnce() throws Exception {
		Lazy<String> lazy = create(createSupplier());
		ExecutorService executor = Executors.newFixedThreadPool(8);
		List<Future<String>> futures = new ArrayList<>();

		for (int i = 0; i < 16; i++) {
			Future<String> getResult = executor.submit(() -> getResource(lazy));
			futures.add(getResult);
		}

		for (Future<String> future : futures)
			future.get();

		assertThat(supplierExecutions)
				.describedAs("Concurrently getting the resource should not execute the supplier more than once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	private Supplier<String> createSupplier() {
		return () -> {
			supplierExecutions.increment();
			return SUPPLIER_RESULT;
		};
	}

}

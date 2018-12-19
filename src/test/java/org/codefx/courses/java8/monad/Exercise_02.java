package org.codefx.courses.java8.monad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exercise_02 {

	/*
	 * NOTE: This test class has a `@Disabled` annotation that you need to remove.
	 *
	 * TASK: Assume a class exposes a `Lazy` resource and another class wants to
	 *       expose its own resource that builds on the first one, i.e. there's a
	 *       function that takes the first resource as input and computes the second.
	 *
	 *       Create a method on `Lazy` that returns a new `Lazy` whose resource is the
	 *       result of applying the function to the first `Lazy`'s resource. This
	 *       should of course be lazy, i.e. the function should only be executed when
	 *       needed and only once.
	 *
	 *       (Have a look at `Optional` and `Stream` for a fitting method name.)
	 *
	 *       To test your implementation, implement the two wrapper methods
	 *       `create` and `apply` below. Then you can run the tests.
	 */

	// API WRAPPER

	private static <T> Lazy<T> create(Supplier<T> generator) {
		return Lazy.of(generator);
	}

	private static <T> T getResource(Lazy<T> lazy) {
		return lazy.get();
	}

	private static <T, U> Lazy<U> apply(Lazy<T> lazy, Function<T, U> function) {
		return lazy.map(function);
	}

	// TESTS

	private static final String SUPPLIER_RESULT = "result";
	private static final Integer FUNCTION_RESULT = SUPPLIER_RESULT.length();
	private final LongAdder functionExecutions = new LongAdder();

	private Lazy<String> lazy;

	@BeforeEach
	void resetLazyAndExecutionCounter() {
		lazy = create(createSupplier());
		functionExecutions.reset();
	}

	@Test
	void applyNullFunction() {
		assertThrows(
				NullPointerException.class,
				() -> apply(lazy, null),
				"A null function should not be legal");
	}

	@Test
	void applyNullReturningFunction() {
		Lazy<Integer> mappedLazy = apply(lazy, __ -> null);

		assertThrows(
				NullPointerException.class,
				() -> getResource(mappedLazy),
				"A function that returns null should not be legal");
	}

	@Test
	void applyDoesNotExecuteFunction() {
		apply(lazy, createFunction());

		assertThat(functionExecutions)
				.describedAs("The function should not be executed during creation")
				.extracting(LongAdder::sum)
				.isEqualTo(0L);
	}

	@Test
	void getReturnsMappedResult() {
		Lazy<Integer> mappedLazy = apply(lazy, createFunction());

		Integer result = getResource(mappedLazy);

		assertThat(result)
				.describedAs("Lazy::get should return the function's result")
				.isEqualTo(FUNCTION_RESULT);
	}

	@Test
	void getExecutesFunctionOnce() {
		Lazy<Integer> mappedLazy = apply(lazy, createFunction());

		getResource(mappedLazy);

		assertThat(functionExecutions)
				.describedAs("The first call to Lazy::get should execute the function once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	@Test
	void repeatedGetExecutesFunctionOnce() {
		Lazy<Integer> mappedLazy = apply(lazy, createFunction());

		for (int i = 0; i < 8; i++)
			getResource(mappedLazy);

		assertThat(functionExecutions)
				.describedAs("Repeatedly calling Lazy::get should not execute the function more than once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	@Test
	void multiThreadedGetExecutesFunctionOnce() throws Exception {
		Lazy<Integer> mappedLazy = apply(lazy, createFunction());
		ExecutorService executor = Executors.newFixedThreadPool(8);
		List<Future<Integer>> futures = new ArrayList<>();

		for (int i = 0; i < 16; i++) {
			Future<Integer> getResult = executor.submit(() -> getResource(mappedLazy));
			futures.add(getResult);
		}

		for (Future<Integer> future : futures)
			future.get();

		assertThat(functionExecutions)
				.describedAs("Concurrently calling Lazy::get should not execute the function more than once")
				.extracting(LongAdder::sum)
				.isEqualTo(1L);
	}

	@ParameterizedTest
	@ValueSource(strings = { "test", "", "long string" })
	void rightIdentity(String resource) {
		// of(v).apply(v -> v)
		Lazy<String> applyIdentity = apply(create(() -> resource), Function.identity());

		assertThat(getResource(applyIdentity)).isEqualTo(resource);
	}

	@ParameterizedTest
	@CsvSource({ "test, 4", "'', 0", "long string, 11" })
	void leftIdentity(String resource, Integer resourceLength) {
		Function<String, Integer> length = string -> string.length();
		// of(v).map(f)
		Lazy<Integer> createThenApply = apply(create(() -> resource), length);
		// of(f.apply(v))
		Lazy<Integer> applyThenCreate = create(() -> length.apply(resource));

		assertThat(getResource(createThenApply)).isEqualTo(resourceLength);
		assertThat(getResource(applyThenCreate)).isEqualTo(resourceLength);
	}

	@ParameterizedTest
	@CsvSource({ "test, 4", "'', 0", "long string, 11" })
	void associativity(String resource, Integer resourceLength) {
		Function<String, String> duplicate = string -> string + string;
		Function<String, Integer> length = string -> string.length();

		// of(v).map(f).map(g)
		Lazy<Integer> mappedIndividually = apply(apply(create(() -> resource), duplicate), length);
		// of(v).map(f.andThen(g))
		Lazy<Integer> mappedComposed = apply(create(() -> resource), duplicate.andThen(length));

		assertThat(getResource(mappedIndividually)).isEqualTo(resourceLength * 2);
		assertThat(getResource(mappedComposed)).isEqualTo(resourceLength * 2);
	}

	private static Supplier<String> createSupplier() {
		return () -> SUPPLIER_RESULT;
	}

	private Function<String, Integer> createFunction() {
		return string -> {
			functionExecutions.increment();
			return string.length();
		};
	}

}

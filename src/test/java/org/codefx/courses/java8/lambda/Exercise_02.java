package org.codefx.courses.java8.lambda;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

	/*
	 * TASK: Replace the three anonymous classes assigned to
	 *       `evenFilter`, `twoIncrementer`, `sysOutPrinter`
	 *       with lambda expressions
	 */

	private final IntFilter evenFilter = integer -> integer % 2 == 0;

	private interface IntFilter {

		boolean pass(int integer);

	}

	private final IntIncrementer twoIncrementer = integer -> integer + 2;

	private interface IntIncrementer {

		int increment(int integer);

	}

	private final IntPrinter sysOutPrinter = integer -> System.out.println(integer);

	private interface IntPrinter {

		void print(int integer);

	}

	@Test
	void evenFilter() {
		assertThat(evenFilter.pass(0)).isTrue();
		assertThat(evenFilter.pass(1)).isFalse();
		assertThat(evenFilter.pass(2)).isTrue();
	}

	@Test
	void twoIncrementer() {
		assertThat(twoIncrementer.increment(0)).isEqualTo(2);
		assertThat(twoIncrementer.increment(5)).isEqualTo(7);
		assertThat(twoIncrementer.increment(10)).isEqualTo(12);
	}

}

package org.codefx.courses.java8.lambda;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_03 {

	/*
	 * TASK: Uncomment the tests and use lambdas to create interface implementations that make them pass.
	 *       Experiment with the different ways to define parameters (with/without types, with/without parenthesis)
	 *       and bodies (with/without curly braces).
	 */

	/** Computes the length (or rather, size) of the list. */
	private ListCruncher<String> listLength = list -> list.size();

	/** Computes the sum of the lengths of the strings in the list. */
	private ListCruncher<String> stringLengths = list -> list.stream().mapToInt(String::length).sum();

	private interface ListCruncher<T> {

		int crunch(List<T> list);

	}

	/** Adds all ints in the list or returns the default value if the list is empty. */
	private ListAccumulator adder = (ints, def) -> ints.stream().reduce((i, j) -> i + j).orElse(def);

	/** Multiplies all ints in the list or returns the default value if the list is empty. */
	private ListAccumulator multiplier = (ints, def) -> ints.stream().reduce((i, j) -> i * j).orElse(def);

	private interface ListAccumulator {

		int accumulate(List<Integer> ints, int defaultValue);

	}

	@Test
	void listLengthCruncher() {
		assertThat(listLength.crunch(asList())).isEqualTo(0);
		assertThat(listLength.crunch(asList(""))).isEqualTo(1);
		assertThat(listLength.crunch(asList("A", "BC"))).isEqualTo(2);
		assertThat(listLength.crunch(asList("A", "BC", "D, EF"))).isEqualTo(3);
	}

	@Test
	void stringLengthsCruncher() {
		assertThat(stringLengths.crunch(asList())).isEqualTo(0);
		assertThat(stringLengths.crunch(asList(""))).isEqualTo(0);
		assertThat(stringLengths.crunch(asList("A"))).isEqualTo(1);
		assertThat(stringLengths.crunch(asList("A", "BC"))).isEqualTo(3);
		assertThat(stringLengths.crunch(asList("A", "BC", "D, EF"))).isEqualTo(8);
	}

	@Test
	void adder(){
		assertThat(adder.accumulate(asList(), 5)).isEqualTo(5);
		assertThat(adder.accumulate(asList(0), 5)).isEqualTo(0);
		assertThat(adder.accumulate(asList(0), 5)).isEqualTo(0);
	}

	@Test
	void multiplier(){
		assertThat(multiplier.accumulate(asList(), 5)).isEqualTo(5);
		assertThat(multiplier.accumulate(asList(0), 5)).isEqualTo(0);
		assertThat(multiplier.accumulate(asList(1, 5, 3), 5)).isEqualTo(15);
	}

}

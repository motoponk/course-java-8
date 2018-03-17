package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

	/*
	 * TASK: Uncomment the tests for LAMBDA API and implement methods on
	 *       `LambdaList` that make them pass.
	 *
	 * TASK: Look at the tests for LAMBDA USE and refactor methods in
	 *       `LambdaListLoops` to use the new API.
	 */

	private final LambdaList<String> list = LambdaList.create("c", "o", "d", "e", "fx");
	private final LambdaListLoops loops = new LambdaListLoops();

	// LAMBDA API

	@Test
	void forEachElement() {
		List<String> copy = new ArrayList<>();

		list.forEachElement(copy::add);

		assertThat(copy).containsExactlyElementsOf(list);
	}

	@Test
	void filter() {
		LambdaList<String> filtered = list.filter(s -> s.length() > 1);

		assertThat(filtered).containsOnly("fx");
	}

	@Test
	void map() {
		LambdaList<String> filtered = list.map(s -> s + " ");

		assertThat(filtered).containsOnly("c ", "o ", "d ", "e ", "fx ");
	}

	@Test
	void reduce() {
		String reduced = list.reduce("", (s1, s2) -> s1 + s2);

		assertThat(reduced).isEqualTo("codefx");
	}

	@Test
	void collect() {
		HashSet<String> set = list.collect(HashSet::new);

		assertThat(set).containsOnly("c", "o", "d", "e", "fx");
	}

	// LAMBDA USE

	@Test
	void duplicateStrings() {
		List<String> duplicated = loops.duplicateStrings(list);
		assertThat(duplicated).containsOnly("cc", "oo", "dd", "ee", "fxfx");
	}

	@Test
	void concatenateStrings() {
		String concatenated = loops.concatenateStrings(list);
		assertThat(concatenated).isEqualTo("codefx");
	}

	@Test
	void getLongDescriptions() {
		LambdaList<Todo> todos = LambdaList
				.create(new Todo("Short desc"), new Todo("This is a long description"));

		List<String> longDescriptions = loops.getLongDescriptions(todos, 15);

		assertThat(longDescriptions).containsExactly("This is a long description");
	}

	@Test
	void noOfImportantTodos() {
		LambdaList<Todo> todos = LambdaList.create(
				new Todo("", Importance.A_LITTLE),
				new Todo("", Importance.VERY),
				new Todo("", Importance.VERY),
				new Todo("", Importance.SOMEWHAT));

		int noOfImportantTodos = loops.noOfImportantTodos(todos, Importance.VERY);

		assertThat(noOfImportantTodos).isEqualTo(2);
	}

	@Test
	void charsToIntSum() {
		int sum = loops.charsToIntSum(list);
		assertThat(sum).isEqualTo(63);
	}

}

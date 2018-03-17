package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

	/*
	 * TASK: Create a copy of `LambdaListLoops` and call it `LambdaListStreams`.
	 *       Then uncomment the tests below and make sure they are passing.
	 *       After these preparations, refactor the code in `LambdaListStreams`
	 *       to use Java 8 streams instead of the custom made `LambdaList`
	 *       functionality.
	 *
	 * TIP: Changing the argument types in `LambdaListStreams` from `LambdaList`
	 *      to `List` makes it easier to avoid the custom made API.
	 */

	private final LambdaListStreams streams = new LambdaListStreams();

	@Test
	void duplicateStrings() {
		LambdaList<String> list = LambdaList.create("c", "o", "d", "e", "fx");
		List<String> duplicated = streams.duplicateStrings(list);
		assertThat(duplicated).containsOnly("cc", "oo", "dd", "ee", "fxfx");
	}

	@Test
	void concatenateStrings() {
		LambdaList<String> list = LambdaList.create("c", "o", "d", "e", "fx");
		String concatenated = streams.concatenateStrings(list);
		assertThat(concatenated).isEqualTo("codefx");
	}

	@Test
	void getLongDescriptions() {
		LambdaList<Todo> todos = LambdaList
				.create(new Todo("Short desc"), new Todo("This is a long description"));

		List<String> longDescriptions = streams.getLongDescriptions(todos, 15);

		assertThat(longDescriptions).containsExactly("This is a long description");
	}

	@Test
	void noOfImportantTodos() {
		LambdaList<Todo> todos = LambdaList.create(
				new Todo("", Importance.A_LITTLE),
				new Todo("", Importance.VERY),
				new Todo("", Importance.VERY),
				new Todo("", Importance.SOMEWHAT));

		int noOfImportantTodos = streams.noOfImportantTodos(todos, Importance.VERY);

		assertThat(noOfImportantTodos).isEqualTo(2);
	}

	@Test
	void charsToIntSum() {
		LambdaList<String> list = LambdaList.create("c", "o", "d", "e", "fx");
		int sum = streams.charsToIntSum(list);
		assertThat(sum).isEqualTo(63);
	}

}

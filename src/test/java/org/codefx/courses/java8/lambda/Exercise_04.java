package org.codefx.courses.java8.lambda;

import org.codefx.courses.java8.lambda.Todo.Importance;
import org.codefx.courses.java8.lambda.TodoList.TodoFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.ZonedDateTime.now;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

	/*
	 * TASK: Transform the anonymous classes in `TodoList` to lambda expressions
	 *       and look out for (non-) capturing variables.
	 *
	 * ASSUMPTION: In Exercise 01 you created an interface `TodoFilter` that you
	 *             can now make package visible (at least). You also created a
	 *             method `todosThatPass(TodoFilter)` that applies the filter to
	 *             the `List<Todo>`.
	 *
	 * TASK: Add a field `nextDeadline` to `TodoList` and its constructor
	 *       (keep single-parameter constructor around to avoid compile errors).
	 *       Uncomment the tests and write methods that pass them.
	 *       Again, look out for (non-) capturing variables.
	 *
	 * OBSERVE: Try to assign new (dummy) values to the involved variables
	 *          and see how the compiler behaves.
	 */

	private final Todo taxes = new Todo("Taxes", Importance.A_LITTLE, now().plusMonths(3));
	private final Todo laundry = new Todo("Laundry", Importance.SOMEWHAT, now().plusDays(1));
	private final Todo groceries = new Todo("Groceries", Importance.VERY, now().plusDays(2));

	private final TodoList list = new TodoList(asList(laundry, groceries, taxes), now().plusDays(3));

	@Test
	void dueBeforeNextDeadline() {
		TodoFilter filter = list.dueBeforeNextDeadline();
		List<Todo> filtered = list.todosThatPass(filter);

		assertThat(filtered).containsOnly(laundry, groceries);
	}


	@Test
	void dueBefore() {
		TodoFilter filter = list.dueBefore(now().plusDays(1).plusHours(12));
		List<Todo> filtered = list.todosThatPass(filter);

		assertThat(filtered).containsOnly(laundry);
	}

	@Test
	void dueInNextThreeDays() {
		// three days counting from time of method call, not TodoFilter invocation!
		TodoFilter filter = list.dueInNextThreeDays();
		List<Todo> filtered = list.todosThatPass(filter);

		assertThat(filtered).containsOnly(laundry, groceries);
	}

}

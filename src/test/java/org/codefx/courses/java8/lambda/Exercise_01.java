package org.codefx.courses.java8.lambda;

import org.codefx.courses.java8.lambda.Todo.Importance;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.ZonedDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

	/*
	 * TASK: Uncomment `todosMoreImportantThan` and implement the corresponding method to make it pass
	 *
	 * TASK: Uncomment `todosDueAfter` and implement the corresponding method to make it pass
	 *
	 * OBSERVE: There's a lot of duplication
	 *
	 * TASK: Refactor the code in `TodoList`, to remove duplication and reduce necessity to create a new method for each possible filter
	 *
	 * HINT: Fuhdwh dq lqwhuidfh zlwk d vlqjoh phwkrg wkdw dffhsw d `Wrgr` dqg uhwxuqv d errohdq (Caesar 3)
	 *
	 * HINT: Jcywfhy ymj qttunsl htij fsi ufxx ns fs nsyjwkfhj nruqjrjsyfynts - ymj qttu zxjx ymfy yt knqyjw ymj qnxy (Caesar 5)
	 *
	 * TASK: Make sure the improved code is extensible by applying one or two new and weirdly specific filters.
	 */

	private final Todo taxes = new Todo("Taxes", Importance.A_LITTLE, now().plusMonths(3));
	private final Todo laundry = new Todo("Laundry", Importance.SOMEWHAT, now().plusDays(1));
	private final Todo groceries = new Todo("Groceries", Importance.VERY, now().plusDays(2));

	private final TodoList list = new TodoList(Arrays.asList(laundry, groceries, taxes));

	@Test
	void todosWithImportance() {
		List<Todo> filtered = list.todosWithImportance(Importance.SOMEWHAT);
		assertThat(filtered).containsOnly(laundry);
	}

	@Test
	void todosMoreImportantThan() {
		List<Todo> filtered = list.todosMoreImportantThan(Importance.A_LITTLE);
		assertThat(filtered).containsOnly(laundry, groceries);
	}

	@Test
	void todosDueBy() {
		List<Todo> filtered = list.todosDueBy(now().plusDays(3));
		assertThat(filtered).containsOnly(laundry, groceries);
	}

	@Test
	void todosDueAfter() {
		List<Todo> filtered = list.todosDueAfter(now().plusDays(3));
		assertThat(filtered).containsOnly(taxes);
	}

}

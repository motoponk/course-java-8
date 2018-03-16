package org.codefx.courses.java8.lambda;

import org.codefx.courses.java8.lambda.Todo.Importance;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZonedDateTime.now;

public class TodoList {

	private final List<Todo> todos;
	private final ZonedDateTime nextDeadline;

	public TodoList(List<Todo> todos, ZonedDateTime nextDeadline) {
		this.todos = todos;
		this.nextDeadline = nextDeadline;
	}

	public TodoList(List<Todo> todos) {
		this(todos, now().plusDays(3));
	}

	public List<Todo> todosWithImportance(Importance importance) {
		return todosThatPass(todo -> todo.importance() == importance);
	}

	public List<Todo> todosMoreImportantThan(Importance importance) {
		return todosThatPass(todo -> todo.importance().ordinal() > importance.ordinal());
	}

	public List<Todo> todosDueBy(ZonedDateTime time) {
		return todosThatPass(todo -> todo.due().isBefore(time));
	}

	public List<Todo> todosDueAfter(ZonedDateTime time) {
		return todosThatPass(todo -> todo.due().isAfter(time));
	}

	List<Todo> todosThatPass(TodoFilter filter) {
		List<Todo> filtered = new ArrayList<>();
		for (Todo todo : todos)
			if(filter.passes(todo))
				filtered.add(todo);
		return filtered;
	}

	public TodoFilter dueBeforeNextDeadline() {
		return todo -> todo.due().isBefore(nextDeadline);
	}

	public TodoFilter dueBefore(ZonedDateTime date) {
		return todo -> todo.due().isBefore(date);
	}

	public TodoFilter dueInNextThreeDays() {
		ZonedDateTime inThreeDays = now().plusDays(3);
		return todo -> todo.due().isBefore(inThreeDays);
	}

	interface TodoFilter {

		boolean passes(Todo todo);

	}

}

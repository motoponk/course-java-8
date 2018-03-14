package org.codefx.courses.java8.lambda;

import org.codefx.courses.java8.lambda.Todo.Importance;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

	private final List<Todo> todos;

	public TodoList(List<Todo> todos) {
		this.todos = todos;
	}

	public List<Todo> todosWithImportance(Importance importance) {
		return todosThatPass(new TodoFilter() {
			@Override
			public boolean passes(Todo todo) {
				return todo.importance() == importance;
			}
		});
	}

	public List<Todo> todosMoreImportantThan(Importance importance) {
		return todosThatPass(new TodoFilter() {
			@Override
			public boolean passes(Todo todo) {
				return todo.importance().ordinal() > importance.ordinal();
			}
		});
	}

	public List<Todo> todosDueBy(ZonedDateTime time) {
		return todosThatPass(new TodoFilter() {
			@Override
			public boolean passes(Todo todo) {
				return todo.due().isBefore(time);
			}
		});
	}

	public List<Todo> todosDueAfter(ZonedDateTime time) {
		return todosThatPass(new TodoFilter() {
			@Override
			public boolean passes(Todo todo) {
				return todo.due().isAfter(time);
			}
		});
	}

	private List<Todo> todosThatPass(TodoFilter filter) {
		List<Todo> filtered = new ArrayList<>();
		for (Todo todo : todos)
			if(filter.passes(todo))
				filtered.add(todo);
		return filtered;
	}

	interface TodoFilter {

		boolean passes(Todo todo);

	}

}

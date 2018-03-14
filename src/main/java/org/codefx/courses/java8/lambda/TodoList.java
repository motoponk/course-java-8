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
		List<Todo> filtered = new ArrayList<>();
		for (Todo todo : todos)
			if(todo.importance() == importance)
				filtered.add(todo);
		return filtered;
	}

	public List<Todo> todosDueBy(ZonedDateTime time) {
		List<Todo> filtered = new ArrayList<>();
		for (Todo todo : todos)
			if(todo.due().isBefore(time))
				filtered.add(todo);
		return filtered;
	}

}

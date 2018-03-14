package org.codefx.courses.java8.lambda;

import java.time.ZonedDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Todo {

	private final String description;
	private final Importance importance;
	private final ZonedDateTime due;

	public Todo(String description, Importance importance, ZonedDateTime due) {
		this.description = requireNonNull(description);
		this.importance = requireNonNull(importance);
		this.due = requireNonNull(due);
	}

	public Todo(String description, Importance importance) {
		this(description, importance, ZonedDateTime.now().plusDays(3));
	}

	public Todo(String description, ZonedDateTime due) {
		this(description, Importance.SOMEWHAT, due);
	}

	public Todo(String description) {
		this(description, Importance.SOMEWHAT);
	}

	public String description() {
		return description;
	}

	public Importance importance() {
		return importance;
	}

	public ZonedDateTime due() {
		return due;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Todo todo = (Todo) o;
		return Objects.equals(description, todo.description) &&
				importance == todo.importance &&
				Objects.equals(due, todo.due);
	}

	@Override
	public int hashCode() {

		return Objects.hash(description, importance, due);
	}

	public enum Importance {

		NOT, A_LITTLE, SOMEWHAT, VERY, UTMOST

	}

}

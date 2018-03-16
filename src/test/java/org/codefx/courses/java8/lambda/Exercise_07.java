package org.codefx.courses.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Exercise_07 {

	/*
	 * TASK: Take a look at the various lambda expressions below and
	 *       change them to method references.
	 *
	 * TASK: Recreate similar examples with the `Todo` instead of the `Type` class.
	 */

	private Type type = Type.create("");

	// static method
	private Function<String, Type> create = Type::create;
	// constructor
	private Function<String, Type> constructor = Type::new;
	// on existing object
	private Supplier<String> getFieldFromType = type::getField;
	// on first parameter
	private Function<Type, String> getField = Type::getField;
	private BiFunction<Type, Integer, String> appendedField = Type::appendedField;

	private static class Type {

		private final String field;

		Type(String field) {
			this.field = field;
		}

		static Type create(String field) {
			return new Type(field);
		}

		String getField() {
			return field;
		}

		String appendedField(Integer parameter) {
			return field + parameter;
		}

	}


	private Todo todo = new Todo("");

	// static method
	// `Todo` has no static method
	// constructor
	private Function<String, Todo> constructorTodo = Todo::new;
	// on existing object
	private Supplier<String> getDescriptionFromTodo = todo::description;
	// on first parameter
	private Function<Todo, String> getDescription = Todo::description;

}

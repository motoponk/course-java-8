package org.codefx.courses.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Exercise_07 {

	/*
	 * TASK: Take a look at the various lambda expressions below and add
	 *       a change them to method references.
	 *
	 * TASK: Recreate similar examples with the `Todo` instead of the `Type` class.
	 */

	private Type type = Type.create("");

	// static method
	private Function<String, Type> create = field -> Type.create(field);
	// constructor
	private Function<String, Type> constructor = field -> new Type(field);
	// on existing object
	private Supplier<String> getFieldFromType = () -> type.getField();
	// on first parameter
	private Function<Type, String> getField = _type -> _type.getField();
	private BiFunction<Type, Integer, String> appendedField = (_type, parameter) -> _type.appendedField(parameter);

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

}

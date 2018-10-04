package org.codefx.courses.java8.lambda;

import org.codefx.courses.java8.lambda.Todo.Importance;

import java.time.ZonedDateTime;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Exercise_05 {

	/*
	 * TASK: Visit https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html
	 *       and look for the most closely fitting functional interfaces to call the methods below.
	 *       Use that to declare a field of that type for each method and use a lambda to populate it
	 *       (see `printString` for an example).
	 */

	private Consumer<String> printString = s -> print(s);

	private void print(String s) {
		System.out.println(s);
	}

	private void print(Integer i) {
		System.out.println(i);
	}

	private void print(int i) {
		System.out.println(i);
	}

	private void print(Float f) {
		System.out.println(f);
	}

	private void print(float f) {
		System.out.println(f);
	}

	private int answer() {
		return 42;
	}

	private String extractDescription(Todo todo) {
		return todo.description();
	}

	private String duplicate(String s) {
		return s + s;
	}

	private double plus(double d1, double d2) {
		return d1 + d2;
	}

	private Todo withoutDescription() {
		return new Todo("");
	}

	private Todo withDescripton(String description) {
		return new Todo(description);
	}

	private Todo withDescriptonAndDueDate(String description, ZonedDateTime due) {
		return new Todo(description, due);
	}

	private Todo withDescriptonImportanceAndDueDate(String description, Importance importance, ZonedDateTime due) {
		return new Todo(description, importance, due);
	}

	private boolean isDescriptionEmpty(Todo todo) {
		return todo.description().isEmpty();
	}

	private boolean isDescriptionTooLong(Todo todo, int maxLength) {
		return todo.description().length() > maxLength;
	}

}

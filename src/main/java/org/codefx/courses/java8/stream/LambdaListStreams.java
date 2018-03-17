package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class LambdaListStreams {

	public List<String> duplicateStrings(List<String> list) {
		return list.stream()
				.map(s -> s +  s)
				.collect(toList());
	}

	public String concatenateStrings(List<String> list) {
		return list.stream()
				.reduce("", (s1, s2) -> s1 +  s2);
	}

	public List<String> getLongDescriptions(List<Todo> list, int minLength) {
		return list.stream()
				.map(Todo::description)
				.filter(description -> description.length() >= minLength)
				.collect(toList());
	}

	public int noOfImportantTodos(List<Todo> list, Importance importance) {
		return (int) list.stream()
				.filter(todo -> todo.importance() == importance)
				.count();
	}

	public int charsToIntSum(List<String> list) {
		return list.stream()
				.filter(s -> s.length() == 1)
				.map(s -> s.charAt(0))
				.map(Character::getNumericValue)
				.reduce(0, (i1, i2) -> i1 + i2);
	}

}

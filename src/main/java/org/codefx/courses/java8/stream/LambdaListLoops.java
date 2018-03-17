package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;

import java.util.List;

public class LambdaListLoops {

	public List<String> duplicateStrings(LambdaList<String> list) {
		return list.map(s -> s +  s);
	}

	public String concatenateStrings(LambdaList<String> list) {
		return list.reduce("", (s1, s2) -> s1 +  s2);
	}

	public List<String> getLongDescriptions(LambdaList<Todo> list, int minLength) {
		return list.map(Todo::description)
				.filter(description -> description.length() >= minLength);
	}

	public int noOfImportantTodos(LambdaList<Todo> list, Importance importance) {
		return list.filter(todo -> todo.importance() == importance)
				.size();
	}

	public int charsToIntSum(LambdaList<String> list) {
		return list.filter(s -> s.length() == 1)
				.map(s -> s.charAt(0))
				.map(Character::getNumericValue)
				.reduce(0, (i1, i2) -> i1 + i2);
	}

}

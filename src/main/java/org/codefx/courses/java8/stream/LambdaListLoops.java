package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;

import java.util.ArrayList;
import java.util.List;

public class LambdaListLoops {

	public List<String> duplicateStrings(LambdaList<String> list) {
		List<String> duplicated = new ArrayList<>();
		for (String s : list)
			duplicated.add(s + s);
		return duplicated;
	}

	public String concatenateStrings(LambdaList<String> list) {
		String concatenated = "";
		for (String s : list)
			concatenated += s;
		return concatenated;
	}

	public List<String> getLongDescriptions(LambdaList<Todo> list, int minLength) {
		List<String> longDescriptions = new ArrayList<>();
		for (Todo todo : list) {
			String description = todo.description();
			if (description.length() > minLength)
				longDescriptions.add(description);
		}
		return longDescriptions;
	}

	public int noOfImportantTodos(LambdaList<Todo> list, Importance importance) {
		int noOfImportantTodos = 0;
		for (Todo todo : list)
			if (todo.importance() == importance)
				noOfImportantTodos++;
		return noOfImportantTodos;
	}

	public int charsToIntSum(LambdaList<String> list) {
		int sum = 0;
		for (String s : list)
			if (s.length() == 1) {
				char c = s.charAt(0);
				sum += Character.getNumericValue(c);
			}
		return sum;
	}

}

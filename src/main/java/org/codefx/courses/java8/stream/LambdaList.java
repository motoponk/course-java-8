package org.codefx.courses.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LambdaList<E> extends ArrayList<E> {

	public static void main(String[] args) {
		LambdaList<String> strings = LambdaList.create("c", "o", "d", "e", "fx");

		for (String string : strings) {
			System.out.println(string);
		}

	}

	// OPERATIONS

	// for each element
	// filter
	// map
	// reduce
	// collect

	// CONSTRUCTION

	private LambdaList() {
		super();
	}

	private LambdaList(Collection<E> elements) {
		super(elements);
	}

	public static <E> LambdaList<E> create(E... elements) {
		return new LambdaList<>(Arrays.asList(elements));
	}

}

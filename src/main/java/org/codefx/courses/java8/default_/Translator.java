package org.codefx.courses.java8.default_;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public interface Translator {

	String translate(String sentence);

	default List<String> translate(List<String> text) {
		return text.stream()
				.map(this::translate)
				.collect(toList());
	}

	default boolean verify(String sentence) {
		return false;
	}

	default boolean verify(List<String> text) {
		return false;
	}

}

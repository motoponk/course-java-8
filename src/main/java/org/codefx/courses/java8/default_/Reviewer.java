package org.codefx.courses.java8.default_;

import java.util.List;

// to fix the last test without reimplementing `verify(List<String)`:
// public interface Reviewer extends Translator {
public interface Reviewer {

	boolean verify(String sentence);

	default boolean verify(List<String> text) {
		return text.stream().allMatch(this::verify);
	}

}

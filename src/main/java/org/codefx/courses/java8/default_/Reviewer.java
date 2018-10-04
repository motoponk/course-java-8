package org.codefx.courses.java8.default_;

import java.util.List;

public interface Reviewer {

	boolean verify(String sentence);

	default boolean verify(List<String> text) {
		return text.stream().allMatch(this::verify);
	}

}

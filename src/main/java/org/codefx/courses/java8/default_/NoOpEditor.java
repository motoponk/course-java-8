package org.codefx.courses.java8.default_;

import java.util.List;

// to fix the last test without reimplementing `verify(List<String)`:
// public class NoOpEditor implements Reviewer {
public class NoOpEditor implements Translator, Reviewer {

	@Override
	public boolean verify(String sentence) {
		return true;
	}

	@Override
	public boolean verify(List<String> text) {
		return Reviewer.super.verify(text);
	}

	@Override
	public String translate(String sentence) {
		return sentence;
	}

}

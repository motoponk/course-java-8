package org.codefx.courses.java8.stream;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamSources {

	final Path textFile = new File(StreamSources.class.getClassLoader().getResource("file.txt").getPath()).toPath();
	final String[] visibilities = { "public", "protected", "private" };

	public Stream<Character> letters() {
		throw new RuntimeException("Not yet implemented.");
	}

	public Stream<String> numbers(int maxInclusive) {
		throw new RuntimeException("Not yet implemented.");
	}

	public Stream<String> visibilities() {
		throw new RuntimeException("Not yet implemented.");
	}

	public Stream<String> textFile() {
		throw new RuntimeException("Not yet implemented.");
	}

}

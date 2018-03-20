package org.codefx.courses.java8.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamSources {

	final Path textFile = new File(StreamSources.class.getClassLoader().getResource("file.txt").getPath()).toPath();
	final String[] visibilities = { "public", "protected", "private" };

	public Stream<Character> letters() {
		return Stream.of('c', 'o', 'd', 'e', 'f', 'x');
	}

	public Stream<String> numbers(int maxInclusive) {
		return Stream
				.iterate(0, i -> i <= maxInclusive, i -> i + 1)
				.map(i -> "" + i);
	}

	public Stream<String> visibilities() {
		return Arrays.stream(visibilities);
	}

	public Stream<String> textFile() throws IOException {
		return Files.lines(textFile);
	}

}

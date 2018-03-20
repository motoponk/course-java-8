package org.codefx.courses.java8.stream;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamSources {

	final Path textFile = Paths.get(StreamSources.class.getClassLoader().getResource("file.txt").getPath());
	final String[] visibilities = { "public", "protected", "private" };

}

package org.codefx.courses.java8.stream;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

	/*
	 * TASK: Uncomment the tests and implement the methods that makes them pass.
	 */

	private final StreamSources sources = new StreamSources();

	@Test
	public void letters() {
		assertThat(sources.letters()).containsExactly('c', 'o', 'd', 'e', 'f', 'x');
	}

	@Test
	public void numbers() {
		assertThat(sources.numbers(5)).containsExactly("0", "1", "2", "3", "4", "5");
	}

	@Test
	public void visibilities() {
		assertThat(sources.visibilities()).containsExactly(sources.visibilities);
	}

	@Test
	public void textFile() throws IOException {
		assertThat(sources.textFile()).containsExactly("this", "is", "a", "text", "file");
	}

}

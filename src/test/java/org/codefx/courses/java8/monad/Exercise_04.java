package org.codefx.courses.java8.monad;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

	/*
	 * TASK: Play with Optional and the three monad laws; try to break them
	 */

	@Test
	void rightIdentity() {
		// no way to break it
	}

	@Test
	void leftIdentity() {
		String v = null;
		Function f = s -> "mango";

		Optional applied = Optional.ofNullable(f.apply(v));
		Optional mapped = Optional.ofNullable(v).map(f);

		assertThat(applied).isNotEqualTo(mapped);

	}

	@Test
	void associativity() {
		String v = "kiwi";
		Function f = s -> null;
		Function g = s -> "mango";

		Optional mapped = Optional.of(v).map(f).map(g);
		Optional composed = Optional.of(v).map(f.andThen(g));

		assertThat(composed).isNotEqualTo(mapped);
	}

}

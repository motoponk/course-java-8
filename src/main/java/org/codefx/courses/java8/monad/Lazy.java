package org.codefx.courses.java8.monad;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class Lazy<T> {

	private final Supplier<? extends T> generator;
	private volatile T element;

	private Lazy(Supplier<? extends T> generator) {
		this.generator = requireNonNull(generator);
		this.element = null;
	}

	public static <T> Lazy<T> of(Supplier<? extends T> generator) {
		return new Lazy<>(generator);
	}

	public T get() {
		if (element == null)
			initialize();
		return element;
	}

	private void initialize() {
		// for a more performant implementation see Effective Java (item 71 in 2nd edition)
		synchronized (this) {
			if (element == null) {
				element = generator.get();
				requireNonNull(element, "The generator function must not produce a null result.");
			}
		}
	}

}

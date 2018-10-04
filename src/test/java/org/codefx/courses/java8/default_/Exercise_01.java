package org.codefx.courses.java8.default_;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Exercise_01 {

	/*
	 * TASK: Uncomment the first three tests and make them pass by creating a default method
	 *       `Translator.translate(List<String>)`.
	 *
	 * TASK: Uncomment `verifySentence` and add a default method `Translator.verify(String)`
	 *       that always returns false.
	 * OBSERVE: The test `verifySentence` shows that the method actually returns true.
	 *          Why does it do that?
	 *
	 * TASK: Uncomment `verifyText` and add a default method `Translator.verify(List<String>)`
	 *       that returns false.
	 *       You will get a compile error in `NoOpEditor` - fix it two different ways:
	 *        - implement `verify(List<String>)` in `NoOpEditor` by calling the method
	 *          on the appropriate interface (test has to pass)
	 *        - find a way to have one of the interfaces `Translator` and `Editor` extend the
	 *          other, so that one is more specific and default method resolution works
	 *          without adding a method to `NoOpEditor`
	 */

	private final Translator translator = new NoOpEditor();
	private final NoOpEditor nopOpEditor = new NoOpEditor();

	@Test
	public void translateText() {
		List<String> paragraph = List.of("This is the first sentence.", "And this the second one (hardly).");

		List<String> translation = nopOpEditor.translate(paragraph);

		assertThat(translation).isEqualTo(paragraph);
	}

	@Test
	public void isNotInstanceMethod() {
		Throwable thrown = catchThrowable(()-> NoOpEditor.class.getDeclaredMethod("translate", List.class));
		assertThat(thrown)
				.as("It looks like you declared the method on `NoOpEditor`.")
				.isInstanceOf(Exception.class);
	}

	@Test
	public void isDefaultMethod() throws ReflectiveOperationException {
		Method translate = Translator.class.getMethod("translate", List.class);
		assertThat(translate.isDefault())
				.as("It looks like you did not declare `translate` as a default method on `Translator`.")
				.isTrue();
	}

	@Test
	public void verifySentence() {
		boolean correct = translator.verify("This is a sentence");

		assertThat(correct).isTrue();
	}

	@Test
	public void verifyText() {
		List<String> paragraph = List.of("This is the first sentence.", "And this the second one (hardly).");

		boolean correct = translator.verify(paragraph);

		assertThat(correct).isTrue();
	}

}

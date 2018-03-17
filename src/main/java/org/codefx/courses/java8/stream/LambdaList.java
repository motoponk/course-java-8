package org.codefx.courses.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaList<E> extends ArrayList<E> {

	public static void main(String[] args) {
		LambdaList<String> strings = LambdaList.create("c", "o", "d", "e", "fx");

		for (String string : strings) {
			System.out.println(string);
		}

	}

	// OPERATIONS

	// for each element
	public void forEachElement(Consumer<E> consumer) {
		for (E element : this)
			consumer.accept(element);
	}

	// filter
	public LambdaList<E> filter(Predicate<E> filter) {
		LambdaList<E> filtered = LambdaList.create();
		for (E element : this)
			if (filter.test(element))
				filtered.add(element);
		return filtered;
	}

	// map
	public <F> LambdaList<F> map(Function<E, F> function) {
		LambdaList<F> mapped = LambdaList.create();
		for (E element : this) {
			F mappedElement = function.apply(element);
			mapped.add(mappedElement);
		}
		return mapped;
	}

	// reduce
	public E reduce(E initialValue, BinaryOperator<E> operator) {
		E reduction = initialValue;
		for (E element : this)
			reduction = operator.apply(reduction, element);
		return reduction;
	}

	// collect
	public <C extends Collection<E>> C collect(Supplier<C> constructor) {
		C collection = constructor.get();
		collection.addAll(this);
		return collection;
	}

	// CONSTRUCTION

	private LambdaList() {
		super();
	}

	private LambdaList(Collection<E> elements) {
		super(elements);
	}

	public static <E> LambdaList<E> create(E... elements) {
		return new LambdaList<>(Arrays.asList(elements));
	}

}

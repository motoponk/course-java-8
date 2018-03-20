package org.codefx.courses.java8.stream;

import org.assertj.core.api.SoftAssertions;
import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.codefx.courses.java8.lambda.Todo.Importance.A_LITTLE;
import static org.codefx.courses.java8.lambda.Todo.Importance.SOMEWHAT;
import static org.codefx.courses.java8.lambda.Todo.Importance.VERY;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Exercise_05 {

	/*
	 * TASK: Take a look at https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html,
	 *       uncomment the tests, and implement the methods that make them pass.
	 */

	private final Todo groceries = new Todo("Groceries", A_LITTLE);
	private final Todo washingCar = new Todo("Washing car", A_LITTLE);
	private final Todo taxes = new Todo("Taxes", VERY);
	private final Todo garage = new Todo("Garage", VERY);
	private final Todo laundry = new Todo("Laundry", SOMEWHAT);
	private final List<Todo> todos = List.of(groceries, taxes, laundry, garage, washingCar);

	private final StreamCollectors collectors = new StreamCollectors();

	@Test
	void orderedItems() {
		assertThat(collectors.orderedItems()).extracting(Item::id).containsExactlyInAnyOrder(1, 2, 3);
	}

	@Test
	void ordersById() {
		Map<Integer, Order> orders = collectors.ordersById();

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(orders).hasSize(4);
		orders.forEach((id, order) -> softly
				.assertThat(order.id()).isEqualTo(id));
		softly.assertAll();
	}

	@Test
	void itemIdsByOrderId() {
		Map<Integer, List<Integer>> itemIds = collectors.itemIdsByOrderId();

		assertThat(itemIds).containsOnly(
				entry(1, List.of(1, 2)),
				entry(2, List.of(2, 3)),
				entry(3, List.of(1, 3)),
				entry(4, List.of(1)));
	}

	@Test
	void orderedItemsById() {
		// WATCH OUT: Look at the contract of `Collectors.toMap` and
		//            learn why `orderedItemsById` throws an `IllegalStateException`
		org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, collectors::orderItemsByItemId);
	}

	@Test
	void descriptions() {
		assertThat(collectors.descriptions(todos)).isEqualTo("Groceries, Taxes, Laundry, Garage, Washing car");
	}

	@Test
	void todosByImportance() {
		Map<Importance, List<Todo>> todosByImportance = collectors.todosByImportance(todos);

		assertThat(todosByImportance).containsOnly(
				entry(A_LITTLE, List.of(groceries, washingCar)),
				entry(SOMEWHAT, List.of(laundry)),
				entry(VERY, List.of(taxes, garage)));
	}

	@Test
	void descriptionsByImportance() {
		// WATCH OUT: This one is a tough nut to crack!
		Map<Importance, String> descriptionsByImportance = collectors.descriptionsByImportance(todos);

		assertThat(descriptionsByImportance).containsOnly(
				entry(A_LITTLE, "Groceries, Washing car"),
				entry(SOMEWHAT, "Laundry"),
				entry(VERY, "Taxes, Garage"));
	}

}

package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;
import org.codefx.courses.java8.stream.repo.ItemRepository;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class StreamCollectors {

	private final OrderRepository orders = new OrderRepository();
	private final ItemRepository items = new ItemRepository();

	public Set<Item> orderedItems() {
		return orders.loadOrderIds().stream()
				.flatMap(id -> orders.loadOrder(id).stream())
				.flatMap(order -> order.itemIds().stream())
				.flatMap(id -> items.loadItem(id).stream())
				.collect(toSet());
	}

	public Map<Integer, Order> ordersById() {
		return orders.loadOrderIds().stream()
				.flatMap(id -> orders.loadOrder(id).stream())
				.collect(toMap(Order::id, order -> order));
	}

	public Map<Integer, List<Integer>> itemIdsByOrderId() {
		return orders.loadOrderIds().stream()
				.flatMap(id -> orders.loadOrder(id).stream())
				.collect(toMap(Order::id, Order::itemIds));
	}

	public Map<Integer, Item> orderItemsByItemId() {
		return orders.loadOrderIds().stream()
				.flatMap(id -> orders.loadOrder(id).stream())
				.flatMap(order -> order.itemIds().stream())
				.flatMap(id -> items.loadItem(id).stream())
				.collect(toMap(Item::id, item -> item));
	}

	public String descriptions(List<Todo> todos) {
		return todos.stream()
				.map(Todo::description)
				.collect(joining(", "));
	}

	public Map<Importance, List<Todo>> todosByImportance(List<Todo> todos) {
		return todos.stream().collect(groupingBy(Todo::importance));
	}

	public Map<Importance, String> descriptionsByImportance(List<Todo> todos) {
		return todos.stream()
				.map(todo -> Map.entry(todo.importance(), todo.description()))
				.collect(groupingBy(Entry::getKey, mapping(Entry::getValue, joining(", "))));
	}

}

package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.lambda.Todo;
import org.codefx.courses.java8.lambda.Todo.Importance;
import org.codefx.courses.java8.stream.repo.ItemRepository;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StreamCollectors {

	private final OrderRepository orders = new OrderRepository();
	private final ItemRepository items = new ItemRepository();

	public Set<Item> orderedItems() {
		// reuse/copy the functionality you implemented in Exercise 03 to get a
		// `Stream<Item>`; this exercise is only about collecting the elements!
		throw new RuntimeException("Not yet implemented.");
	}

	public Map<Integer, Order> ordersById() {
		throw new RuntimeException("Not yet implemented.");
	}

	public Map<Integer, List<Integer>> itemIdsByOrderId() {
		throw new RuntimeException("Not yet implemented.");
	}

	public Map<Integer, Item> orderItemsByItemId() {
		throw new RuntimeException("Not yet implemented.");
	}

	public String descriptions(List<Todo> todos) {
		throw new RuntimeException("Not yet implemented.");
	}

	public Map<Importance, List<Todo>> todosByImportance(List<Todo> todos) {
		throw new RuntimeException("Not yet implemented.");
	}

	public Map<Importance, String> descriptionsByImportance(List<Todo> todos) {
		throw new RuntimeException("Not yet implemented.");
	}

}

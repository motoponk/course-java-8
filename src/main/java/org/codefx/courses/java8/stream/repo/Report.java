package org.codefx.courses.java8.stream.repo;

import org.codefx.courses.java8.stream.repo.AddressRepository.Address;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;
import org.codefx.courses.java8.stream.repo.UserRepository.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Report {

	private final UserRepository users = new UserRepository();
	private final OrderRepository orders = new OrderRepository();
	private final ItemRepository items = new ItemRepository();
	private final AddressRepository addresses = new AddressRepository();

	public void forEachOrderItem(Consumer<Item> processItem) {
	}

	public Optional<User> anyUserWithoutOrder() {
		return Optional.empty();
	}

	public long noOfOrdersWithItems(int noOfItems) {
		return 0;
	}

	public boolean existsOrderWithAtLeastThreeItems() {
		return false;
	}

	public Optional<Order> mostRecentOrder() {
		return Optional.empty();
	}

	public List<Address> addressesWithOrders() {
		return List.of();
	}

	public List<Item> threeMostRecentlyOrderedOrderItems() {
		return List.of();
	}

	private Stream<Item> recentlyOrderedOrderItems() {
		return Stream.of();
	}

	public List<Item> mostRecentlyOrderedOrderItemsExceptFirstThree() {
		return List.of();
	}

	public List<User> usersByOldestOrder() {
		return List.of();
	}

	public String csvListOfOrderedItemDescriptionsLexicographicallySorted() {
		return "";
	}

}

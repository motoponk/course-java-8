package org.codefx.courses.java8.stream.repo;

import org.codefx.courses.java8.stream.repo.AddressRepository.Address;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;
import org.codefx.courses.java8.stream.repo.UserRepository.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Report {

	private final UserRepository users = new UserRepository();
	private final OrderRepository orders = new OrderRepository();
	private final ItemRepository items = new ItemRepository();
	private final AddressRepository addresses = new AddressRepository();

	public void forEachOrderId(Consumer<Integer> processOrderId) {
		throw new RuntimeException("Not yet implemented.");
	}

	public void forEachOptionalOrder(Consumer<Optional<Order>> processOptionalOrder) {
		throw new RuntimeException("Not yet implemented.");
	}

	public void forEachOrder(Consumer<Order> processOrder) {
		throw new RuntimeException("Not yet implemented.");
	}

	public void forEachOrderItem(Consumer<Item> processItem) {
		throw new RuntimeException("Not yet implemented.");
	}

	public Optional<User> anyUserWithoutOrder() {
		throw new RuntimeException("Not yet implemented.");
	}

	public long noOfOrdersWithItems(int noOfItems) {
		throw new RuntimeException("Not yet implemented.");
	}

	public boolean existsOrderWithAtLeastThreeItems() {
		throw new RuntimeException("Not yet implemented.");
	}

	public Optional<Order> mostRecentOrder() {
		throw new RuntimeException("Not yet implemented.");
	}

	public List<Address> addressesWithOrders() {
		throw new RuntimeException("Not yet implemented.");
	}

	public List<Item> threeMostRecentlyOrderedOrderItems() {
		throw new RuntimeException("Not yet implemented.");
	}

	private Stream<Item> recentlyOrderedOrderItems() {
		throw new RuntimeException("Not yet implemented.");
	}

	public List<Item> mostRecentlyOrderedOrderItemsExceptFirstThree() {
		throw new RuntimeException("Not yet implemented.");
	}

	public List<User> usersByOldestOrder() {
		throw new RuntimeException("Not yet implemented.");
	}

	public String csvListOfOrderedItemDescriptionsLexicographicallySorted() {
		throw new RuntimeException("Not yet implemented.");
	}

}

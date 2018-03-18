package org.codefx.courses.java8.stream.repo;

import org.codefx.courses.java8.stream.repo.AddressRepository.Address;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;
import org.codefx.courses.java8.stream.repo.UserRepository.User;

import java.time.ZonedDateTime;
import java.util.Comparator;
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

	public void forEachOrderId(Consumer<Integer> processOrderId) {
		orders.loadOrderIds().stream()
				.forEach(processOrderId);
	}

	public void forEachOptionalOrder(Consumer<Optional<Order>> processOptionalOrder) {
		orders.loadOrderIds().stream()
				.map(orders::loadOrder)
				.forEach(processOptionalOrder);
	}

	public void forEachOrder(Consumer<Order> processOrder) {
		// needing a `Stream<Order>` from `orders` is common,
		// so after this, I added it as a method
		orders.loadOrderIds().stream()
				.map(orders::loadOrder)
				.flatMap(Optional::stream)
				.forEach(processOrder);
	}

	public void forEachOrderItem(Consumer<Item> processItem) {
		orders.streamOrders()
				.flatMap(order -> items.streamItems(order.itemIds()))
				.forEach(processItem);
	}

	public Optional<User> anyUserWithoutOrder() {
		return users.streamUsers()
				.filter(user -> user.orderIds().isEmpty())
				.findAny();
	}

	public long noOfOrdersWithItems(int noOfItems) {
		return orders.streamOrders()
				.filter(order -> order.itemIds().size() == noOfItems)
				.count();
	}

	public boolean existsOrderWithAtLeastThreeItems() {
		return orders.streamOrders()
				.anyMatch(order -> order.itemIds().size() >= 3);
	}

	public Optional<Order> mostRecentOrder() {
		return orders.streamOrders()
				.max((o1, o2) -> o1.date().compareTo(o2.date()));
	}

	public List<Address> addressesWithOrders() {
		return orders.streamOrders()
				.flatMap(order -> addresses.loadAddress(order.addressId()).stream())
				.distinct()
				.collect(toList());
	}

	public List<Item> threeMostRecentlyOrderedOrderItems() {
		return recentlyOrderedOrderItems()
				.limit(3)
				.collect(toList());
	}

	private Stream<Item> recentlyOrderedOrderItems() {
		return orders.streamOrders()
				.sorted((o1, o2) -> - o1.date().compareTo(o2.date()))
				.flatMap(order -> items.streamItems(order.itemIds()));
	}

	public List<Item> mostRecentlyOrderedOrderItemsExceptFirstThree() {
		return recentlyOrderedOrderItems()
				.skip(3)
				.collect(toList());
	}

	public List<User> usersByOldestOrder() {
		return users.streamUsers()
				.sorted(this::compareOldestOrders)
				.collect(toList());
	}

	// the (static) methods on `Comparator` allow a more elegant solution,
	// but I show this one because it uses no additional APIs
	private int compareOldestOrders(User u1, User u2) {
		Optional<Order> u1Order = oldestOrder(u1);
		Optional<Order> u2Order = oldestOrder(u2);

		if (u1Order.isPresent() && u2Order.isPresent()) {
			ZonedDateTime u1Date = u1Order.get().date();
			ZonedDateTime u2Date = u2Order.get().date();
			return u1Date.compareTo(u2Date);
		}

		if (u1Order.isPresent())
			return -1;

		if (u2Order.isPresent())
			return 1;

		return 0;
	}

	private Optional<Order> oldestOrder(User user) {
		return user.orderIds().stream()
				.flatMap(orderId -> orders.loadOrder(orderId).stream())
				.min((o1, o2) -> o1.date().compareTo(o2.date()));
	}

	public String csvListOfOrderedItemDescriptionsLexicographicallySorted() {
		return orders.streamOrders()
				.flatMap(order -> order.itemIds().stream())
				.distinct()
				.flatMap(itemId -> items.loadItem(itemId).stream())
				.map(Item::description)
				.sorted()
				.collect(joining(", "));
	}

}

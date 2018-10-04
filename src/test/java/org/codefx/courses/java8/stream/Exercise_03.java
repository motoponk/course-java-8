package org.codefx.courses.java8.stream;

import org.codefx.courses.java8.stream.repo.AddressRepository.Address;
import org.codefx.courses.java8.stream.repo.ItemRepository.Item;
import org.codefx.courses.java8.stream.repo.OrderRepository.Order;
import org.codefx.courses.java8.stream.repo.Report;
import org.codefx.courses.java8.stream.repo.UserRepository.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_03 {

	/*
	 * OBSERVE: Javadoc for Stream
	 *          at https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/package-summary.html
	 *          and https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html
	 *          is pretty good; keep it open while solving these tasks.
	 *
	 * TASK: Uncomment the tests and implement the corresponding methods
	 *       in `Report`.
	 *
	 * HINT: Look out for repeating requests to the repositories (e.g. needing a Stream<User>) and
	 *       feel free to extend the `...Repository` classes with such methods.
	 *
	 * HINT: The term "order-items" means items that were actually ordered and can include duplicates.
	 *       Plain "items" can not have duplicates.
	 */

	private final Report report = new Report();

//	@Test
//	void forEachOrderId() {
//		List<Integer> orderIds = new ArrayList<>();
//
//		report.forEachOrderId(orderIds::add);
//
//		assertThat(orderIds).containsExactlyInAnyOrder(1, 2, 3, 4);
//	}

//	@Test
//	void forEachOptionalOrder() {
//		List<Optional<Order>> orders = new ArrayList<>();
//
//		report.forEachOptionalOrder(orders::add);
//
//		assertThat(orders)
//				.extracting(Optional::get)
//				.extracting(Order::id)
//				.containsExactlyInAnyOrder(1, 2, 3, 4);
//	}

//	@Test
//	void forEachOrder() {
//		List<Order> users = new ArrayList<>();
//
//		report.forEachOrder(users::add);
//
//		assertThat(users).extracting(Order::id).containsExactlyInAnyOrder(1, 2, 3);
//	}

//	@Test
//	void forEachOrderItem() {
//		List<Item> orderItems = new ArrayList<>();
//
//		report.forEachOrderItem(orderItems::add);
//
//		assertThat(orderItems).extracting(Item::id).containsExactlyInAnyOrder(1, 1, 1, 2, 2, 3, 3);
//	}

//	@Test
//	void anyUserWithoutOrder() {
//		Optional<User> user = report.anyUserWithoutOrder();
//
//		assertThat(user).map(User::id).contains(2);
//	}

//	@Test
//	void noOfOrdersWithItems() {
//		long oneItem = report.noOfOrdersWithItems(1);
//		long twoItems = report.noOfOrdersWithItems(2);
//		long threeItems = report.noOfOrdersWithItems(3);
//
//		assertThat(oneItem).isEqualTo(1);
//		assertThat(twoItems).isEqualTo(3);
//		assertThat(threeItems).isEqualTo(0);
//	}

//	@Test
//	void existsOrderWithAtLeastThreeItems() {
//		boolean exists = report.existsOrderWithAtLeastThreeItems();
//
//		assertThat(exists).isFalse();
//	}

//	@Test
//	void mostRecentOrder() {
//		Optional<Order> order = report.mostRecentOrder();
//
//		assertThat(order).map(Order::id).contains(4);
//	}

//	@Test
//	void addressesWithOrders() {
//		List<Address> addresses = report.addressesWithOrders();
//
//		assertThat(addresses).extracting(Address::id).containsExactlyInAnyOrder(1, 2, 3);
//	}

//	@Test
//	void threeMostRecentlyOrderedOrderItems() {
//		List<Item> items = report.threeMostRecentlyOrderedOrderItems();
//
//		assertThat(items).extracting(Item::id).containsExactlyInAnyOrder(1, 1, 3);
//	}

//	@Test
//	void mostRecentlyOrderedOrderItemsExceptFirstThree() {
//		List<Item> items = report.mostRecentlyOrderedOrderItemsExceptFirstThree();
//
//		assertThat(items).extracting(Item::id).containsExactlyInAnyOrder(1, 2, 2, 3);
//	}

//	@Test
//	void usersByOldestOrder() {
//		List<User> users = report.usersByOldestOrder();
//
//		assertThat(users).extracting(User::id).containsExactly(1, 3, 2);
//	}

//	@Test
//	void csvListOfOrderedItemDescriptionsLexicographicallySorted() {
//		String descriptions = report.csvListOfOrderedItemDescriptionsLexicographicallySorted();
//
//		assertThat(descriptions).isEqualTo("Book, Flower, Shoes");
//	}

}

package org.codefx.courses.java8.stream.repo;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.time.ZonedDateTime.now;

public class OrderRepository {

	private static final Map<Integer, Order> ORDER_DB;

	static {
		ORDER_DB = new HashMap<>();
		ORDER_DB.put(1, new Order(1, now().minusDays(20), 1, Arrays.asList(1, 2)));
		ORDER_DB.put(2, new Order(2, now().minusDays(15), 1, Arrays.asList(2, 3)));
		ORDER_DB.put(3, new Order(3, now().minusDays(10), 3, Arrays.asList(1, 3)));
		ORDER_DB.put(4, new Order(4, now().minusDays(5), 2, Arrays.asList(1)));
	}

	public Set<Integer> loadOrderIds() {
		return ORDER_DB.keySet();
	}

	public Optional<Order> loadOrder(int id) {
		return Optional.ofNullable(ORDER_DB.get(id));
	}

	public static class Order {

		private final int id;
		private final ZonedDateTime date;
		private final int addressId;
		private final List<Integer> itemIds;

		private Order(int id, ZonedDateTime date, int addressId, List<Integer> itemIds) {
			this.id = id;
			this.date = date;
			this.addressId = addressId;
			this.itemIds = itemIds;
		}

		public int id() {
			return id;
		}

		public ZonedDateTime date() {
			return date;
		}

		public int addressId() {
			return addressId;
		}

		public List<Integer> itemIds() {
			return itemIds;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Order user = (Order) o;
			return id == user.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

	}

}

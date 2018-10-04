package org.codefx.courses.java8.optional;

import org.codefx.courses.java8.optional.CustomerSearch.Customer;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HistorySearch {

	private static final Map<Integer, History> HISTORY_DB = Map.of(
			1, new History(1, List.of("Logged in", "Logged out")),
			2, new History(2, List.of("Logged in", "Purchased", "Logged out"))
	);

	private final CustomerSearch customerSearch;

	public HistorySearch(CustomerSearch customerSearch) {
		this.customerSearch = customerSearch;
	}

	public Optional<History> findHistory(Customer customer) {
		return Optional.ofNullable(HISTORY_DB.get(customer.id()));
	}

	// return an `Optional` that is empty or not, depending on whether
	// a history for a customer with that ID exists
	// (if you found a customer, use `findHistory` to find their history)
	public Optional<History> findCustomersHistory(int customerId) {
		return customerSearch.findCustomer(customerId)
				.flatMap(this::findHistory);
	}

	public static class History {

		private final int customerId;
		private final List<String> history;

		private History(int customerId, List<String> history) {
			this.customerId = customerId;
			this.history = history;
		}

		public int customerId() {
			return customerId;
		}

		public List<String> history() {
			return history;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			History history = (History) o;
			return customerId == history.customerId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(customerId);
		}

	}

}

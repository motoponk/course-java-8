package org.codefx.courses.java8.optional;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomerSearch {

	public static final Customer ANONYMOUS = new Customer(0, "Anonymous");

	private static final Map<Integer, Customer> CUSTOMER_DB = Map.of(
			1, new Customer(1, "Jane Doe"),
			2, new Customer(2, "John Doe"),
			3, new Customer(3, "Jimmy Doe")
	);

	// always return an empty `Optional`
	public Optional<Customer> findNonExistingCustomer(int id) {
		return null;
	}

	// return an `Optional`, but assume that the customer always exist
	// and throw a NullPointerException if it doesn't
	// (the combination of `Optional` and NPE doesn't make a lot of sense in real life)
	public Optional<Customer> findExistingCustomer(int id) {
		return null;
	}

	// return an `Optional` that is empty or not, depending on whether
	// a customer with that ID exists
	public Optional<Customer> findCustomer(int id) {
		return null;
	}

	Customer determineReplacement(int id) {
		return CUSTOMER_DB.get(id%3 + 1);
	}

	public static class Customer {

		private final int id;
		private final String name;

		private Customer(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int id() {
			return id;
		}

		public String name() {
			return name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Customer customer = (Customer) o;
			return id == customer.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

	}

}

package org.codefx.courses.java8.stream.repo;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class AddressRepository {

	private static final Map<Integer, Address> ADDRESS_DB = Map.of(
			1, new Address(1, "Doe, Some Street #42, Country"),
			2, new Address(2, "E-Corp, Evil Street #1, Country"),
			3, new Address(3, "Good Bakery, Baker Street #23, Country"),
			4, new Address(4, "Project Chaos, Paper Street #5, Country")
	);

	public Set<Integer> loadAddressIds() {
		return ADDRESS_DB.keySet();
	}

	public Optional<Address> loadAddress(int id) {
		return Optional.ofNullable(ADDRESS_DB.get(id));
	}

	public static class Address {

		private final int id;
		private final String details;

		private Address(int id, String details) {
			this.id = id;
			this.details = details;
		}

		public int id() {
			return id;
		}

		public String details() {
			return details;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Address address = (Address) o;
			return id == address.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

	}

}

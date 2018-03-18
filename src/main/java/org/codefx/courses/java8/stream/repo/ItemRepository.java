package org.codefx.courses.java8.stream.repo;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class ItemRepository {

	private static final Map<Integer, Item> ITEM_DB = Map.of(
			1, new Item(1, "Shoes"),
			2, new Item(2, "Book"),
			3, new Item(3, "Flower"),
			4, new Item(4, "CD")
	);

	public Set<Integer> loadItemIds() {
		return ITEM_DB.keySet();
	}

	public Optional<Item> loadItem(int id) {
		return Optional.ofNullable(ITEM_DB.get(id));
	}

	public Stream<Item> streamItems(Collection<Integer> ids) {
		return ids.stream()
				.flatMap(id -> loadItem(id).stream());
	}

	public static class Item {

		private final int id;
		private final String description;

		private Item(int id, String description) {
			this.id = id;
			this.description = description;
		}

		public int id() {
			return id;
		}

		public String description() {
			return description;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Item address = (Item) o;
			return id == address.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

	}

}

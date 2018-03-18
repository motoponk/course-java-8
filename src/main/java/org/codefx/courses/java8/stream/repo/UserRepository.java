package org.codefx.courses.java8.stream.repo;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class UserRepository {

	private static final Map<Integer, User> USER_DB = Map.of(
			1, new User(1, "Jane Doe", List.of(1, 4)),
			2, new User(2, "John Doe", List.of()),
			3, new User(3, "Jimmy Doe", List.of(2, 3))
	);

	public Set<Integer> loadUserIds() {
		return USER_DB.keySet();
	}

	public Optional<User> loadUser(int id) {
		return Optional.ofNullable(USER_DB.get(id));
	}

	public Stream<User> streamUsers() {
		return loadUserIds().stream()
				.flatMap(userId -> loadUser(userId).stream());
	}

	public static class User {

		private final int id;
		private final String name;
		private final List<Integer> orderIds;

		private User(int id, String name, List<Integer> orderIds) {
			this.id = id;
			this.name = name;
			this.orderIds = orderIds;
		}

		public int id() {
			return id;
		}

		public String name() {
			return name;
		}

		public List<Integer> orderIds() {
			return orderIds;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			User user = (User) o;
			return id == user.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

	}

}

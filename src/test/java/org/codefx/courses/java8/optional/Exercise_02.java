package org.codefx.courses.java8.optional;

import org.codefx.courses.java8.optional.CustomerSearch.Customer;
import org.codefx.courses.java8.optional.HistorySearch.History;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class Exercise_02 {

	/*
	 * TASK: Uncomment all but the last test and implement the methods in `CustomerSearch`
	 *       and `HistorySearch` that make them pass.
	 *
	 * TASK: Refactor the methods in `CustomerLogin` that you wrote for optional exercise 1
	 *       to use `Optional::ifPresent`, `ifPresentOrElse`, and `or`.
	 *
	 * TASK: Uncomment `logInCustomerOrLogAttempt` and implement the method in
	 *       `CustomerLogin` to make it pass.
	 *
	 * OBSERVE: If a meaningful alternative to an empty `Optional` exists (value or exception),
	 *          the `orElse...` methods provide a cleaner approach than `ifPresent`.
	 *          The latter only work well if "the end of the rope" is reached (like in
	 *          `logInCustomerIfExists` and `logInCustomerOrLogFailedAttempt`).
	 */

	private final CustomerSearch customerSearch = new CustomerSearch();
	private final Login loginMock = mock(Login.class);
	private final ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
	private final CustomerLogin login = new CustomerLogin(customerSearch, loginMock);
	private final HistorySearch historySearch = new HistorySearch(customerSearch);

	@ParameterizedTest(name = "customer with id = {0} and name = \"{1}\" exists: {2}")
	@CsvSource({
			"1, Jane Doe, true", "1, John Doe, false",
			"2, Jane Doe, false", "2, John Doe, true",
			"4, Jane Doe, false", "5, John Doe, false"})
	public void findCustomerWithName(int id, String name, boolean exists) {
		Optional<Customer> customer = customerSearch.findCustomerWithName(id, name);

		if (exists) {
			assertThat(customer).isPresent();
			assertThat(customer).map(Customer::id).contains(id);
			assertThat(customer).map(Customer::name).contains(name);
		} else
			assertThat(customer).isEmpty();
	}

	@ParameterizedTest(name = "id = {0}")
	@CsvSource({"1, Jane Doe", "2, John Doe", "3, Jimmy Doe", "4, _", "5, _"})
	public void findCustomerName(int id, String name) {
		Optional<String> customerName = customerSearch.findCustomerName(id);

		if (id <= 3) {
			assertThat(customerName).contains(name);
		} else {
			assertThat(customerName).isEmpty();
		}
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void findOrCreateCustomer(int id) {
		Optional<Customer> customer = customerSearch.findOrCreateCustomer(id);

		assertThat(customer).isPresent();
		assertThat(customer).map(Customer::id).contains(id);
		if (id > 3) {
			assertThat(customer).map(Customer::name).containsSame(CustomerSearch.NAMELESS);
		}
	}

	@ParameterizedTest(name = "id = {0}")
	@CsvSource({"1, Jane Doe", "2, John Doe", "3, Jimmy Doe", "4, _", "5, _"})
	public void findHistory(int id, String name) {
		Optional<History> history = historySearch.findCustomersHistory(id);

		if (id <= 2) {
			assertThat(history).isPresent();
			assertThat(history).map(History::customerId).contains(id);
		} else {
			assertThat(history).isEmpty();
		}
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void logInCustomerOrLogAttempt(int id) {
		login.logInCustomerOrLogFailedAttempt(id);

		if (id <= 3) {
			verify(loginMock).logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with %d", id)
					.isEqualTo(id);
		} else {
			ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
			verify(loginMock).logFailedAttempt(captor.capture());
			assertThat(captor.getValue())
					.as("logFailedAttempt should be called with %d", id)
					.isEqualTo(id);
		}
	}

}

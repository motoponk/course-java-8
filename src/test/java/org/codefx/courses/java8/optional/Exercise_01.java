package org.codefx.courses.java8.optional;

import org.codefx.courses.java8.optional.CustomerSearch.Customer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.codefx.courses.java8.optional.CustomerSearch.ANONYMOUS;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class Exercise_01 {

	/*
	 * OBSERVE: Javadoc for Optional at https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html
	 *          is pretty good; keep it open while solving these tasks.
	 *
	 * TASK: Uncomment the first three tests and implement the corresponding methods in `CustomerSearch`.
	 *
	 * TASK: Uncomment the other tests and implement the corresponding methods in `CustomerLogin`.
	 *       (Of the `CustomerSearch::login...` methods, only use `loginCustomer(id)` for that
	 *       and don't change that class any further.)
	 *       For now, stick to the `Optional` methods `isPresent`, `isEmpty`, and the `orElse...` variants.
	 */

	private final CustomerSearch search = new CustomerSearch();
	private final Login loginMock = mock(Login.class);
	private final ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
	private final CustomerLogin login = new CustomerLogin(search, loginMock);

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void findEmptyCustomer(int id) {
		assertThat(search.findNonExistingCustomer(id)).isEmpty();
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void findExistingCustomer(int id) {
		if (id <= 3) {
			assertThat(search.findExistingCustomer(id)).isPresent();
			assertThat(search.findExistingCustomer(id).orElseThrow().id()).isEqualTo(id);
		} else
			assertThatThrownBy(() -> search.findExistingCustomer(id))
					.isInstanceOf(NullPointerException.class);
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void findCustomer(int id) {
		if (id <= 3) {
			assertThat(search.findCustomer(id)).isPresent();
			assertThat(search.findCustomer(id).orElseThrow().id()).isEqualTo(id);
		} else
			assertThat(search.findCustomer(id)).isEmpty();
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void logInCustomerIfExists(int id) {
		login.logInCustomerIfExists(id);

		if (id <= 3) {
			verify(loginMock).logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with %d", id)
					.isEqualTo(id);
		} else
			verifyZeroInteractions(loginMock);
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void logInCustomerOrAnonymous(int id) {
		CustomerSearch searchSpy = spy(new CustomerSearch());
		CustomerLogin login = new CustomerLogin(searchSpy, loginMock);

		login.logInCustomerOrAnonymous(id);

		if (id <= 3) {
			verify(loginMock, times(1)
					.description("logIn should be called"))
					.logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with %d", id)
					.isEqualTo(id);
		} else {
			verify(loginMock, times(1)
					.description("logIn should be called"))
					.logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with anonymous customer")
					.isEqualTo(ANONYMOUS.id());
		}
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void logInCustomerOrReplacement(int id) {
		CustomerSearch searchSpy = spy(new CustomerSearch());
		CustomerLogin login = new CustomerLogin(searchSpy, loginMock);

		login.logInCustomerOrReplacement(id);

		if (id <= 3) {
			verify(loginMock, times(1)
					.description("logIn should be called"))
					.logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with %d", id)
					.isEqualTo(id);
			verify(searchSpy, never()
					.description("No need to call `determineReplacement` if there's a customer with that id. 😉"))
					.determineReplacement(anyInt());
		} else {
			verify(loginMock, times(1)
					.description("logIn should be called"))
					.logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with replacement customer")
					.isEqualTo(id%3 + 1);
		}
	}

	@ParameterizedTest(name = "id = {0}")
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	public void logInCustomerOrThrowIllegalArgumentException(int id) {
		if (id <= 3) {
			login.logInCustomerOrThrowIllegalArgumentException(id);
			verify(loginMock).logIn(captor.capture());
			assertThat(captor.getValue().id())
					.as("logIn should be called with %d", id)
					.isEqualTo(id);
		} else {
			assertThatThrownBy(() -> login.logInCustomerOrThrowIllegalArgumentException(id))
					.isInstanceOf(IllegalArgumentException.class);
		}
	}

}

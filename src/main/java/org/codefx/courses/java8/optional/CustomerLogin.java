package org.codefx.courses.java8.optional;

import org.codefx.courses.java8.optional.CustomerSearch.Customer;

import java.util.Optional;

import static org.codefx.courses.java8.optional.CustomerSearch.ANONYMOUS;

public class CustomerLogin {

	private final CustomerSearch search;
	private final Login login;

	public CustomerLogin(CustomerSearch search, Login login) {
		this.search = search;
		this.login = login;
	}

	// locate the customer and, if they are found, log them in
	public void logInCustomerIfExists(int id) {
		Optional<Customer> customer = search.findCustomer(id);
		if (customer.isPresent())
			login.logIn(customer.orElseThrow());
	}

	// locate the customer and, if they are found, log them in;
	// otherwise log in CustomerSearch.ANONYMOUS
	public void logInCustomerOrAnonymous(int id) {
		Customer customer = search
				.findCustomer(id)
				.orElse(ANONYMOUS);
		login.logIn(customer);
	}

	// locate the customer and, if they are found, log them in;
	// otherwise log in CustomerSearch::determineReplacement
	public void logInCustomerOrReplacement(int id) {
		Customer customer = search
				.findCustomer(id)
				.orElseGet(() -> search.determineReplacement(id));
		login.logIn(customer);
	}

	// locate the customer and, if they are found, log them in;
	// otherwise throw an IllegalArgumentExcpetion
	public void logInCustomerOrThrowIllegalArgumentException(int id) {
		Customer customer = search
				.findCustomer(id)
				.orElseThrow(IllegalArgumentException::new);
		login.logIn(customer);
	}

	// locate the customer and, if they are found, log them in;
	// otherwise call Login::logFailedAttempt
	public void logInCustomerOrLogFailedAttempt(int id) {
		throw new RuntimeException("Not yet implemented.");
	}

}

package org.codefx.courses.java8.optional;

import org.codefx.courses.java8.optional.CustomerSearch.Customer;

public interface Login {

	void logIn(Customer customer);

	void logFailedAttempt(int id);

}

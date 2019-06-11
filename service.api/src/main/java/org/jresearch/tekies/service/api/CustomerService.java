package org.jresearch.tekies.service.api;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.jresearch.tekies.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Customer management
 *
 * Out of scope of the test task
 */
public interface CustomerService {

	@Nonnull
	public Customer addCustomer(String firstName, String lastName, String phone, @Nonnull String email, LocalDate birthday) throws DuplicateEmail;

	@Nonnull
	public Optional<Customer> getCustomer(long customerId);

	@Nonnull
	public Customer updateCustomer(@Nonnull Customer update) throws DuplicateEmail;

	public void removeCustomer(long customerId);

	@Nonnull
	public Page<Customer> getCustomers(@Nonnull Pageable pageParameters);

}

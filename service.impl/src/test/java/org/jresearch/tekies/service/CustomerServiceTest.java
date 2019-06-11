package org.jresearch.tekies.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Optional;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.DuplicateEmail;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("nls")
public class CustomerServiceTest extends BaseTest {

	@Autowired
	private CustomerService customerService;

	@Test
	public void testСontext() {
		assertNotNull(customerService);
	}

	@Test
	public void testService() throws DuplicateEmail {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		assertNotNull(customer);
		assertNotNull(customer.getId());
		Optional<Customer> optional = customerService.getCustomer(customer.getId().longValue());
		assertNotNull(optional);
		assertTrue(optional.isPresent());

		optional = customerService.getCustomer(Long.MIN_VALUE);
		assertNotNull(optional);
		assertTrue(optional.isEmpty());

	}

	@Test(expected = DuplicateEmail.class)
	public void testDuplicateEmail() throws DuplicateEmail {
		customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Page<Customer> customers = customerService.getCustomers(Pageable.unpaged());
		assertEquals(2, customers.getSize());
	}
}

package org.jresearch.tekies.resources.api;

import static org.junit.Assert.*;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDaoTest extends BaseTest {

	@Autowired
	private CustomerDao dao;

	@Test
	public void testCustomer() {
		assertEquals(20, dao.count());
		Customer customer = new Customer();
		Customer saved = dao.save(customer);
		assertNotNull(saved.getId());
		assertEquals(21, dao.count());
		dao.delete(saved);
		assertEquals(20, dao.count());
	}

}

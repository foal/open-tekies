package org.jresearch.tekies.resources.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerDaoTest extends BaseTest {

	@Autowired
	private CustomerDao dao;

	@Test
	public void testCustomer() {
		assertEquals(0, dao.count());
		Customer customer = new Customer();
		Customer saved = dao.save(customer);
		assertNotNull(saved.getId());
		assertEquals(1, dao.count());
		dao.delete(saved);
		assertEquals(0, dao.count());
	}

}

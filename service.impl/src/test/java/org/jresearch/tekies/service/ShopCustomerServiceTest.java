package org.jresearch.tekies.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.domain.ItemOrder;
import org.jresearch.tekies.resources.api.ItemDao;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.DuplicateEmail;
import org.jresearch.tekies.service.api.NoCustomer;
import org.jresearch.tekies.service.api.NoItem;
import org.jresearch.tekies.service.api.NoOrder;
import org.jresearch.tekies.service.api.ShopCustomerService;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("nls")
public class ShopCustomerServiceTest extends BaseTest {

	@Autowired
	private ShopCustomerService shopCustomerService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ItemDao itemDao;

	@Test
	public void testСontext() {
		assertNotNull(shopCustomerService);
	}

	@Test
	public void testService() throws DuplicateEmail, NoItem, NoCustomer {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item saved = itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(customer.getId().longValue(), saved.getId().longValue());
		assertNotNull(order);
		assertNotNull(order.getId());
	}

	@Test(expected = NoCustomer.class)
	public void testNoCustomer() throws DuplicateEmail, NoItem, NoCustomer {
		customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item saved = itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(Long.MIN_VALUE, saved.getId().longValue());
		assertNotNull(order);
		assertNotNull(order.getId());
	}

	@Test(expected = NoItem.class)
	public void testNoItemOnCreate() throws DuplicateEmail, NoItem, NoCustomer {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(customer.getId().longValue(), Long.MIN_VALUE);
		assertNotNull(order);
		assertNotNull(order.getId());
	}

	@Test(expected = NoItem.class)
	public void testNoItemOnAdd() throws DuplicateEmail, NoItem, NoCustomer, NoOrder {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item saved = itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(customer.getId().longValue(), saved.getId().longValue());
		assertNotNull(order);
		assertNotNull(order.getId());

		shopCustomerService.addToOrder(order.getId().longValue(), Long.MIN_VALUE);
	}

	@Test(expected = NoOrder.class)
	public void testNoOrder() throws DuplicateEmail, NoItem, NoCustomer, NoOrder {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item saved = itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(customer.getId().longValue(), saved.getId().longValue());
		assertNotNull(order);
		assertNotNull(order.getId());

		Item sndTtem = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item sndSaved = itemDao.save(sndTtem);
		shopCustomerService.addToOrder(Long.MIN_VALUE, sndSaved.getId().longValue());
	}

	@Test
	public void testAddItem() throws DuplicateEmail, NoItem, NoCustomer, NoOrder {
		Customer customer = customerService.addCustomer("firstName", "lastName", "phone", "email", LocalDate.now().minusYears(30));
		Item item = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item saved = itemDao.save(item);

		ItemOrder order = shopCustomerService.createOrder(customer.getId().longValue(), saved.getId().longValue());
		assertNotNull(order);
		assertNotNull(order.getId());

		ItemOrder updateOrder = shopCustomerService.addToOrder(order.getId().longValue(), saved.getId().longValue());

		assertNotNull(updateOrder);
		assertNotNull(updateOrder.getItemReservations());
		assertFalse(updateOrder.getItemReservations().isEmpty());
		assertEquals(1, updateOrder.getItemReservations().size());
		assertNotNull(updateOrder.getItemReservations().get(0));
		assertEquals(2, updateOrder.getItemReservations().get(0).getAmount());

		Item sndTtem = new Item();
		item.setDescription("description");
		item.setName("name");
		item.setPrice(BigDecimal.valueOf(200));
		item.setVat(BigDecimal.valueOf(20));
		Item sndSaved = itemDao.save(sndTtem);

		updateOrder = shopCustomerService.addToOrder(order.getId().longValue(), sndSaved.getId().longValue());

		assertNotNull(updateOrder);
		assertNotNull(updateOrder.getItemReservations());
		assertFalse(updateOrder.getItemReservations().isEmpty());
		assertEquals(2, updateOrder.getItemReservations().size());
		assertNotNull(updateOrder.getItemReservations().get(0));
		assertEquals(2, updateOrder.getItemReservations().get(0).getAmount());
	}

}

package org.jresearch.tekies.resources.api;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.domain.ItemOrder;
import org.jresearch.tekies.domain.ItemReservation;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoTest extends BaseTest {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ItemDao itemDao;

	@Test
	public void testItemOrder() {
		assertEquals(0, orderDao.count());
		ItemOrder item = new ItemOrder();
		ItemOrder saved = orderDao.save(item);
		assertNotNull(saved.getId());
		assertEquals(1, orderDao.count());
		orderDao.delete(saved);
		assertEquals(0, orderDao.count());
	}

	@Test
	public void testItemOrderWithReservations() {
		Item item1 = new Item();
		item1.setName("name1");
		item1.setPrice(BigDecimal.valueOf(10l));
		Item item2 = new Item();
		item2.setName("name2");
		item2.setPrice(BigDecimal.valueOf(20l));
		Item sItem1 = itemDao.saveAndFlush(item1);
		Item sItem2 = itemDao.saveAndFlush(item2);

		assertEquals(0, orderDao.count());
		ItemOrder item = new ItemOrder();
		ItemReservation e1 = new ItemReservation();
		e1.setItem(sItem1);
		e1.setPrice(sItem1.getPrice());
		item.getItemReservations().add(e1);
		ItemReservation e2 = new ItemReservation();
		e2.setItem(sItem2);
		e2.setPrice(sItem2.getPrice());
		item.getItemReservations().add(e2);
		ItemOrder saved = orderDao.save(item);
		assertNotNull(saved.getId());
		List<ItemReservation> itemReservations = saved.getItemReservations();
		assertNotNull(itemReservations);
		assertFalse(itemReservations.isEmpty());
		assertEquals(2, itemReservations.size());
		assertEquals(BigDecimal.valueOf(10l), itemReservations.get(0).getPrice());
		assertEquals(BigDecimal.valueOf(20l), itemReservations.get(1).getPrice());
		assertEquals(1, orderDao.count());
		itemReservations.get(0).setPrice(BigDecimal.valueOf(9l));
		ItemOrder newSaved = orderDao.save(saved);

		assertNotNull(newSaved.getId());
		itemReservations = newSaved.getItemReservations();
		assertNotNull(itemReservations);
		assertFalse(itemReservations.isEmpty());
		assertEquals(2, itemReservations.size());
		assertTrue(BigDecimal.valueOf(9l).compareTo(itemReservations.get(0).getPrice()) == 0);
		assertTrue(BigDecimal.valueOf(10l).compareTo(itemReservations.get(0).getItem().getPrice()) == 0);
		assertTrue(BigDecimal.valueOf(20l).compareTo(itemReservations.get(1).getPrice()) == 0);
		assertEquals(1, orderDao.count());

		orderDao.delete(saved);
		assertEquals(0, orderDao.count());
	}

}

package org.jresearch.tekies.resources.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemDaoTest extends BaseTest {

	@Autowired
	private ItemDao dao;

	@Test
	public void testItem() {
		assertEquals(0, dao.count());
		Item item = new Item();
		Item saved = dao.save(item);
		assertNotNull(saved.getId());
		assertEquals(1, dao.count());
		dao.delete(saved);
		assertEquals(0, dao.count());
	}

}

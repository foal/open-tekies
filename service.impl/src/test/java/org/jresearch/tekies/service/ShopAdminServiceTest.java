package org.jresearch.tekies.service;

import static org.junit.Assert.*;

import org.jresearch.tekies.service.api.ShopAdminService;
import org.jresearch.tekies.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopAdminServiceTest extends BaseTest {

	@Autowired
	private ShopAdminService shopAdminService;

	@Test
	public void testСontext() {
		assertNotNull(shopAdminService);
	}

}

package org.jresearch.tekies.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.domain.ItemOrder;
import org.jresearch.tekies.domain.ItemReservation;
import org.jresearch.tekies.resources.api.ItemDao;
import org.jresearch.tekies.resources.api.OrderDao;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.NoCustomer;
import org.jresearch.tekies.service.api.NoItem;
import org.jresearch.tekies.service.api.NoOrder;
import org.jresearch.tekies.service.api.ShopCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.util.streamex.StreamEx;

@Service
public class ShopCustomerServiceImpl implements ShopCustomerService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerService customerService;

	@Override
	@Nonnull
	public Optional<Item> getItem(long itemId) {
		return itemDao.findById(Long.valueOf(itemId));
	}

	@Override
	@Nonnull
	public List<Item> getItems() {
		return itemDao.findAll();
	}

	@Override
	@Nonnull
	public Optional<ItemOrder> getOrder(long orderId) {
		return orderDao.findById(Long.valueOf(orderId));
	}

	@Override
	@Nonnull
	public List<ItemOrder> getCustomerOrders(long customerId) throws NoCustomer {
		Customer customer = customerService.getCustomer(customerId).orElseThrow(NoCustomer::new);
		return orderDao.findAllByCustomer(customer, Pageable.unpaged()).getContent();
	}

	@Override
	@Nonnull
	@Transactional
	public ItemOrder createOrder(long customerId, long itemId) throws NoItem, NoCustomer {
		Customer customer = customerService.getCustomer(customerId).orElseThrow(NoCustomer::new);
		ItemOrder order = new ItemOrder();
		order.getItemReservations().add(createItemReservation(itemId));
		order.setCreateTime(OffsetDateTime.now(ZoneOffset.UTC));
		order.setCustomer(customer);
		return orderDao.save(order);
	}

	private ItemReservation createItemReservation(long itemId) throws NoItem {
		Item item = getItem(itemId).orElseThrow(NoItem::new);
		ItemReservation reservation = new ItemReservation();
		reservation.setAmount(1);
		reservation.setDiscount(BigDecimal.ZERO);
		reservation.setItem(item);
		reservation.setPrice(item.getPrice());
		reservation.setVat(item.getVat());
		return reservation;
	}

	@SuppressWarnings("boxing")
	@Override
	@Nonnull
	@Transactional
	public ItemOrder addToOrder(long orderId, long itemId) throws NoOrder, NoItem {
		ItemOrder order = orderDao.findById(Long.valueOf(orderId)).orElseThrow(NoOrder::new);
		Optional<ItemReservation> existingReservation = StreamEx.of(order.getItemReservations()).filterBy(r -> r.getItem().getId(), itemId).findAny();
		if (existingReservation.isPresent()) {
			ItemReservation reservation = existingReservation.get();
			reservation.setAmount(reservation.getAmount() + 1);
		} else {
			order.getItemReservations().add(createItemReservation(itemId));
		}
		return orderDao.save(order);
	}

	@Override
	@Nonnull
	@Transactional
	public ItemOrder updateOrder(@Nonnull ItemOrder order) {
		return orderDao.save(order);
	}

}

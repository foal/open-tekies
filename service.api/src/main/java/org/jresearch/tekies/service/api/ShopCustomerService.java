package org.jresearch.tekies.service.api;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.domain.ItemOrder;

/**
 * Manager the orders from customer POV
 */
public interface ShopCustomerService {

	/**
	 * Try to find an item by item's id
	 *
	 * @param itemId
	 *            - id of the item to find
	 * @return result of the search
	 */
	@Nonnull
	public Optional<Item> getItem(long itemId);

	/**
	 * Gets the item list
	 *
	 * @return result of the search
	 */
	@Nonnull
	public List<Item> getItems();

	/**
	 * Try to find an order by order's id
	 *
	 * @param orderId
	 *            - id of the order to find
	 * @return result of the search
	 */
	@Nonnull
	public Optional<ItemOrder> getOrder(long orderId);

	/**
	 *
	 * Try to find the orders created by specific customer
	 *
	 * @param customerId
	 *            - id of the customer to find the orders
	 * @return result of the search
	 * @throws NoCustomer
	 *             - if the customer with given id not found
	 */
	@Nonnull
	public List<ItemOrder> getCustomerOrders(long customerId) throws NoCustomer;

	/**
	 * Add the item to the new order
	 *
	 * @param itemId
	 *            - id of the item to add
	 * @return - created order with given item
	 * @throws NoItem
	 *             - if the item with given id not found
	 * @throws NoCustomer
	 *             - if the customer with given id not found
	 */
	@Nonnull
	public ItemOrder createOrder(long customerId, long itemId) throws NoItem, NoCustomer;

	/**
	 * Add the item to existing order
	 *
	 * @param orderId
	 *            - id of the order to update
	 * @param itemId
	 *            - id of the item to add
	 * @return - updated order
	 * @throws NoOrder
	 *             - if the order with given id not found
	 */
	@Nonnull
	public ItemOrder addToOrder(long orderId, long itemId) throws NoItem, NoOrder;

	/**
	 * Save the changed order
	 *
	 * @param order
	 *            - order to save
	 * @return - updated order
	 */
	@Nonnull
	public ItemOrder updateOrder(@Nonnull ItemOrder order);

}

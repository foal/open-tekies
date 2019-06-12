package org.jresearch.tekies.rest.api;

import java.util.List;

import org.jresearch.tekies.domain.Item;
import org.jresearch.tekies.domain.ItemOrder;
import org.jresearch.tekies.service.api.NoCustomer;
import org.jresearch.tekies.service.api.NoItem;
import org.jresearch.tekies.service.api.NoOrder;
import org.jresearch.tekies.service.api.ShopCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@SuppressWarnings("nls")
@RestController
@RequestMapping("/shop")
public class ShopCustomerController {

	@Autowired
	private ShopCustomerService shopCustomerService;

	@GetMapping("/item/{itemId}")
	@ApiOperation("Get item")
	@ApiResponses({ @ApiResponse(code = 200, message = "Found"), @ApiResponse(code = 404, message = "Not found") })
	public Item getItem(@PathVariable long itemId) {
		return shopCustomerService.getItem(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/items")
	@ApiOperation("Get all items")
	public List<Item> getItems() {
		return shopCustomerService.getItems();
	}

	@GetMapping("/order/{orderId}")
	@ApiOperation("Get order")
	@ApiResponses({ @ApiResponse(code = 200, message = "Found"), @ApiResponse(code = 404, message = "Not found") })
	public ItemOrder getOrder(@PathVariable long orderId) {
		return shopCustomerService.getOrder(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/customerOrders/{customerId}")
	@ApiOperation("Get customer orders")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 404, message = "Customer not found") })
	public List<ItemOrder> getCustomerOrders(@PathVariable long customerId) {
		try {
			return shopCustomerService.getCustomerOrders(customerId);
		} catch (@SuppressWarnings("unused") NoCustomer e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer");
		}
	}

	@PostMapping("/create/{customerId}/{itemId}")
	@ApiOperation("Create customer order")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 404, message = "Customer or item not found") })
	public ItemOrder createOrder(@PathVariable long customerId, @PathVariable long itemId) {
		try {
			return shopCustomerService.createOrder(customerId, itemId);
		} catch (@SuppressWarnings("unused") NoItem e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item");
		} catch (@SuppressWarnings("unused") NoCustomer e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer");
		}
	}

	@PostMapping("/add/{orderId}/{itemId}")
	@ApiOperation("Add item to order")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 404, message = "Order or item not found") })
	public ItemOrder addToOrder(@PathVariable long orderId, @PathVariable long itemId) {
		try {
			return shopCustomerService.addToOrder(orderId, itemId);
		} catch (@SuppressWarnings("unused") NoItem e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item");
		} catch (@SuppressWarnings("unused") NoOrder e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order");
		}
	}

	@PostMapping("/update")
	@ApiOperation("Update order")
	public ItemOrder updateOrder(@RequestBody ItemOrder in) {
		return shopCustomerService.updateOrder(in);
	}

}

package org.jresearch.tekies.rest.api;

import java.util.List;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.DuplicateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	@ApiOperation("Create customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 406, message = "Duplicate email") })
	public Customer addCustomer(@RequestBody ImmutableNewCustomerDto in) {
		try {
			return customerService.addCustomer(in.firstName().orElse(null), in.lastName().orElse(null), in.phone().orElse(null), in.email(), in.birthday().orElse(null));
		} catch (@SuppressWarnings("unused") DuplicateEmail e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicate email");
		}
	}

	@GetMapping("/{customerId}")
	@ApiOperation("Get customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Found"), @ApiResponse(code = 404, message = "Not found") })
	public Customer getCustomer(@PathVariable long customerId) {
		return customerService.getCustomer(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/update")
	@ApiOperation("Update customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 406, message = "Duplicate email") })
	public Customer updateCustomer(@RequestBody Customer in) {
		try {
			return customerService.updateCustomer(in);
		} catch (@SuppressWarnings("unused") DuplicateEmail e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicate email");
		}
	}

	@DeleteMapping(("/{customerId}"))
	@ApiOperation("Remove customer")
	public void removeCustomer(@PathVariable long customerId) {
		customerService.removeCustomer(customerId);
	}

	@GetMapping("/all")
	@ApiOperation("Get customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers(Pageable.unpaged()).getContent();
	}

}

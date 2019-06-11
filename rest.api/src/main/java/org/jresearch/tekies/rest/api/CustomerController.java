package org.jresearch.tekies.rest.api;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.DuplicateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	@ApiOperation("Create customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 406, message = "Duplicate email") })
	public Customer addCustomer(@RequestBody ImmutableNewCustomerDto in) {
		try {
			return customerService.addCustomer(in.firstName().orElse(null), in.lastName().orElse(null), in.phone().orElse(null), in.email(), in.birthday().orElse(null));
		} catch (DuplicateEmail e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicate email");
		}
	}

}

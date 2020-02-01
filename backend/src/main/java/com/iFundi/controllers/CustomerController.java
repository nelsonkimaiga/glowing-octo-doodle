package com.iFundi.controllers;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.iFundi.handlers.CustomResponse;
import com.iFundi.handlers.UserResponse;
import com.iFundi.models.Customer;
import com.iFundi.services.CustomerService;

@Controller
//@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class CustomerController {

	@Autowired
	private Environment env;

	@Autowired
	private CustomerService customerService;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping(value = "/customers")
	public ResponseEntity<?> getProfiles() {
		try {
			List<Customer> customers = customerService.getAllCustomers();

			if (customers.isEmpty()) {
				return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 200, true, "no customers found",
						new HashSet<>(customers)), HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "found customers", new HashSet<>(customers)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/addcustomers")
	public ResponseEntity<?> addAllCustomers(@RequestBody Customer customer) throws Exception {
		Customer customr = customerService.addCustomer(customer);
		if (customr == null) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to add user"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "Customer records added succesfully"),
				HttpStatus.OK);
	}

	@PostMapping(path = "/customer/update")
	public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer) {
		System.out.println(customer);
		try {
			customerService.updateCustomerDetails(customer);
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "Customer records updated succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to update user"),
					HttpStatus.OK);
		}
	}

}

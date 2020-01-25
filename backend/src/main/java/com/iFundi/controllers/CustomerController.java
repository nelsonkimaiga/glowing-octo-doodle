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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.iFundi.handlers.CustomResponse;
import com.iFundi.models.ApiResponse;
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

	@ResponseBody
	@RequestMapping(path = "/addcustomers", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity addAllCustomers(@RequestBody Customer customerslist) {

		try {
			String customers = gson.toJson(customerslist, Customer[].class);
			logger.info(gson.toJson(customers, Customer[].class));
//			Customer customerslist = (Customer) Arrays.asList(customers);
			customerService.addCustomer(customerslist);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "customers saved successfully")));
		} catch (Exception e) {
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, "error adding customers")));
		}
	}

	@ResponseBody
	@PostMapping(value = "/customer/update")
	public ResponseEntity<?> updCustomer(@PathVariable(value = "id") Long id, @RequestBody Customer customer) {
		try {
			Customer cust = customerService.findById(id);
			if (cust.equals(null)) {
				return new ResponseEntity<>(
						new CustomResponse(CustomResponse.APIV, 201, false, "No customer with specified id"),
						HttpStatus.OK);
			}

			customerService.updCustomers(id);
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 200, true, "Updated succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 201, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}

}

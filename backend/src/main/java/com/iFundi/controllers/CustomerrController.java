package com.iFundi.controllers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.iFundi.config.ResourceConfig;
import com.iFundi.models.ApiResponse;
import com.iFundi.models.Customerr;
import com.iFundi.repositories.CustomerrRepository;

/**
 * Created by CLLSDJACKT013 on 2/14/2019.
 */
@RestController
@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class CustomerrController {
	@Autowired
	private CustomerrRepository customerrRepository;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(CustomerrController.class);

	@ResponseBody
	@RequestMapping(path = "/addcustomer", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity addCustomer(@RequestBody String request) {
		logger.info("[-----ADD CUSTOMER REQUEST]---" + request);
		try {
			Customerr customerr = gson.fromJson(request, Customerr.class);
			customerrRepository.save(customerr);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "customer saved successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}
	}

	@ResponseBody
	@RequestMapping(path = "/addcustomers", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity addAllCustomers(@RequestBody String request) {
		logger.info("--------[ADD CUSTOMERS' LIST REQUEST]------------");
		try {
			Customerr[] customers = gson.fromJson(request, Customerr[].class);
			logger.info(gson.toJson(customers, Customerr[].class));
			List<Customerr> customerslist = Arrays.asList(customers);
			customerrRepository.saveAll(customerslist);
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, "customers enrolled successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, "error adding customers")));
		}
	}

	@ResponseBody
	@RequestMapping(path = "/fetchall", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity fetchAllCustomers() {
		logger.info("---------------------[FETCH ALL CUSTOMERS INIT...]-----------------------");
		try {
			List<Customerr> customerrs = customerrRepository.findAll();
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, customerrs, "customers fetch success")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, "error fetching customers")));
		}
	}

	@ResponseBody
	@RequestMapping(path = "/getcustomerbyafisid", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity getCustomerByAfisId(@RequestBody String request) {
		logger.info("---------------------[FETCH CUSTOMER BY AFISID  INIT...]-----------------------");
		try {
			Customerr customerr = gson.fromJson(request, Customerr.class);
			Customerr foundCustomer = customerrRepository.fetchCustomerByAfisid(customerr.getAfisid());
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, "customer retrieved successfully", foundCustomer)));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}
	}

	@ResponseBody
	@RequestMapping(path = "/fetchactivecustomers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getActiveCustomers() {
		logger.info("---------------------[FETCH ACTIVE CUSTOMERS   INIT...]-----------------------");
		try {
			List<Customerr> customerrs = customerrRepository.fetchAllActiveCustomers();
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, customerrs, "active customers")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@ResponseBody
	@RequestMapping(path = "/fetchinactivecustomers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getInactiveCustomers() {
		logger.info("---------------------[FETCH INACTIVE CUSTOMERS   INIT...]-----------------------");
		try {
			List<Customerr> inactivecustomers = customerrRepository.fetchAllInactiveCustomers();
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, inactivecustomers, "inactive customers")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@ResponseBody
	@RequestMapping(path = "/deletecustomer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteCustomer(@RequestBody String customerrequest) {
		logger.info("---------------------[DELETE  CUSTOMER   INIT...]-----------------------");
		try {
			Customerr customerr = gson.fromJson(customerrequest, Customerr.class);
			customerrRepository.deleteCustomerrByAfisid(customerr.getAfisid());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "customer deleted successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@ResponseBody
	@RequestMapping(path = "/deletecustomers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteCustomers(@RequestBody String customerrequest) {
		logger.info("---------------------[DELETE  CUSTOMERS   INIT...]-----------------------");
		try {
			Customerr[] customerrs = gson.fromJson(customerrequest, Customerr[].class);
			List<Customerr> customers = Arrays.asList(customerrs);
			customers.forEach(customerr -> customerrRepository.deleteCustomerrByAfisid(customerr.getAfisid()));
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, "customers deleted successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}
}

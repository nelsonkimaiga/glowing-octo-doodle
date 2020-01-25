package com.iFundi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.iFundi.models.Customer;
import com.iFundi.repositories.CustomerRepository;

@Service
public class CustomerService {

	// private static String UPLOAD_ROOT = "compas_uploads";
	@Autowired
	private CustomerRepository customerRepository;

//	 @Autowired
//	 private CustomerBioRepository customerbio;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, ResourceLoader resourceLoader) {
		super();
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer findById(Long id) {
		return customerRepository.getCustomerById(id);
	}

	public Customer getCustomer(String idNumber) {
		// return cutomerRepository.findByAccountNumber(accountNumber);
		return customerRepository.getCustomerByIdNo(idNumber);
	}

	public int updCustomers(Long id) {
		return customerRepository.updCustomers(id);
	}

	public Customer addCustomer(Customer customerslist) {
		// TODO Auto-generated method stub
		return customerRepository.save(customerslist);
	}

}

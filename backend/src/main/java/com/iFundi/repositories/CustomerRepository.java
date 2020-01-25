package com.iFundi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iFundi.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("select u from Customer u")
	@Override
	List<Customer> findAll();

	@Query("select count(u) from Customer u")
	int countEnrolledCustomers();

	@Query("SELECT u from Customer u where u.id=?1")
	Customer getCustomerById(Long id);

	@Query("SELECT u from Customer u where u.idNumber=?1")
	Customer getCustomerByIdNo(String idNumber);

}

package com.iFundi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("select u from Customer u")
	@Override
	List<Customer> findAll();

	@Query("select count(u) from Customer u")
	int countEnrolledCustomers();

	@Query("SELECT u from Customer u where u.id= :id")
	Customer getCustomerById(@Param("id") Long id);

	@Query("SELECT u from Customer u where u.idNumber= :id_number")
	Customer getCustomerByIdNo(@Param("id_number") String idNumber);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE customer set active=?1, email=?2 full_name=?3, id_number=?4, postal_town=?5, profile_pic=?6, sub_location=?7 WHERE id =?8")
	void updateCustomerDetails(boolean active, String emailAddress, String fullName, String idNumber, String postalTown,
			String phoneNumber, String subLocation, String profilePic);

}

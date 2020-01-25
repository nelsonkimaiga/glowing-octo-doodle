package com.iFundi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	JobSeeker findByIdNumber(String idNumber);

	@Query(nativeQuery = true, value = "SELECT * FROM job_seeker j WHERE j.location=?1")
	List<JobSeeker> findByLocation(String location);

	@Query(nativeQuery = true, value = "SELECT * FROM job_seeker j WHERE j.profession=?1")
	List<JobSeeker> findByProfession(String profession);

	@Query(nativeQuery = true, value = "SELECT * FROM job_seeker j WHERE j.estimate_price=?1")
	List<JobSeeker> findByEstimatePrice(String estimatePrice);

	@Modifying
	@Transactional
	@Query(value = "UPDATE job_seeker set email= :emailAddress, full_name= :fullName, id_number= :idNumber, postal_town= :postalTown, profession_level= :professionLevel, profile_pic= :profilePic WHERE id = :id", nativeQuery = true)
	int updContractor(@Param("id") Long id);

}

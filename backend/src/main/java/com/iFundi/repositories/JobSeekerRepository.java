package com.iFundi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iFundi.models.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	JobSeeker findByIdNumber(String idNumber);

	@Query("SELECT j FROM JobSeeker j WHERE j.location=?1")
	JobSeeker findByLocation(String location);

	@Query("SELECT j FROM JobSeeker j WHERE j.profession=?1")
	JobSeeker findByProfession(String profession);

	@Query("SELECT j FROM JobSeeker j WHERE j.estimatePrice=?1")
	JobSeeker findByEstimatePrice(String estimatePrice);

	@Modifying
	@Transactional
	@Query(value = "UPDATE job_seeker set email=?1, full_name=?2, id_number=?3, postal_town=?4, profession_level=?5, estimate_price=?6, location=?7, profession=?8, profile_pic= ?9, other_skills=?10, professional_boards=?11 WHERE id =?12", nativeQuery = true)
	void updContractor(JobSeeker jobseeker);

	@Query("select u from JobSeeker u")
	@Override
	List<JobSeeker> findAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE job_seeker set rating=?1 WHERE id=?2", nativeQuery = true)
	void rateContractor(JobSeeker jobseeker);

	@Modifying
	@Transactional
	@Query(value = "UPDATE job_seeker set certificate_of_good_conduct=?1, profile_pic= ?2, other_skills=?3, professional_boards=?4 WHERE id =?4", nativeQuery = true)
	void addProfilePicture(JobSeeker jobseeker);

}

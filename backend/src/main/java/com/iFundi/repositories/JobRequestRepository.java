package com.iFundi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iFundi.models.JobRequest;;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {

	@Query("select jobRequest from JobRequest jobRequest where jobRequest.jobLocation= :jobLocation")
	JobRequest findByLocation(@Param(value = "jobLocation") String jobLocation);

	@Query("select jobRequest from JobRequest jobRequest where jobRequest.ApplicantName= :ApplicantName")
	JobRequest findByApplicant(String ApplicantName);

}

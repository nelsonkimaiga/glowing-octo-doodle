package com.iFundi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iFundi.models.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	JobSeeker findByIdNumber(String idNumber);

}

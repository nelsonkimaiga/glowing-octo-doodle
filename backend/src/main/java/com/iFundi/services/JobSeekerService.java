package com.iFundi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.models.JobSeeker;
import com.iFundi.repositories.JobSeekerRepository;

@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	public List<JobSeeker> getJobSeekers() {
		return jobSeekerRepository.findAll();
	}

	public Optional<JobSeeker> findById(Long id) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.findById(id);
	}

	public JobSeeker findByIdNumber(String idNumber) {
		return jobSeekerRepository.findByIdNumber(idNumber);
	}

}

package com.iFundi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.models.JobRequest;
import com.iFundi.repositories.JobRequestRepository;

@Service
public class JobRequestService {
	@Autowired
	private JobRequestRepository jobRequestRepository;

	public List<JobRequest> getRequests() {
		return jobRequestRepository.findAll();
	}

	// find requests by id
	public Optional<JobRequest> findById(Long id) {

		return jobRequestRepository.findById(id);
	}

	// find requests by location
	public JobRequest findByLocation(String jobLocation) {

		return jobRequestRepository.findByLocation(jobLocation);
	}

	// find job request by applicant
	public JobRequest findByApplicant(String ApplicantName) {
		return jobRequestRepository.findByApplicant(ApplicantName);
	}

}

package com.iFundi.services;

import java.util.List;

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

//	public JobSeeker findById(Long id) {
//		// TODO Auto-generated method stub
//		return jobSeekerRepository.findById(id);
//	}

	// new end-points
	public JobSeeker findByLocation(String location) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.findByLocation(location);
	}

	// find by profession
	public JobSeeker findByProfession(String profession) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.findByProfession(profession);
	}

	// find by estimate price
	public JobSeeker findByEstimatePrice(String estimatePrice) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.findByEstimatePrice(estimatePrice);
	}

	public JobSeeker findByIdNumber(String idNumber) {
		return jobSeekerRepository.findByIdNumber(idNumber);
	}

	public JobSeeker addContractor(JobSeeker contractor) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.save(contractor);
	}

	public void updContractor(JobSeeker jobseeker) {
		jobSeekerRepository.updContractor(jobseeker);
	}

	public void rateContractor(JobSeeker jobseeker) {
		jobSeekerRepository.rateContractor(jobseeker);
	}

}

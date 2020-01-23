package com.iFundi.controllers;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.iFundi.handlers.CustomResponse;
import com.iFundi.handlers.UserResponse;
import com.iFundi.models.ApiResponse;
import com.iFundi.models.ApiSecurity;
import com.iFundi.models.JobSeeker;
import com.iFundi.repositories.JobSeekerRepository;
import com.iFundi.services.JobSeekerService;

@RestController
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;
	private JobSeekerRepository jobSeekerRepository;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(JobSeekerController.class);

	@GetMapping(value = "/jobseekers")
	public ResponseEntity<?> getJobSeekers() throws Exception {
		System.out.println("Job Seekers: ####");

		List<JobSeeker> jobseekers = jobSeekerRepository.findAll();

		if (jobseekers.isEmpty()) {
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 404, false, "no contractors found"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "found users", new HashSet<>(jobseekers)),
				HttpStatus.OK);

	}

	@ResponseBody
	@RequestMapping(path = "/getJobSeekersById", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findJobSeekersById(@RequestBody String idNumber) {
		logger.info("---------------------[FETCH CONTRACTOR INIT...]-----------------------");
		try {
			JobSeeker jobseeker = jobSeekerRepository.findByIdNumber(idNumber);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success", jobseeker)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@PostMapping(value = "/contractors/save")
	public ResponseEntity<?> addContractor(@RequestBody JobSeeker contractor) throws Exception {
		logger.info("---------------------[SAVING CONTRACTOR...]-----------------------");
		JobSeeker jobseeker = jobSeekerService.findByIdNumber(contractor.getIdNumber());
		if (jobseeker != null && jobseeker.getId() == 0) {
			return new ResponseEntity<>(new UserResponse(UserResponse.APIV, 203, false, "contractor already exists!"),
					HttpStatus.OK);
		}
		JobSeeker jobseekr = jobSeekerService.addContractor(contractor);

		if (jobseekr == null) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to add contractor"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, true, "Contractor records updated"),
				HttpStatus.OK);
	}

	// find jobseeker by location
	@ResponseBody
	@RequestMapping(path = "/getContractorByLocation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findByLocation(@RequestBody String location) {
		logger.info("---------------------[FETCH CONTRACTOR BY LOCATION INIT...]-----------------------");
		try {
			JobSeeker jobSeeker = gson.fromJson(location, JobSeeker.class);
			List<JobSeeker> jobseekerlocation = jobSeekerRepository.findByLocation(location);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success",
					(ApiSecurity) jobSeekerRepository.findByLocation(jobSeeker.getLocation()))));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	// find by profession
	@ResponseBody
	@RequestMapping(path = "/getContractorByProfession", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findByProfession(String profession) {
		logger.info("---------------------[FETCH CONTRACTOR BY PROFESSION INIT...]-----------------------");
		try {
			JobSeeker jobSeeker = gson.fromJson(profession, JobSeeker.class);
			List<JobSeeker> jobseekerprofession = jobSeekerRepository.findByProfession(profession);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success",
					(ApiSecurity) jobSeekerRepository.findByLocation(jobSeeker.getProfession()))));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	// find by estimate price
	@ResponseBody
	@RequestMapping(path = "/getContractorByEstimatePrice", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findByEstimatePrice(String estimatePrice) {
		logger.info("---------------------[FETCH CONTRACTOR BY ESTIMATE PRICE INIT...]-----------------------");
		try {
			JobSeeker jobSeekerEstimatePrice = gson.fromJson(estimatePrice, JobSeeker.class);
			List<JobSeeker> jobseekerestimate = jobSeekerRepository.findByEstimatePrice(estimatePrice);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success",
					(ApiSecurity) jobSeekerRepository.findByLocation(jobSeekerEstimatePrice.getEstimatePrice()))));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

}

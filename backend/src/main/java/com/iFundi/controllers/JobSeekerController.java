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
	public ResponseEntity<?> getJobSeekers() {
		try {
			System.out.println("Job Seekers: ####");
			List<JobSeeker> jobseekers = jobSeekerService.getJobSeekers();

			if (jobseekers.isEmpty()) {
				return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 200, true, "no customers found",
						new HashSet<>(jobseekers)), HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "found customers", new HashSet<>(jobseekers)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, false, "Server error processing request"),
					HttpStatus.OK);
		}
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
	@PostMapping(value = "/getContractorByLocation")
	public ResponseEntity<?> show(@RequestBody JobSeeker jobseeker) {
		try {
			JobSeeker jobseekr = jobSeekerService.findByLocation(jobseeker.getLocation());
			if (jobseekr != null) {
				return new ResponseEntity<>(
						new CustomResponse(CustomResponse.APIV, 200, true, "Contractors by this location found"),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 201, false, "no contractor with specified parameter found"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}

	// find by profession
	@PostMapping(value = "/getContractorByProfession")
	public ResponseEntity<?> showContractors(@RequestBody JobSeeker jobseeker) {
		try {
			JobSeeker jobseekr = jobSeekerService.findByProfession(jobseeker.getProfession());
			if (jobseekr != null) {
				return new ResponseEntity<>(
						new CustomResponse(CustomResponse.APIV, 200, true, "Contractors by this profession found"),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 201, false, "no contractor with specified parameter found"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}

	// find by estimate price
	@PostMapping(value = "/getContractorByPrice")
	public ResponseEntity<?> display(@RequestBody JobSeeker jobseeker) {
		try {
			JobSeeker jobseekr = jobSeekerService.findByProfession(jobseeker.getEstimatePrice());
			if (jobseekr != null) {
				return new ResponseEntity<>(
						new CustomResponse(CustomResponse.APIV, 200, true, "Contractors by this price found"),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 201, false, "no contractor with specified parameter found"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}

	@ResponseBody
	@PostMapping(value = "/contractor/update")
	public ResponseEntity<?> updateContractorDetails(@RequestBody JobSeeker jobseeker) {
		System.out.println(jobseeker);
		try {
			jobSeekerService.updContractor(jobseeker);
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "Contractor records updated succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(UserResponse.APIV, 409, false, "failed to update contractor"), HttpStatus.OK);
		}
	}

	@ResponseBody
	@PostMapping(value = "/contractor/rate")
	public ResponseEntity<?> rateFundi(@RequestBody JobSeeker jobseeker) {
		System.out.println(jobseeker);
		try {
			jobSeekerService.rateContractor(jobseeker);
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "Contractor rating updated succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new CustomResponse(UserResponse.APIV, 409, false, "failed to rate contractor"),
					HttpStatus.OK);
		}
	}

	@ResponseBody
	@PostMapping(value = "/contractor/updatefields")
	public ResponseEntity<?> updateContractorInfo(@RequestBody JobSeeker contractor) {
		System.out.println(contractor);
		try {
			jobSeekerService.addProfilePicture(contractor);
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 200, true, "Contractor fields updated succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(UserResponse.APIV, 409, false, "failed to update contractor"), HttpStatus.OK);
		}
	}
}

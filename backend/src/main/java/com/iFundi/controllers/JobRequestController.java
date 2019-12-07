package com.iFundi.controllers;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.iFundi.config.ResourceConfig;
import com.iFundi.handlers.CustomResponse;
import com.iFundi.models.ApiResponse;
import com.iFundi.models.JobRequest;
import com.iFundi.repositories.JobRequestRepository;
import com.iFundi.services.JobRequestService;

@RestController
@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class JobRequestController {
	@Autowired
	private JobRequestService jobRequestService;
	private JobRequestRepository jobRequestRepository;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(JobRequestController.class);

	@GetMapping(value = "/jobrequests")
	public ResponseEntity<?> getJobRequests() throws Exception {
		System.out.println("Job requests: ####");

		List<JobRequest> jobrequests = jobRequestService.getRequests();

		if (jobrequests.isEmpty()) {
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 404, false, "no job request found"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "found job request", new HashSet<>(jobrequests)),
				HttpStatus.OK);

	}

	// fetch by location
	@ResponseBody
	@RequestMapping(path = "/getJobRequestsByLocation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findJobRequestByLocation(@RequestBody String jobLocation) {
		logger.info("---------------------[FETCH JOB REQUEST INIT...]-----------------------");
		try {
			JobRequest jobrequest = jobRequestRepository.findByLocation(jobLocation);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success", jobrequest)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	// fetch requests by applicant

	@ResponseBody
	@RequestMapping(path = "/getJobRequestsByApplicant", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findJobRequestsByApplicant(@RequestBody String applicantName) {
		logger.info("---------------------[FETCH JOB REQUEST APPLICANTS INIT...]-----------------------");
		try {
			JobRequest jobrequest = jobRequestRepository.findByApplicant(applicantName);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success", jobrequest)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

}

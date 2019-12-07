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
import com.iFundi.models.JobSeeker;
import com.iFundi.repositories.JobSeekerRepository;
import com.iFundi.services.JobSeekerService;

@RestController
@RequestMapping(value = ResourceConfig.iFundi_API_v1)
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;
	private JobSeekerRepository jobSeekerRepository;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(JobSeekerController.class);

	@GetMapping(value = "/jobseekers")
	public ResponseEntity<?> getJobSeekers() throws Exception {
		System.out.println("Job Seekers: ####");

		List<JobSeeker> jobseekers = jobSeekerService.getJobSeekers();

		if (jobseekers.isEmpty()) {
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 404, false, "no job seekers found"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(
				new CustomResponse(CustomResponse.APIV, 200, true, "found users", new HashSet<>(jobseekers)),
				HttpStatus.OK);

	}

	@ResponseBody
	@RequestMapping(path = "/getJobSeekersById", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findJobSeekersById(@RequestBody String idNumber) {
		logger.info("---------------------[FETCH JOB INIT...]-----------------------");
		try {
			JobSeeker jobseeker = jobSeekerRepository.findByIdNumber(idNumber);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success", jobseeker)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

}

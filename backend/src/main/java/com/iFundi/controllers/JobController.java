package com.iFundi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.iFundi.models.ApiResponse;
import com.iFundi.models.Job;
import com.iFundi.repositories.JobRepository;

/**
 * Created by CLLSDJACKT013 on 22/11/2019.
 */
@RestController
public class JobController {
	@Autowired
	private JobRepository jobRepository;
	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(JobController.class);

	@ResponseBody
	@RequestMapping(path = "/savejob", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity saveAccount(@RequestBody String request) {
		logger.info("---------------------[SAVE JOB INIT...]-----------------------");
		try {
			Job account = gson.fromJson(request, Job.class);
			logger.info(gson.toJson(account));
			jobRepository.save(account);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "job saved successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@RequestMapping(path = "/updatejob", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity updateJob(@RequestBody String account) {
		return ResponseEntity.status(201).body("ok...");
	}

	@ResponseBody
	@RequestMapping(path = "/getJobById", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity findJobsById(@RequestBody int Id) {
		logger.info("---------------------[FETCH JOB INIT...]-----------------------");
		try {
			Job job = jobRepository.findById(Id);
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "success", job)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}

	}

	@ResponseBody
	@RequestMapping(path = "/getalljobs", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity getAllAccounts() {
		logger.info("---------------------[FETCH ALL JOBS INIT...]-----------------------");
		try {
			List<Job> jobs = jobRepository.findAll();
			return ResponseEntity.status(201)
					.body(gson.toJson(new ApiResponse(true, "accounts retrieved successfully", jobs)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}
	}

	@ResponseBody
	@RequestMapping(path = "deletejob", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity deleteAccount(@RequestBody String jobrequest) {
		logger.info("---------------------[DELETE  JOB INIT...]-----------------------");
		try {
			Job job = gson.fromJson(jobrequest, Job.class);
			jobRepository.deleteJobById(job.getId());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(true, "account deleted successfully")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(201).body(gson.toJson(new ApiResponse(false, e.getMessage())));
		}
	}

}

package com.iFundi.controllers;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iFundi.handlers.CustomResponse;
import com.iFundi.handlers.UserResponse;
import com.iFundi.models.InsuranceRequests;
import com.iFundi.services.InsuranceRequestsService;

@RestController
public class InsuranceRequestsController {
	@Autowired
	private InsuranceRequestsService insuranceRequestsService;

	@PostMapping(value = "/insurance/request")
	public ResponseEntity<?> addInsuranceRequest(@RequestBody InsuranceRequests insuranceRequests) throws Exception {
		InsuranceRequests requests = insuranceRequestsService.addInsuranceRequest(insuranceRequests);

		if (requests == null) {
			return new ResponseEntity<>(
					new CustomResponse(UserResponse.APIV, 409, false, "failed to add insurance request"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 200, true, "Insurance records added"),
				HttpStatus.OK);
	}

	@GetMapping(value = "/insurance/fetchrequests")
	public ResponseEntity<?> findInsuranceRequests() {
		try {
			System.out.println("Insurance Requests: ####");
			List<InsuranceRequests> insuranceRequests = insuranceRequestsService.findInsuranceRequests();

			if (insuranceRequests.isEmpty()) {
				return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 201, true,
						"no insurance requests found", new HashSet<>(insuranceRequests)), HttpStatus.OK);
			}
			return new ResponseEntity<>(new CustomResponse(CustomResponse.APIV, 200, true, "found insurance requests",
					new HashSet<>(insuranceRequests)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new CustomResponse(CustomResponse.APIV, 500, false, "Server error processing request"),
					HttpStatus.OK);
		}
	}
}

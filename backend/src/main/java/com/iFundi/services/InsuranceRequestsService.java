package com.iFundi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.models.InsuranceRequests;
import com.iFundi.repositories.InsuranceRequestsRepository;

@Service
public class InsuranceRequestsService {

	@Autowired
	private InsuranceRequestsRepository insuranceRequestsRepository;

	public InsuranceRequests addInsuranceRequest(InsuranceRequests insuranceRequests) {
		// TODO Auto-generated method stub
		return insuranceRequestsRepository.save(insuranceRequests);
	}

	public List<InsuranceRequests> findInsuranceRequests() {
		return insuranceRequestsRepository.findAll();
	}
}

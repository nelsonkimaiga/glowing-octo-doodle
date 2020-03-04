package com.iFundi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iFundi.repositories.JobRepository;;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;

//	public String findImageByAccount(String account) {
//		return jobRepository.findImageByAccount(account);
//	}

}

package com.iFundi.models;

import java.util.List;

/**
 * Created by CLLSDJACKT013 on 1/29/2019.
 */
public class ApiResponse {
	private boolean status;
	private String message;
	private Job account;
	private List<Job> accounts;
	private Customerr customerr;
	private List<Customerr> customerrs;
	private ApiSecurity apiSecurity;
	private JobSeeker jobseeker;
	private JobRequest jobrequest;
	// default constructor

	public ApiResponse(boolean status, String message, ApiSecurity apiSecurity) {
		this.status = status;
		this.message = message;
		this.apiSecurity = apiSecurity;
	}

	public ApiResponse(boolean status, String message, Job account, ApiSecurity apiSecurity) {
		this.status = status;
		this.message = message;
		this.account = account;
	}

	public ApiResponse(boolean status, String message, Job account) {
		this.status = status;
		this.message = message;
		this.account = account;
	}

	public ApiResponse(boolean status, String message, List<Job> accounts) {
		this.status = status;
		this.message = message;
		this.accounts = accounts;
	}

	public ApiResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public ApiResponse(boolean status, String message, Customerr customerr) {
		this.status = status;
		this.message = message;
		this.customerr = customerr;
	}

	public ApiResponse(boolean status, List<Customerr> customerrs, String message) {
		this.status = status;
		this.message = message;
		this.customerrs = customerrs;
	}

	public ApiResponse(boolean status, String message, JobSeeker jobseeker) {
		// TODO Auto-generated constructor stub
		this.status = status;
		this.message = message;
		this.jobseeker = jobseeker;
	}

	public ApiResponse(boolean status, String message, JobRequest jobrequest) {
		// TODO Auto-generated constructor stub
		this.status = status;
		this.message = message;
		this.jobrequest = jobrequest;

	}

}

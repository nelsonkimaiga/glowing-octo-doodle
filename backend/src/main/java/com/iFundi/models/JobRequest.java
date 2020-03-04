package com.iFundi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_requests")
public class JobRequest extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "active")
	private boolean active = false;

	@Column(name = "date_posted")
	private String date_posted;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "applicant_name")
	private String ApplicantName;

	@Column(name = "location")
	private String jobLocation;

	@Column(name = "job_poster")
	private String jobPoster;

	public JobRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobRequest(boolean active, String date_posted, String jobDescription, String applicantName,
			String jobLocation, String jobPoster) {
		super();
		this.active = active;
		this.date_posted = date_posted;
		this.jobDescription = jobDescription;
		ApplicantName = applicantName;
		this.jobLocation = jobLocation;
		this.jobPoster = jobPoster;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getApplicantName() {
		return ApplicantName;
	}

	public void setApplicantName(String applicantName) {
		ApplicantName = applicantName;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobPoster() {
		return jobPoster;
	}

	public void setJobPoster(String jobPoster) {
		this.jobPoster = jobPoster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

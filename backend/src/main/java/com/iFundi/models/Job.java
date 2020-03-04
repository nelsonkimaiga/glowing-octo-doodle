package com.iFundi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Created by CLLSDJACKT013 on 2/6/2019.
 */
@Entity
@Table(name = "Job")
public class Job {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String jobName;
	private boolean activeStatus;
	private String CreatedBy;
	private String category;
	private String jobDescription;

	// default constructor
	public Job() {
	}

	// getters and setters here

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getCatergory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}

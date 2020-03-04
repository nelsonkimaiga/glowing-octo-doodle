package com.iFundi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Kimaiga
 *
 */

@Entity
@Table(name = "insurance_requests")
public class InsuranceRequests extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "id_number")
	private String idNumber;

	@Override
	public String toString() {
		return "InsuranceRequests [fullName=" + fullName + ", idNumber=" + idNumber + "]";
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

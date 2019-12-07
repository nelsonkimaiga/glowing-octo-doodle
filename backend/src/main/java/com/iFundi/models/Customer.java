package com.iFundi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Kimaiga
 *
 */

@Entity
@Table(name = "CustomerMaster")
public class Customer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "active")
	private boolean active = false;

	@Column(name = "dob")
	private String dateOfBirth;

	@Column(name = "email")
	private String emailAddress;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "id_number")
	private String idNumber;

	@Column(name = "postal_town")
	private String postalTown;

	@Column(name = "profilePic", columnDefinition = "varchar(max)")
	private String profilePic;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [active=" + active + ", dateOfBirth=" + dateOfBirth + ", emailAddress=" + emailAddress
				+ ", fullName=" + fullName + ", gender=" + gender + ", idNumber=" + idNumber + ", postalTown="
				+ postalTown + ", profilePic=" + profilePic + "]";
	}

	public Customer(boolean active, String dateOfBirth, String emailAddress, String fullName, String gender,
			String idNumber, String postalTown, String profilePic) {
		super();
		this.active = active;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
		this.fullName = fullName;
		this.gender = gender;
		this.idNumber = idNumber;
		this.postalTown = postalTown;
		this.profilePic = profilePic;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPostalTown() {
		return postalTown;
	}

	public void setPostalTown(String postalTown) {
		this.postalTown = postalTown;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

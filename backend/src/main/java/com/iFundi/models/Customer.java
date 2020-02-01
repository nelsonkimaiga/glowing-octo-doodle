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
@Table(name = "customer")
public class Customer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "active")
	private boolean active = false;

	@Column(name = "email")
	private String emailAddress;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "id_number")
	private String idNumber;

	@Column(name = "postal_town")
	private String postalTown;

	@Column(name = "sub_location")
	private String subLocation;

	@Column(name = "profilePic")
	private String profilePic;

	@Column(name = "phone_number")
	private String phoneNumber;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [active=" + active + ", emailAddress=" + emailAddress + ", fullName=" + fullName
				+ ", idNumber=" + idNumber + ", postalTown=" + postalTown + ", profilePic=" + profilePic
				+ ", subLocation= " + subLocation + ",  phoneNumber=" + phoneNumber + "]";
	}

	public Customer(boolean active, String dateOfBirth, String emailAddress, String fullName, String idNumber,
			String postalTown, String profilePic, String subLocation, String phoneNumber) {
		super();
		this.active = active;
		this.emailAddress = emailAddress;
		this.fullName = fullName;
		this.idNumber = idNumber;
		this.postalTown = postalTown;
		this.profilePic = profilePic;
		this.subLocation = subLocation;
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

}

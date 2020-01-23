package com.iFundi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_seeker")
public class JobSeeker extends BaseModel {

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

	@Column(name = "profession_level")
	private String professionLevel;

	@Column(name = "profession")
	private String profession;

	@Column(name = "certificate_of_good_conduct", columnDefinition = "LONGTEXT")
	private String certificate;

	@Column(name = "location")
	private String location;

	@Column(name = "estimatePrice")
	private String estimatePrice;

	@Override
	public String toString() {
		return "JobSeeker [active=" + active + ", dateOfBirth=" + dateOfBirth + ", emailAddress=" + emailAddress
				+ ", fullName=" + fullName + ", gender=" + gender + ", idNumber=" + idNumber + ", postalTown="
				+ postalTown + ", professionLevel=" + professionLevel + ", certificate=" + certificate + ", location="
				+ location + ", profession=" + profession + ", estimatePrice=" + estimatePrice + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JobSeeker() {
		super();
		// TODO Auto-generated constructor stub
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

//	public String getProfilePic() {
//		return certifcate;
//	}
//
//	public void setProfilePic(String profilePic) {
//		this.certifcate = profilePic;
//	}

	public String getProfessionLevel() {
		return professionLevel;
	}

	public void setProfessionLevel(String professionLevel) {
		this.professionLevel = professionLevel;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(String estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

}

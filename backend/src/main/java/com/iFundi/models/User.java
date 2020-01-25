package com.iFundi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Kimaiga
 *
 */

@Entity
@Table(name = "users")
public class User extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "email")
	private String email;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "location")
	private String location;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "user_role")
	private String userRole;

	@Column(name = "created_by", nullable = true)
	private int createdBy;

	@Column(name = "status", columnDefinition = "tinyint")
	private boolean status = false;

	@Column(name = "verified")
	private String approved;

	@Column(name = "verified_by", nullable = true)
	private int approvedBy;

	@Column(name = "verified_on")
	private Date approvedOn;

	public User() {
		super();
	}

	public User(String email, String fullName, String password, String phone, String username, int group, int createdBy,
			boolean status, String approved, int approvedBy, Date approvedOn, String location, String userRole) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.phone = phone;
		this.username = username;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.status = status;
		this.approved = approved;
		this.approvedBy = approvedBy;
		this.approvedOn = approvedOn;
		this.location = location;
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User{" + "aproved=" + approved + ", username=" + username + ", active=" + status + '}';
	}
}

package com.iFundi.models;

import javax.persistence.*;

@Entity
@Table(name="audit_logs")
public class AuditTrail extends BaseModel{
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="module")
	private String module;
	
	protected AuditTrail() {
		super();
	}
	
	public AuditTrail(int userId, String module) {
		super();
		this.userId = userId;
		this.module = module;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	
}

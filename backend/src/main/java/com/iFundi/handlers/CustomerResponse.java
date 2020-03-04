package com.iFundi.handlers;

import com.iFundi.models.Customer;

public class CustomerResponse {
	
	public static final String  APIV="1.0";
	private int respCode;
	private String respMessage;
	private boolean status;
	private String version;
	private Customer customer;
	
	public CustomerResponse(int respCode, String respMessage, boolean status, String version, Customer customer) {
		super();
		this.respCode = respCode;
		this.respMessage = respMessage;
		this.status = status;
		this.version = version;
		this.customer = customer;
	}
	
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public static String getApiv() {
		return APIV;
	}
	
}

package com.iFundi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by CLLSDJACKT013 on 3/4/2019.
 */
@Entity
public class ApiRequest {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String requestid;
    private String transid;
    private String accountNumber;
    private boolean status;

    //default constructor
    public ApiRequest(){

    }

    //constructor

    public ApiRequest(String requestid, String transid, String accountNumber, boolean status) {
        this.requestid = requestid;
        this.transid = transid;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    //generate getters and setters

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

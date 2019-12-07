package com.iFundi.models.extras;

import java.io.Serializable;
import java.util.Date;

public interface UsersToVerify extends Serializable{
	int getCounter();
	int getId();
	int getCreatedBy();
	String getFullName();
	String getGroupName();
	Date getCreatedAt(); 
}

package com.onlineretail.service;

import java.sql.Date;
import java.util.List;

import com.onlineretail.model.*;

public interface RegisterService {

	int AddRegister(String name, String password, String phNo, String gender,
                    String city, String country, Date regDate) throws Exception;

	boolean isDuplicateUserName(String userName) throws Exception;

	List<Registration> findAll();
	
	void deleteRegistration(int Id);

}

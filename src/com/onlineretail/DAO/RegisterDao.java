package com.onlineretail.DAO;

import java.util.*;

import com.onlineretail.model.*;

public interface RegisterDao {
	public int Save(Registration registration);

	public List<Registration> findAll();

	public void updateRegistration(Registration registration);

	boolean isDuplicateUserName(String userName);

	void deleteRegistration(int Id);
	
	int findByUserId(int userId);
}

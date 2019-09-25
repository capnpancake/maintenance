package com.onlineretail.service.impl;

import com.onlineretail.DAO.RegisterDao;
import com.onlineretail.DAO.jdbc.JdbcRegisterDao;
import com.onlineretail.app.DuplicateUsernameException;
import com.onlineretail.model.Registration;
import com.onlineretail.service.RegisterService;

import java.sql.Date;
import java.util.List;

public class RegisterServiceImpl implements RegisterService {

	private RegisterDao registerdao;

	public RegisterServiceImpl() {
		registerdao = new JdbcRegisterDao();
	}

	@Override
	public int AddRegister(String UserName, String Password, String PhNo,
			String Gender, String City, String Country, Date regDate)
			throws Exception {
		if (!isDuplicateUserName(UserName)) {

			Registration register = new Registration();
			register.setUsername(UserName);
			register.setPassword(Password);
			register.setPhnnumber(PhNo);
			register.setGender(Gender);
			register.setCity(City);
			register.setCountry(Country);
			register.setRegistereddate(regDate);

			return registerdao.Save(register);
		} else {
			throw new DuplicateUsernameException("Username already exists.");
		}

	}

	@Override
	public boolean isDuplicateUserName(String userName) throws Exception {
		boolean status = false;
		 if(registerdao.isDuplicateUserName(userName))
		 {	 
			 status = true;
			 throw new Exception("Registration Not Added");
		 }

		return status;
	}
	
	public List<Registration> findAll() {
		return registerdao.findAll();
	}

	@Override
	public void deleteRegistration(int Id) {
		registerdao.deleteRegistration(Id);
	}	

}

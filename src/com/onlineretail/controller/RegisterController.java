package com.onlineretail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

import com.onlineretail.model.Category;
import com.onlineretail.model.Registration;
import com.onlineretail.service.*;
import com.onlineretail.service.impl.*;

public class RegisterController {
	public RegisterService registerService;
	public Scanner scanner;

	public RegisterController() {
		scanner = new Scanner(System.in);
		registerService = new RegisterServiceImpl();
	}

	public String addRegister() throws Exception {
		try {
			System.out.println("Registration");
			System.out
					.println("----------------------------------------------------");
			String Name, Password, PhNo, Gender, City, Country;
			System.out.println("Enter UserName:  ");
			Name = scanner.nextLine();
			System.out.println("Enter Password:  ");
			Password = scanner.nextLine();
			System.out.println("Enter Phone Number:  ");
			PhNo = scanner.nextLine();
			System.out.println("Enter Gender:  ");
			Gender = scanner.nextLine();
			System.out.println("Enter City:  ");
			City = scanner.nextLine();
			System.out.println("Enter Country:  ");
			Country = scanner.nextLine();

			java.util.Date dt = new java.util.Date();
			Date regDate = new Date(dt.getYear(), dt.getMonth(), dt.getDate());

			if (registerService.AddRegister(Name, Password, PhNo, Gender, City,
					Country, regDate) == 1) {
				return "Registration Added";
			} else {
				return "Registration Details Not Added";
			}
		} catch (Exception e) {
			return "Registration Details Not Added";
		}
	}

	public void findAll() {
		List<Registration> registrations = registerService.findAll();
		System.out.println("ID \tName\t\tGender");
		for (Registration registration : registrations) {
			System.out.println(registration.getId() + "\t"
					+ registration.getUsername() + "\t\t"
					+ registration.getGender());
		}
	}

	public void deleteRegistration(int Id) {
		registerService.deleteRegistration(Id);
	}

}

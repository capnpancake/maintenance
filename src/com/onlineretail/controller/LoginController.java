package com.onlineretail.controller;

import java.util.Scanner;

import com.onlineretail.service.*;
import com.onlineretail.service.impl.LoginServiceImpl;

public class LoginController {
	public LoginService loginService;
	public Scanner scanner;

	public LoginController() {
		scanner = new Scanner(System.in);
		loginService = new LoginServiceImpl();
	}

	public int loginValidate() throws Exception {
		int status = 0;
		try {
			System.out.println("Login");
			System.out
					.println("----------------------------------------------------");
			String UserName, Password;
			System.out.println("Enter UserName:  ");
			UserName = scanner.nextLine();
			System.out.println("Enter Password:  ");
			Password = scanner.nextLine();

			if (UserName.equals("admin") && Password.equals("admin")) {
				status = 1;
			} else {
				if (loginService.loginValidate(UserName, Password) == 1) {
					status = 2;
				} else {
					status = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}

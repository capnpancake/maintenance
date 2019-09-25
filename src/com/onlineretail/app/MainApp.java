package com.onlineretail.app;

import com.onlineretail.DAO.RegisterDao;
import com.onlineretail.DAO.jdbc.JdbcRegisterDao;
import com.onlineretail.controller.CommentController;
import com.onlineretail.controller.LoginController;
import com.onlineretail.controller.RegisterController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

	static LoginController loginController;
	static RegisterController registerController;
	static CommentController commentController;
	static RegisterDao registerDao;

	static Scanner scanner;
	static int choice = 0;
	static String message = null;

	public static void main(String[] args) {
		int result = 0;
		try {
			initialize();
			System.out
					.println("************************************************************************");
			System.out.println("				Online Retail								");
			System.out
					.println("************************************************************************");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out
					.println("----------------------------------------------------");
			System.out.println("Enter the Choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				result = login();
				if (result == 1)
					adminOperation();
				else if (result == 2)
					userOperation();
				else
					System.out.println("Invalid UserName");
				break;
			case 2:
				message = registerController.addRegister();
				System.out.println(message);
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initialize() {
		scanner = new Scanner(System.in);
		loginController = new LoginController();
		registerController = new RegisterController();
		commentController = new CommentController();
		registerDao = new JdbcRegisterDao();
	}

	public static int login() throws Exception {
		return loginController.loginValidate();
	}

	public static void userOperation() {
		String ch = "y";
		int subChoice = 0;
		do {
			try {

				System.out.println("Main Menu");
				System.out
						.println("----------------------------------------------------");
				System.out.println("1. Comments ");
				System.out.println("\t1. Add Comments ");
				System.out.println("\t2. Back ");

				System.out.println("Enter the Choice for User Operation: ");
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					System.out
							.println("Enter the Choice for Comments Operation: ");
					subChoice = scanner.nextInt();

					switch (subChoice) {
					case 1:
						message = commentController.addComment();
						System.out.println(message);
						break;
					case 2:
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}

				scanner.nextLine();
				System.out.println("Do you want to continue Y/N");
				ch = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Choice");
				scanner.nextLine();
				System.out.println("Enter Valid Choice: ");
				ch = scanner.nextLine();
			} catch (Exception e1) {

			}
		} while (ch.equals("Y") || ch.equals("y"));
	}

	public static void adminOperation() {
		String ch = "y";
		int subChoice = 0;
		do {
			try {

				System.out.println("Main Menu");
				System.out
						.println("----------------------------------------------------");
				System.out.println("1. Comment");
				System.out.println("\t1. View Comment ");
				System.out.println("\t2. View Comment Based on Category Name");
				System.out.println("\t3. Add Comment Based on Category Name");
				System.out.println("\t4. Back ");
				System.out.println("2. Registration");
				System.out.println("\t1. Delete Registration ");
				System.out.println("\t2. Back ");

				System.out.println("Enter the Choice for Admin Operation: ");
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					System.out
							.println("Enter the Choice for Comment Operation: ");
					subChoice = scanner.nextInt();

					switch (subChoice) {
					case 1:
						commentController.findAll();
						scanner.nextLine();
						break;
					case 2:
						commentController.findCategoryAll();
						scanner.nextLine();
						String categoryName;
						int status;
						do {
							System.out.println("Enter Category Name:  ");
							categoryName = scanner.nextLine();
							status = commentController
									.findCommentDetails(categoryName);
						} while (status == 0);
						if (status == 1) {
							commentController.CommentDetails(categoryName);
						}
						break;
					case 3:
						scanner.nextLine();
						System.out.println(commentController.addComment());
						break;
					case 4:
						scanner.nextLine();
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
					break;
				case 2:
					System.out
							.println("Enter the Choice for Registration Operation: ");
					subChoice = scanner.nextInt();

					switch (subChoice) {
					case 1:
						registerController.findAll();
						int Id, status;
						scanner.nextLine();
						do {
							System.out.println("Enter User Id:  ");
							Id = scanner.nextInt();
							status = registerDao.findByUserId(Id);
						} while (status == 0);
						if (status == 1) {
							registerController.deleteRegistration(Id);
							scanner.nextLine();
						}
						break;
					case 2:
						scanner.nextLine();
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
					break;
				default:
					System.out.println("Invalid Choice");
					scanner.nextLine();
					break;
				}
				System.out.println("Do you want to continue Y/N");
				ch = scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Choice");
				scanner.nextLine();
				System.out.println("Enter Valid Choice: ");
				ch = scanner.nextLine();
			}
			catch(RuntimeException e2){
				System.out.println(e2.getMessage());
			}
			catch (Exception e1) {

			}
		} while (ch.equals("Y") || ch.equals("y"));

	}
}

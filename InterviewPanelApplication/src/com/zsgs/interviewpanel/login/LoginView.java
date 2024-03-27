package com.zsgs.interviewpanel.login;

import java.util.Scanner;

import com.zsgs.interviewpanel.interviewerView.InterviewerView;
import com.zsgs.interviewpanel.panelsetup.InterviewPanelSetupView;

public class LoginView {
	LoginModel loginModel;
	public LoginView()
	{
		loginModel=new LoginModel(this);
	}
	public void init()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Proceed to Login");
		while(true)
		{
			System.out.println("1-Receptionist 2-Interviewer 3.Exit");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				proceedLogin();
				break;
			case 2:
				new InterviewerView().init();
				break;
			case 3:
				return;
			default:
				System.out.println("Enter the Valid Option");
			}
		}
		
	}
	public void onLoginFailed(String alertText) {
		System.out.println(alertText);
		checkForLogin();
	}

	private void checkForLogin() {
		System.out.println("Do you want to Try Again \n Type Yes / No ");
		Scanner scanner=new Scanner(System.in);
		String choice=scanner.next();
		if(choice.equalsIgnoreCase("yes"))
		{
			proceedLogin();
		}
		else if(choice.equalsIgnoreCase("no"))
		{
			System.out.println("--------Thank You---------");
		}
		else
		{
			System.out.println("Invalid Choice, Please Enter Valid Choice.\n");
			checkForLogin();
		}
		
	}

	public void OnSuccessLogin()
	{
		System.out.println("Login Successfull ...");
		new InterviewPanelSetupView().panelInit();
	}
	private void proceedLogin()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the username");
		String userName = scanner.next();
		System.out.println("Enter the password");
		String password = scanner.next();

		loginModel.validateUser(userName, password);
	}
}

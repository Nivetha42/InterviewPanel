package com.zsgs.interviewpanel.interviewerView;

import java.util.Scanner;

import com.zsgs.interviewpanel.addselectedcandidates.AddSelectedCandidatesView;
import com.zsgs.interviewpanel.viewcandidate.ViewCandidateView;

public class InterviewerView {
	InterviewerModel interviewerModel;
	public InterviewerView()
	{
		interviewerModel=new InterviewerModel(this);
	}
	public void init()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the InterviewerId");
		int id=sc.nextInt();
		System.out.println("Enter the Password");
		String pass=sc.next();
		interviewerModel.validateCredentials(id,pass);
	}
	public void onSucessLogin() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Login Successful ...");
		while(true)
		{
		System.out.println("\n1.View Current Candidate Details\t2.List Candidates\t3.Finish Current Interview \t4.Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			interviewerModel.viewCandidateDetail();
			break;
		case 2:
			new ViewCandidateView().initView();
			break;
		case 3:
			if(interviewerModel.checkCandidate())
			{
			getFeedBack();
			}
			else
			{
				System.out.println("Interview is not scheduled.");
			}
			break;
		case 4:
			return;
		default:
			System.out.println("Enter Valid Option");
			
		}
		
		}
	}
	public void getFeedBack()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Provide Feedback for the candidate : ");
		String feedback=sc.nextLine();
		int candidateId=interviewerModel.finishInterview(feedback);
		System.out.println("Is Candidate Selected?(yes/no)");
		String ans=sc.nextLine();
		if(ans.equalsIgnoreCase("yes"))
		{
			new AddSelectedCandidatesView().initAddSelected(candidateId);
		}
		interviewerModel.removeCandidate();
		
		
	}
	public void alertText(String alertText)
	{
		System.out.println(alertText);
	}
	public void onFailedLogin() {
		System.out.println("Invalid Credentials");
	}
}

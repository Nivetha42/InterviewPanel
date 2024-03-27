package com.zsgs.interviewpanel.managecandidate;

import java.util.Scanner;

import com.zsgs.interviewpanel.model.Candidate;

public class ManageCandidateView {
	ManageCandidateModel manageCandidateModel;
	public ManageCandidateView()
	{
		 manageCandidateModel=new ManageCandidateModel(this);
	}
	public void init() {
		System.out.println("\nEnter Candidate Details: ");
		Scanner sc=new Scanner(System.in);
		Candidate candidate=new Candidate();
		System.out.println("Enter Candidate Id");
		candidate.setId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Candidate Name:");
		candidate.setName(sc.nextLine());
		System.out.println("Enter Email Id : ");
		candidate.setEmailId(sc.nextLine());
		System.out.println("Enter the Degree :");
		candidate.setDegree(sc.nextLine());
		System.out.println("Enter Candidate's Location : ");
		candidate.setLocation(sc.nextLine());
		manageCandidateModel.addNewCandidate(candidate);
	}
	public void showAlert(String alertText) {
		System.out.println(alertText);
	}

	public void showInterviewStatus()
	{
		manageCandidateModel.isFree();
	}
	public void schedule()
	{
		manageCandidateModel.scheduleInterview();
	}
	public void onCandidateAdded(Candidate candidate) {
		System.out.println("--------Candidate '" +candidate.getName()+"' Added Sucessfully ----------");
		checkForAddNewCandidate();
	}

	private void checkForAddNewCandidate() {
		System.out.println("Do you want to add more Candidates? \nType Yes/No");
		Scanner sc=new Scanner(System.in);
		String choice=sc.next();
		if(choice.equalsIgnoreCase("yes"))
		{
			init();
		}
		else if(choice.equalsIgnoreCase("no"))
		{
			System.out.println("-----------------------------------Thanks You------------------------------------- ");
		}else
		{
			System.out.println("\n Invalid Choice, Please Enter Valid Choice. \n");
			checkForAddNewCandidate();
		}
	}

	public void onCandidatekExist(Candidate candidate) {
		System.out.println("\n --------candidate '" +candidate.getName()+"'Already Exist ----------\n");
		checkForAddNewCandidate();
	}
	

}

package com.zsgs.interviewpanel.addselectedcandidates;

import java.util.Scanner;
import com.zsgs.interviewpanel.model.Candidate;

public class AddSelectedCandidatesView {
	AddSelectedCandidatesModel addSelectedCandidatesModel; 
	
	
	public AddSelectedCandidatesView() {
		 addSelectedCandidatesModel=new AddSelectedCandidatesModel(this);
	}

	public void initAddSelected(int candidateId)
	{
		addSelectedCandidatesModel.AddSelectedCandidates(candidateId);
	}
	public void viewFeedBack()
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Candidate Id 2.ViewAll 3.Exit");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter Candidate Id : ");
				addSelectedCandidatesModel.getFeedbackById(sc.nextInt());
				break;
			case 2:
				addSelectedCandidatesModel.getFeedback();
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid Option");
			}
		}
	}
	
	public void showAlert(String alertText) {
		System.out.println(alertText);
	}

	public void onCandidateAdded(int candidateId) {
		System.out.println("\n --------Candidate '" +candidateId+"' Added Sucessfully to Selected List----------\n");
	}
	public void viewFeedback(int cid,String name,String feedback)
	{
		System.out.println(cid+"\t\t"+name+"\t\t"+feedback);
	}

	public void onCandidatekNotExist(int candidateId) {
		System.out.println("\n -------- Candidate '" +candidateId+"' Not Registered  ----------\n");
	}
}

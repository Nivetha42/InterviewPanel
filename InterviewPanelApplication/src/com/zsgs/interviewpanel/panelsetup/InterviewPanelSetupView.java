package com.zsgs.interviewpanel.panelsetup;

import java.util.Scanner;

import com.zsgs.interviewpanel.InterviewPanel2024;
import com.zsgs.interviewpanel.addselectedcandidates.AddSelectedCandidatesView;
import com.zsgs.interviewpanel.login.LoginView;
import com.zsgs.interviewpanel.managecandidate.ManageCandidateView;
import com.zsgs.interviewpanel.model.Panel;
import com.zsgs.interviewpanel.viewcandidate.ViewCandidateView;
import com.zsgs.interviewpanel.viewselectedcandidate.DisplaySelectedCandidateView;

public class InterviewPanelSetupView {
	private InterviewPanelSetUpModel interviewPanelSetUpModel;
	public InterviewPanelSetupView()
	{
		interviewPanelSetUpModel=new InterviewPanelSetUpModel(this);
	}
	public void panelInit()
	{
		interviewPanelSetUpModel.startSetUp();
	}
	public void onInitiateSetUp() {
		Panel panel=new Panel();
		Scanner sc=new Scanner(System.in);
		System.out.println("\nEnter the Team Name : ");
		panel.setTeamName(sc.nextLine());
		System.out.println("Enter the Team Email id : ");
		panel.setEmailId(sc.nextLine());
		panel.setId(1);
		interviewPanelSetUpModel.createPanel(panel);
		
	}
	public void alertText(String alertText)
	{
		System.out.println(alertText);
		onInitiateSetUp();
	}
	public void onSetUpComplete(Panel panel) {
		System.out.println("\nPanel SetUp Completed");
		System.out.println("Team Name : "+panel.getTeamName());
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("\n1.Add Candidate \n2.View Candidates \n3.Check FreeSlot \n4.Schedule Interview for Candidate \n5.View Selected Candidates\n6.View Candidate's FeedBack \n7.LogOut \n0.Exit \nEnter Your Choice");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				new ManageCandidateView().init();
				break;
			case 2:
				new ViewCandidateView().initView();
				break;
			case 3:
				new ManageCandidateView().showInterviewStatus();
				break;
			case 4:
				new ManageCandidateView().schedule();
				break;
			case 5:
				new DisplaySelectedCandidateView().initView();
				break;
			case 6:
				new AddSelectedCandidatesView().viewFeedBack();
				break;
			case 7:
				System.out.println("----------------------------------------------You are Logged Out Successfully"+"-----------------------------------------------");
				new LoginView().init();
				return ;
			case 0:
				System.out.println("----------------------------------------Thank you for Using "+InterviewPanel2024.getInstance().getAppName()+"----------------------------------------");
				System.exit(0);
			default:
				System.out.println("Enter the valid Option");
			
			}
			
		}
		
	}
}

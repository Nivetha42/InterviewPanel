package com.zsgs.interviewpanel.managecandidate;

import java.util.Scanner;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Candidate;

public class ManageCandidateModel {
	ManageCandidateView manageCandidateView;
	public ManageCandidateModel(ManageCandidateView manageCandidateView) {
		this.manageCandidateView= manageCandidateView;
	}
	public void addNewCandidate(Candidate candidate) {
		if(PanelDatabase.getInstance().insertCandidate(candidate))
		{
			manageCandidateView.onCandidateAdded(candidate);
		}
		else
		{
			manageCandidateView.onCandidatekExist(candidate);
		}
		
	}
	public void scheduleInterview()
	{
		if(PanelDatabase.getInstance().getAllCandidates().size()!=0)
		{
		if(PanelDatabase.getInstance().scheduleInterview()==false)
		{
			manageCandidateView.showAlert("Interview Already Scheduled .");
		}
		else
		{
			
			Candidate c=PanelDatabase.getInstance().viewCurrentCandidate();
			manageCandidateView.showAlert("\nInterview Scheduled For the candidate '"+c.getName()+"' Candidate Id : "+c.getId());
			
		}
		}
		else
		{
			manageCandidateView.showAlert("Candidate List is Empty ....");
		}
	}
public void isFree()
{
	if(PanelDatabase.getInstance().isInterviewerFree())
	{
		manageCandidateView.showAlert("Interviewer is Free You can Schedule Interview ....");
	}
	else
	{
		manageCandidateView.showAlert("Interviewer is already conducting an Interview ....");
	}
}
	
}

package com.zsgs.interviewpanel.interviewerView;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Candidate;

public class InterviewerModel {
	InterviewerView interviewerView;
	public InterviewerModel(InterviewerView interviewerView) {
		 this.interviewerView=interviewerView;
	}
	
	public void validateCredentials(int id, String pass) {
		if(id==221)
		{
			if(pass.equals("interview"))
			{
				interviewerView.onSucessLogin();
				return;
			}
		}
		interviewerView.onFailedLogin();
		
		
	}
	public void viewCandidateDetail()
	{
		Candidate c=PanelDatabase.getInstance().viewCurrentCandidate();
		if(PanelDatabase.getInstance().isInterviewerFree()==false)
		{
		if(c!=null)
		{
			interviewerView.alertText("-------------------------------------------------");
			interviewerView.alertText("Candidate Id : "+c.getId());
			interviewerView.alertText("Candidate Name : "+c.getName());
			interviewerView.alertText("Candidate Email ID : "+c.getEmailId());
			interviewerView.alertText("Degree : "+c.getDegree());
			interviewerView.alertText("-------------------------------------------------");
			return;
		}
		}
	
			interviewerView.alertText("Interview is Not Scheduled");
	
	}
	public void removeCandidate()
	{
		if(PanelDatabase.getInstance().removeCandidate())
		{
			
			interviewerView.alertText("Interview Completed");
		}
	}

	public int finishInterview(String feedback) {
		int cid=PanelDatabase.getInstance().viewCurrentCandidate().getId();
		PanelDatabase.getInstance().getFeedBack(cid, feedback);
		return cid;
		
	}

	public boolean checkCandidate() {
		if(PanelDatabase.getInstance().isInterviewerFree()==true)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}

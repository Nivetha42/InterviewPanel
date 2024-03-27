package com.zsgs.interviewpanel.viewcandidate;

import java.util.ArrayDeque;
import java.util.List;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Candidate;




public class ViewCandidateModel {
	ViewCandidateView viewCandidateView;
	public ViewCandidateModel(ViewCandidateView viewCandidateView) {
		this.viewCandidateView=viewCandidateView;
	}

	public void getCandidateList() {
		ArrayDeque<Candidate> allCandidates=PanelDatabase.getInstance().getAllCandidates();
		viewCandidateView.alertText("-----------------------------Candidate List ----------------------------------");
		System.out.println("ID\t\tName\t\tEmailId\t\tDegree\t\tLocation");
		viewCandidateView.alertText("------------------------------------------------------------------------------");
		if(allCandidates.size()!=0)
		{
			for(Candidate candidate:allCandidates)
			{
				viewCandidateView.viewCandidate(candidate);
			}
		}
		else
		{
			viewCandidateView.alertText("\nNo Candidate Found .........");
		}
		viewCandidateView.alertText("------------------------------------------------------------------------------");
	}

}

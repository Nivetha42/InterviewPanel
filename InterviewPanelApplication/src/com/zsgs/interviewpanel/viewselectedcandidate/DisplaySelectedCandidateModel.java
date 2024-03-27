package com.zsgs.interviewpanel.viewselectedcandidate;

import java.util.ArrayDeque;
import java.util.List;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Candidate;

public class DisplaySelectedCandidateModel {

	DisplaySelectedCandidateView displaySelectedCandidateView;

	public DisplaySelectedCandidateModel(DisplaySelectedCandidateView displaySelectedCandidateView) {
		this.displaySelectedCandidateView = displaySelectedCandidateView;
	}

	public void getCandidateList() {
		List<Candidate> allCandidates = PanelDatabase.getInstance().selectedList();
		System.out.println("\n----------------------------- Selected List ----------------------------------");
		System.out.println("Id\t\tName\t\tEmailId\t\tDegree\t\tLocation");
		System.out.println("\n------------------------------------------------------------------------------");
		if (allCandidates.size() != 0) {
			for (Candidate candidate : allCandidates) {
				displaySelectedCandidateView.viewCandidate(candidate);
			}
		} else {
			displaySelectedCandidateView.alertText("\nNo Selected Candidate Found .........");
		}
		System.out.println("\n------------------------------------------------------------------------------");
	}

}

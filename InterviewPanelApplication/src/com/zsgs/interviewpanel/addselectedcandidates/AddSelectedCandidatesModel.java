package com.zsgs.interviewpanel.addselectedcandidates;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Candidate;

public class AddSelectedCandidatesModel {
	AddSelectedCandidatesView addSelectedCandidatesView;
	public AddSelectedCandidatesModel(AddSelectedCandidatesView addSelectedCandidatesView) {
		this.addSelectedCandidatesView=addSelectedCandidatesView;
	}
	public void AddSelectedCandidates(int candidateId)
	{
		if(PanelDatabase.getInstance().selectedCandidate(candidateId))
		{
			addSelectedCandidatesView.onCandidateAdded(candidateId);
		}
		else
		{
			addSelectedCandidatesView.onCandidatekNotExist(candidateId);
		}
	}
	public String getName(int id)
	{
		List<Candidate> c=PanelDatabase.getInstance().getAllCandidateList();
		for(Candidate candidate:c)
		{
			if(candidate.getId()==id)
			{
				return candidate.getName();
			}
		}
		return "No Candidate Found";
	}
	public void getFeedbackById(int id) {
		LinkedHashMap<Integer,String> feedback=PanelDatabase.getInstance().getFeedBack();
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		addSelectedCandidatesView.showAlert("Id\t\tName\t\tFeedBack");
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		if(feedback!=null)
		{
			if(feedback.containsKey(id))
			{
				String f=feedback.get(id);
				addSelectedCandidatesView.viewFeedback(id,getName(id), f);
				
			}else
			{
				addSelectedCandidatesView.showAlert("No Candidate Found");
			}
		}
		else
		{
		addSelectedCandidatesView.showAlert("No Candidate Found");
		}
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		
		
	}
	public void getFeedback() {
		LinkedHashMap<Integer,String> feedback=PanelDatabase.getInstance().getFeedBack();
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		addSelectedCandidatesView.showAlert("Id\t\tName\t\tFeedBack");
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		if(feedback!=null)
		{
			for(int i:feedback.keySet())
			{
				String f=feedback.get(i);
				addSelectedCandidatesView.viewFeedback(i,getName(i), f);
				
			}
		}
		else
		{
		addSelectedCandidatesView.showAlert("No Candidate Found");
		
		}
		addSelectedCandidatesView.showAlert("---------------------------------------------------------");
		
	}

}

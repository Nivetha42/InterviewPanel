package com.zsgs.interviewpanel.viewcandidate;

import com.zsgs.interviewpanel.model.Candidate;

public class ViewCandidateView {
	ViewCandidateModel  viewCandidateModel;
	public ViewCandidateView()
	{
		viewCandidateModel=new ViewCandidateModel(this);
	}
	public void initView()
	{
		viewCandidateModel.getCandidateList();
		
	}
	public void viewCandidate(Candidate candidate) {
		System.out.println(candidate.getId()+"\t\t"+candidate.getName()+"\t\t"+candidate.getEmailId()+"\t\t"+candidate.getDegree()+"\t\t"+candidate.getLocation());
//		System.out.println("\nCandidate  Id : "+candidate.getId());
//		System.out.println("\nCandidate  Name : "+candidate.getName());
//		System.out.println("\nCandidate  EmailId : "+candidate.getEmailId());
//		System.out.println("\n--------------------------------------------------------------------------");
		
	}
	public void alertText(String alertText)
	{
		System.out.println(alertText);
	}
	
}

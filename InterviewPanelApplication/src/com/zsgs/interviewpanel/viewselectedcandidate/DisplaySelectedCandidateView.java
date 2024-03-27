package com.zsgs.interviewpanel.viewselectedcandidate;

import com.zsgs.interviewpanel.model.Candidate;

public class DisplaySelectedCandidateView {
	DisplaySelectedCandidateModel displaySelectedCandidateModel;
	public DisplaySelectedCandidateView()
	{
		displaySelectedCandidateModel=new DisplaySelectedCandidateModel(this);
	}
	public void initView()
	{
		displaySelectedCandidateModel.getCandidateList();
	}
	public void viewCandidate(Candidate candidate) {
		System.out.println(candidate.getId()+"\t\t"+candidate.getName()+"\t\t"+candidate.getEmailId()+"\t\t"+candidate.getDegree()+"\t\t"+candidate.getLocation());
//		System.out.println("\nCandidate  Id : "+candidate.getId());
//		System.out.println("\nCandidate  Name : "+candidate.getName());
//		System.out.println("\nCandidate  EmailId : "+candidate.getEmailId());	
	}
	public void alertText(String alertText)
	{
		System.out.println(alertText);
	}
}

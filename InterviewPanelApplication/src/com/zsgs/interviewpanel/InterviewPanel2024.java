package com.zsgs.interviewpanel;

import com.zsgs.interviewpanel.login.LoginView;

public class InterviewPanel2024 {

	private static InterviewPanel2024 interviewPanel;
	private String appName="Interview Panel Application";
	private String version="0.0.1";
	private InterviewPanel2024()
	{}
	public static InterviewPanel2024 getInstance()
	{
		if(interviewPanel==null)
		{
			interviewPanel =new  InterviewPanel2024();
		}
		return interviewPanel;
	}
	public void create()
	{
		System.out.println("------------------------------------------"+appName+"---------------------------------------");
		new LoginView().init();
	}
	public String getAppName() {
		return appName;
	}
	public String getVersion() {
		return version;
	}
	public static void main(String[] args) {
		new InterviewPanel2024().getInstance().create();
	}

}

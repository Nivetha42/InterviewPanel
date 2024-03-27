package com.zsgs.interviewpanel.panelsetup;

import com.zsgs.interviewpanel.datalayer.PanelDatabase;
import com.zsgs.interviewpanel.model.Panel;

public class InterviewPanelSetUpModel {
	InterviewPanelSetupView interviewPanelSetupView ;
	private Panel panel;
	
	public InterviewPanelSetUpModel(InterviewPanelSetupView interviewPanelSetupView2) {
		this.interviewPanelSetupView=interviewPanelSetupView2;
		panel=PanelDatabase.getInstance().getPanel();
	}
	public void startSetUp()
	{
		if(panel==null || panel.getId()==0)
		{
			interviewPanelSetupView.onInitiateSetUp();
		}
		else
		{
			interviewPanelSetupView.onSetUpComplete(panel);
		}
	}
	public void createPanel(Panel panel)
	{
		if(panel.getTeamName().length()<3 || panel.getTeamName().length()>50)
		{
			interviewPanelSetupView.alertText("Enter Valid Company Name");
		}
		else
		{
			PanelDatabase.getInstance().insertPanel(panel);
			interviewPanelSetupView.onSetUpComplete(panel);
		}
	}
}

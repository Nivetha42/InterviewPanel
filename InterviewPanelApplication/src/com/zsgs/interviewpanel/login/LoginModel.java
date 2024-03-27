package com.zsgs.interviewpanel.login;

public class LoginModel {
	LoginView loginView;
	public LoginModel(LoginView loginView) {
		 this.loginView=loginView;
	}
	public void validateUser(String username,String password)
	{
		if(validUserName(username))
		{
			if(validPassword(username,password))
			{
				loginView.OnSuccessLogin();
			}else
			{
				loginView.onLoginFailed("\nPassword Incorrect.............");
			}
		}else
		{
			loginView.onLoginFailed("\nUserName Incorrect.............");
		}
	}
	public boolean validUserName(String username)
	{
		return username.equals("zsgs")|| username.equals("zsgsAdmin");
	}
	public boolean validPassword(String username,String password)
	{
		return username.equals("zsgs")&& password.equals("admin")|| username.equals("zsgsAdmin")&& password.equals("admin123");
	}

}

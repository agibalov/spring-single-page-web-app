package com.loki2302.service.validation.subjects;

import com.loki2302.service.validation.Password;
import com.loki2302.service.validation.UserName;

public class UserNameAndPasswordSubject {
	public final static String USER_NAME_FIELD = "userName";
	public final static String PASSWORD_FIELD = "password";
	
	@UserName
	public String userName;
	
	@Password
	public String password;
}
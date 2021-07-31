package com.junit.bankapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.citibank.dao.UserDAOImplementation;
import com.citibank.model.User;

public class CheckValidUsers {

	@Test
	public void validUsers() {
		UserDAOImplementation dao=new UserDAOImplementation();
		User use=new User();
		use.setUsername("seetha");
		use.setName("seetha@123");
		use.setPassword("12345");
		int r=dao.resigterUser(use);
		if(r==0)
		{
			System.out.println("User does not exit");
		}
		else
		{
			System.out.println("Valid user");
		}
			
	}

}

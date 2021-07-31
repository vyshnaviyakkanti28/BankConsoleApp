package com.junit.bankapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.citibank.dao.UserDAOImplementation;

public class UserRegister {

	@Test
	public void test() {
		UserDAOImplementation dao=new UserDAOImplementation();
		long result=dao.balance("seetha", "seetha@123");
		assertEquals(6000,result);
	}

}

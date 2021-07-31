package com.junit.bankapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.citibank.dao.UserDAOImplementation;

public class CheckStatus {

	@Test
	public void checkstatus() {
		UserDAOImplementation dao=new UserDAOImplementation();
		String res=dao.check("rahul", "rahul@com");
		assertEquals(res,"Approved");
	}

}

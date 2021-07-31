package com.citibank.dao;

import java.util.List;

import com.citibank.model.User;
public interface UserDAO {
	int resigterUser(User use);
	List<User> userLogin();
	int applied(User use);
	String check(String uname,String pass);
	Long balance(String uname,String pass);
	long approveReject(int cid);
	 public void withdraw(long sum,long sum2,String uname,String uemail);
	 public void deposit(long sum,long sum2,String uname,String uemail);
	 public void transfer(String uname,String uemail,String rname,long racc,long tamount);

	 public void details();
	public void acDetails(String uname,String uemail);

}

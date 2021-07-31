package com.citibank.bo;

import java.util.List;
import com.citibank.dao.UserDAO;
import com.citibank.dao.UserDAOImplementation;
import com.citibank.model.User;

public class UserBOImplementation implements UserBO {

	@Override
	public int userRegister(User use) {
		// TODO Auto-generated method stub
		UserDAO us=new UserDAOImplementation();
		int status=us.resigterUser(use);
		return status;
	}

	@Override
	public int customerLogin(User use) {
		// TODO Auto-generated method stub
		String uname=use.getUsername();
		String pass=use.getPassword();
		String email=use.getEmail();
		UserDAO us=new UserDAOImplementation();
		List<User> lu=us.userLogin();
		boolean f=false;
		for(User l:lu)
		{
			if(uname.equals(l.getUsername()) && pass.equals(l.getPassword() ) && email.equals(l.getEmail()))
			{
				f=true;
				break;
			}
		}
		if(f==true)
		return 1;
		else
			return 0;
	}

	@Override
	public int apply(User use) {
		UserDAO dao=new UserDAOImplementation();
		int r=dao.applied(use);
		return r;
		
	}

	@Override
	public String checkStatus(String uname, String uemail) {
		
		UserDAO dao=new UserDAOImplementation();
		String r=dao.check(uname,uemail);
		return r;
	}

	@Override
	public Long getBalance(String uname, String uemail) {
		UserDAO dao=new UserDAOImplementation();
		return dao.balance(uname,uemail);
		
	}

	@Override
	public long approval(int cid) {
		UserDAO dao=new UserDAOImplementation();
		long r=0;
		r=dao.approveReject(cid);
		return r;
		
	}

	@Override
	public void makeWithdraw(long sum,long sum2,String uname,String uemail) {
		UserDAO dao=new UserDAOImplementation();
		dao.withdraw(sum,sum2,uname,uemail);
		
		
		
	}
	public void makeDeposit(long sum,long sum2,String uname,String uemail) {
		UserDAO dao=new UserDAOImplementation();
		dao.deposit(sum,sum2,uname,uemail);
		
		
		
	}

	@Override
	public void makeTransfer(String uname, String uemail, String rname, long racc, long tamount) {
		
		UserDAO dao=new UserDAOImplementation();
		dao.transfer(uname,uemail,rname,racc,tamount);
	}

	@Override
	public void txnDetails() {
		UserDAO dao=new UserDAOImplementation();
		 dao.details();
	}

	@Override
	public void accDetails(String uname, String uemail) {
		// TODO Auto-generated method stub
		UserDAO dao=new UserDAOImplementation();
		 dao.acDetails(uname,uemail);
	}

	
	
}

package com.citibank.bo;
import java.util.List;
import com.citibank.model.User;


public interface UserBO {
    public int userRegister(User use);
    public int customerLogin(User use);
    public int apply(User use);
    public String checkStatus(String unmae,String uemail);
    public Long getBalance(String unmae,String uemail);
    public long approval(int cid);
   public void makeWithdraw(long sum,long sum2,String uname,String uemail);
   public void makeDeposit(long sum,long sum2,String uname,String uemail);
   public void makeTransfer(String uname,String uemail,String rname,long racc,long tamount);
   public void txnDetails();
   public void accDetails(String uname,String uemail);
}

package com.citibank.client;
import java.util.Scanner;
import com.citibank.bo.UserBO;
import com.citibank.bo.UserBOImplementation;
import com.citibank.model.User;


public class BankManagementSystem {
	public static void main(String[] args) 
	{
		
		
		Scanner s=new Scanner(System.in);
		
		
		while(true){
			System.out.println("Bank Management System");
			System.out.println("1. New User Registration");
			System.out.println("2. Customer Login");
			System.out.println("3. Employee Login");
			System.out.println("4. Exit");
			System.out.println("Enter Your Choice");
			int choice=s.nextInt();
		switch (choice) {
		case 1:
			s.nextLine();
			System.out.println("Enter your username");
			String uname = s.nextLine();
			System.out.println("Enter  email");
			String uemail=s.nextLine();
			System.out.println("Enter Password");
			String upass=s.nextLine();
			User user=new User();
			user.setUsername(uname);
			user.setEmail(uemail);
			user.setPassword(upass);
			UserBO ubo=new UserBOImplementation();
			int status=ubo.userRegister(user);
	
			break;
		
		case 2:
		
			s.nextLine();
			System.out.println("Enter your username");
			 uname = s.nextLine();
			System.out.println("Enter email");
			uemail=s.nextLine();
			System.out.println("Enter Password");
			upass=s.nextLine();
			user=new User();
			user.setUsername(uname);
			user.setEmail(uemail);
			user.setPassword(upass);
			ubo=new UserBOImplementation();
			status=ubo.customerLogin(user);

			if(status!=0) {
				System.out.println("User Successfully login");
			}else {
				System.out.println("User doesnot exists | incorrect credentials");
			}
		    boolean bool=true;
		  if(status!=0)
		  {  
			while(bool)
			{
				System.out.println("1 Apply");
				System.out.println("2 check status");
				System.out.println("3 withdraw");
				System.out.println("4 Deposit");
				System.out.println("5 Transfer");
				System.out.println("6 View account details");
				System.out.println("7 Exit");
				System.out.println("Enter your choice");
				choice=s.nextInt();
				switch(choice)
				{
				case 1:
					
					s.nextLine();
					System.out.println("Enter your username");
					 uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
					System.out.println("Enter your name");
					String name=s.nextLine();
					System.out.println("Enter your address");
					String address=s.nextLine();
					System.out.println("Enter your Aadhar Number");
					Long aadhar=s.nextLong();
					System.out.println("Enter minimum openning account balance: RS 1000");
					Long amount=s.nextLong();
					while(amount <0)
					{
						System.out.println("Enter positive value");
						amount=s.nextLong();
					}
					 user=new User();
					 user.setName(name);
					 user.setUsername(uname);
					 user.setEmail(uemail);
					 user.setAddress(address);
					 user.setAadharNumber(aadhar);
					 user.setAmount(amount);
					 ubo=new UserBOImplementation();
					 if(ubo.apply(user)!=0)
					   System.out.println("Applied successfully");
					 else
					 {
						 System.out.println("Enter valid data") ;
					 }
					break;
				case 2:
					
					s.nextLine();
					System.out.println("Enter your username");
					uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
					ubo=new UserBOImplementation();
					String r=ubo.checkStatus(uname,uemail);
					System.out.println("Your status is: "+r);
					break;
				case 3:
					
					ubo=new UserBOImplementation();
					s.nextLine();
					System.out.println("Enter your username");
					 uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
				    r=ubo.checkStatus(uname,uemail);
					if(r.equals("Approved"))
					{
						System.out.println("Enter Amount");
						long sum=0;
						sum=s.nextLong();
						long sum2=ubo.getBalance(uname,uemail);
						if(sum<0 ||sum>sum2)
						{
							System.out.println("Insufficient balance| withdrawl is not pussible");
						}
						else
						{
							sum2=sum2-sum;
							ubo.makeWithdraw(sum,sum2,uname,uemail);
							System.out.println("Successfully withdrawl");
						 }
					 }
					else
						  {
							System.out.println("Uderprocess");
							}
					break;
				case 4:
					
					ubo=new UserBOImplementation();
					s.nextLine();
					System.out.println("Enter your username");
					 uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
				    r=ubo.checkStatus(uname,uemail);
					if(r.equals("Approved"))
					{
						System.out.println("Enter Amount");
						long sum=0;
						sum=s.nextLong();
						long sum2=ubo.getBalance(uname,uemail);
						if(sum<0)
						{
							System.out.println("Insufficient balance| withdrawl is not pussible");
						}
						else
						{
							sum2=sum2+sum;
							ubo.makeDeposit(sum,sum2,uname,uemail);
							System.out.println("Successfully deposited");
						 }
					 }
					else
						  {
							System.out.println("Uderprocess");
							}
					break;
					
				case 5:
					
					ubo=new UserBOImplementation();
					s.nextLine();
					System.out.println("Enter your username");
					 uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
				    r=ubo.checkStatus(uname,uemail);
					if(r.equals("Approved"))
					{
						System.out.println("Enter recipient account number");
						Long racc=s.nextLong();
						s.nextLine();
						System.out.println("Enter recipient name");
						String rname=s.nextLine();
						System.out.println("Enter tranfer amount");
						long tamount=s.nextLong();
						
						if(tamount<0)
						{
							System.out.println("Negative balance is not accepted");
						}
						else
						{
							
							ubo.makeTransfer(uname,uemail,rname,racc,tamount);
							System.out.println("Successfully tranferred");
						 }
					 }
					else
						  {
							System.out.println("Uderprocess");
							}
					break;
				case 6:
					
					ubo=new UserBOImplementation();
					s.nextLine();
					System.out.println("Enter your username");
					 uname = s.nextLine();
					System.out.println("Enter email");
					uemail=s.nextLine();
				    ubo.accDetails(uname,uemail); 
					break;
				case 7:
					bool=false;
					break;
			  }
				
			}
		}
			break;
			
		case 3:
		
			//s.nextLine();
			System.out.println("Enter your Employee ID");
			int  id = s.nextInt();
			System.out.println("Enter  username");
			 uname=s.nextLine();
			 s.nextLine();
			System.out.println("Enter Password");
			 upass=s.nextLine();
//			System.out.println("1 Approve or Reject a customer");
//			System.out.println("2 view Transactions details of a customer");
//			System.out.println("3 Exit");
		    	 
		    int ch=0;
		    while(ch!=3)
		    {
		    	System.out.println("1 Approve or Reject a customer");
				System.out.println("2 view Transactions details of all customer");
				System.out.println("3 Exit");
				System.out.println("Enter your choice");
		    	ch=s.nextInt();
		    	if(ch==1)
		    	{
		    		System.out.println("Enter customer ID");
		    		int cid=s.nextInt();
		    		UserBO ubo2=new UserBOImplementation();
					long status1=ubo2.approval(cid);
		    		if(status1!=0)
		    		{
		    			System.out.println("Updated Successfully");
		    		}
		    	}
		    	else if(ch==2)
		    	{	    		
		    		
		    		UserBO ubo2=new UserBOImplementation();
					ubo2.txnDetails();
		    		
		    	}
		    	
		    }
		      
			break;
		
		case 4:
		  
			System.exit(0);
			break;
		
		default:
			System.out.println("Enter the correct Options");
			break;
		  	
		
	}
  
   }
  }
}

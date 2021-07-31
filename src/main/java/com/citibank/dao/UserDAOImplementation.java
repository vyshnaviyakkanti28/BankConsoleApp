package com.citibank.dao;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.citibank.dbconfig.DBConnectionSingleton;
import com.citibank.model.User;

public class UserDAOImplementation implements UserDAO {

	@Override
	public int resigterUser(User use) {
		
		int result=0;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("insert into user (username,email,password) values(?,?,?)");
			
			pstmt.setString(1, use.getUsername());
			pstmt.setString(2, use.getEmail());
			pstmt.setString(3, use.getPassword());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public List<User> userLogin() {
	
		List<User> listOfUser=null;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from user ");
			ResultSet rs = pstmt.executeQuery();
			
			listOfUser=new ArrayList<User>();
			while(rs.next()) {
				User user=new User();
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				
				listOfUser.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listOfUser;
	}

	@Override
	public int applied(User use) {
		int r=0;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("insert into appliedCustomer (name,username,email,address,aadharNo,status,amount) values(?,?,?,?,?,?,?)");
			pstmt.setString(1, use.getName());
			pstmt.setString(2, use.getUsername());
			pstmt.setString(3, use.getEmail());
			pstmt.setString(4, use.getAddress());
			pstmt.setLong(5, use.getAadharNumber());
			pstmt.setString(6, "UnderProcess");
			pstmt.setLong(7, use.getAmount());
			r=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return r;
		
	}

	@Override
	public String check(String uname, String email) {
		String r=null;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from appliedCustomer where username=? AND email=? ");
           pstmt.setString(1, uname);
           pstmt.setString(2, email);
          ResultSet rs= pstmt.executeQuery();
			rs.next();
			r=rs.getString("status");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Long balance(String uname, String email) {
		long res=0;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from appliedCustomer where username=? AND email=? ");
           pstmt.setString(1, uname);
           pstmt.setString(2, email);
          ResultSet rs= pstmt.executeQuery();
			rs.next();
			res=rs.getLong("amount");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public long approveReject(int cid) {
		
		long res=0;
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from appliedCustomer where uid=?");
           pstmt.setInt(1, cid);
          ResultSet rs= pstmt.executeQuery();
			rs.next();
			res=rs.getLong("amount");
	        if(res<1000)
	        {
	        	PreparedStatement ps2=(DBConnectionSingleton.getConnectionInstance())
						.prepareCall("UPDATE appliedCustomer SET  status=? where uid=?");
	        	ps2.setString(1, "Rejected");
	        	ps2.setInt(2, cid);
	        	ps2.executeUpdate();
	        }
	        else if(res>=1000 && rs.getString("status").equals("UnderProcess"))
	        {
	        	 
	        	PreparedStatement ps2=(DBConnectionSingleton.getConnectionInstance())
						.prepareCall("UPDATE appliedCustomer SET  status=?, accountNumber=? where uid=?");
	        	
	        	ps2.setString(1, "Approved");
	        	ps2.setLong(2, cid+10000);
	        	ps2.setLong(3, cid);
	        	ps2.executeUpdate();
	        }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public void withdraw(long sum,long sum2, String uname, String uemail) {
		
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("update appliedCustomer SET amount=? where username=? and email=?");
			pstmt.setLong(1, sum2);
			pstmt.setString(2, uname);
			pstmt.setString(3, uemail);
			pstmt.executeUpdate();
			try {
			PreparedStatement ps2=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("insert into transactions (username,email,amount,txnDate,txnTime,debit_credit ) values(?,?,?,?,?,?)");
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			ps2.setString(1, uname);
			ps2.setString(2, uemail);
			ps2.setLong(3, sum);
			ps2.setDate(4,sqlDate);
			ps2.setTimestamp(5,sqlTime);
			ps2.setString(6,"debit");
			ps2.executeUpdate();
			}
			catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deposit(long sum, long sum2, String uname, String uemail) {
		
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("update appliedCustomer SET amount=? where username=? and email=?");
			pstmt.setLong(1, sum2);
			pstmt.setString(2, uname);
			pstmt.setString(3, uemail);
			pstmt.executeUpdate();
			try {
			PreparedStatement ps2=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("insert into transactions (username,email,amount,txnDate,txnTime,debit_credit ) values(?,?,?,?,?,?)");
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			ps2.setString(1, uname);
			ps2.setString(2, uemail);
			ps2.setLong(3, sum);
			ps2.setDate(4,sqlDate);
			ps2.setTimestamp(5,sqlTime);
			ps2.setString(6,"credit");
			ps2.executeUpdate();
			}
			catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void transfer(String uname, String uemail, String rname, long racc, long tamount) {
		
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("update appliedCustomer SET amount=amount-? where username=? and email=?");
			pstmt.setLong(1, tamount);
			pstmt.setString(2, uname);
			pstmt.setString(3, uemail);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("update appliedCustomer SET amount=amount+? where accountNumber=?");
			pstmt.setLong(1, tamount);
			pstmt.setLong(2, racc);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps2=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("insert into transactions (username,email,amount,txnDate,txnTime,debit_credit ) values(?,?,?,?,?,?)");
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			ps2.setString(1, uname);
			ps2.setString(2, uemail);
			ps2.setLong(3, tamount);
			ps2.setDate(4,sqlDate);
			ps2.setTimestamp(5,sqlTime);
			ps2.setString(6,"transfer");
			ps2.executeUpdate();
			}
			catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}

	@Override
	public void details() {
		
		
		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from transactions");
          ResultSet rs= pstmt.executeQuery();
         
          System.out.println("Username--Email-------Amount-------Date-----------Time------TxnType");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"    "+rs.getString(2)+"     "+
						rs.getLong(3)+"    "+rs.getDate(4)+"     "+rs.getTime(5) +"    "+rs.getString(6));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void acDetails(String uname, String uemail) {
		

		try {
			PreparedStatement pstmt=(DBConnectionSingleton.getConnectionInstance())
					.prepareCall("select * from appliedCustomer where username=? AND email=?");
			 pstmt.setString(1, uname);
			 pstmt.setString(2, uemail);
          ResultSet rs= pstmt.executeQuery();
          System.out.println("ID----Name--------UserName---Email-----City-- AadharNo--Status---Amount----AccountNo");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+
						rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+" "+
						rs.getLong(6)+"  "+rs.getString(7)+"  "+rs.getLong(8)+"     "+rs.getLong(9));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}

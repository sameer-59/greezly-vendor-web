package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.cognizant.helper.ConnectionManager;

public class VendorDAOImpl implements VendorLoginDAO {

	private ConnectionManager manager=new ConnectionManager();
	public boolean findVendor(String uname,String pword) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		boolean result=false;
		System.out.println("dao"+uname);
		System.out.println("dao"+uname);
		try 
		{
			PreparedStatement statement=connection.prepareStatement("select * from vendor_table where vendor_id=? and vendor_password=?");
			statement.setString(1,uname);
		    statement.setString(2,pword);
		    ResultSet resultSet=statement.executeQuery();
		     
		    while(resultSet.next())
		    {
		    	result=true;
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		
		return result;
	}
	
}

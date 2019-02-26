package com.cognizant.service;

import com.cognizant.dao.VendorDAOImpl;

public class VendorServiceImpl implements VendorService{

	@Override
	public boolean findVendor(String uname, String pword) {
		// TODO Auto-generated method stub
		VendorDAOImpl ob = new VendorDAOImpl();
		//FactoryAdminLoginService ob = new FactoryAdminLoginService();
		boolean result=ob.findVendor(uname,pword);
		return result;
	}

}

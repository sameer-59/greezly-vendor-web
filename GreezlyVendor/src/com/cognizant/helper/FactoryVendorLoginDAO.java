package com.cognizant.helper;

import com.cognizant.dao.VendorDAOImpl;
import com.cognizant.dao.VendorLoginDAO;

public class FactoryVendorLoginDAO {

	public static VendorLoginDAO createVendorDAO(){		// TODO Auto-generated method stub
		
		VendorLoginDAO vendordao = new VendorDAOImpl();
		return vendordao;
		
	
	}
}

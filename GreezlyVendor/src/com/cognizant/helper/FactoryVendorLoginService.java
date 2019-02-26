package com.cognizant.helper;

import com.cognizant.service.VendorService;
import com.cognizant.service.VendorServiceImpl;

public class FactoryVendorLoginService {

	public static VendorService createVendorService()
	{
		VendorService vendorService=new VendorServiceImpl();
		return vendorService;
	}
}

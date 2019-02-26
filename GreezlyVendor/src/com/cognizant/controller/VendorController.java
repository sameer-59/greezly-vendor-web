package com.cognizant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.entity.Vendor;
import com.cognizant.helper.ConnectionManager;
import com.cognizant.helper.FactoryVendorLoginService;
import com.cognizant.service.VendorService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/vendorlogin")
public class VendorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int login_attempts = 3;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorController() {
        super();
        System.out.println("constructor");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("username");
		String pword=request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		Vendor admin = new Vendor();
		admin.setUsername(uname);
		admin.setPassword(pword);
		System.out.println(uname);
		
		VendorService vendorService = FactoryVendorLoginService.createVendorService();
		boolean vendorExists=vendorService.findVendor(uname,pword);
		//RequestDispatcher dispatcher=request.getRequestDispatcher("welcome_admin.jsp");
		ConnectionManager manager=new ConnectionManager();
		/*if(adminExists){
			
			HttpSession session = request.getSession();
				session.setAttribute("username",uname);
				dispatcher.forward(request,response);
		}
		else{
				
			out.println("Failed");
		}*/
		if(vendorExists)
		{
				login_attempts=3;
				try {
					Connection connection=manager.openConnection();
					PreparedStatement st = connection.prepareStatement("update vendor_table set status='"+1+"' where vendor_id='"+uname+"' ");
					st.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Welcome"+uname);
				HttpSession session=request.getSession();
				session.setAttribute("username",uname);
				response.sendRedirect("vendorProductView.jsp");
		}
		else
		{
				System.out.println("Login Falied");
				if(login_attempts==0)
				{
					
					out.println("<script language=\"javascript\">" );
	                out.println("alert('No Login Attempts Available Contact DBA')");
	                out.println("location='index.html';");
	                out.println("</script>");
				}
				else
				{
				    login_attempts=login_attempts-1;
				    if(login_attempts==0)
				    {
				    	out.println("<script language=\"javascript\">");
						out.println("alert('Account Blocked!! Contact DBA')");
						out.println("location='index.html';");
						out.println("</script>");
				    	/*connection = null;
				    	
						statement = null;
						st =null;
						rs = null;
						try {
							Class.forName("oracle.jdbc.OracleDriver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try 
						{
								connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","sam","newsam");
								statement = connection.prepareStatement("select admin_username from admin_table where admin_username='"+uname+"' ");
								rs = statement.executeQuery();
								if(rs.next())
								{
									st = connection.prepareStatement("update admin_table set status='"+0+"' where admin_username='"+uname+"' ");
									int i = st.executeUpdate();
									if(i < 0)
									{
										out.println("<script language=\"javascript\">");
										out.println("alert('Account Blocked!! Contact DBA')");
										out.println("location='Index.html';");
										out.println("</script>");
									}
								}
								else
								{
									out.println("<script language=\"javascript\">");
									out.println("alert('No record for '"+uname+"' Contact DBA')");
									out.println("location='Index.html';");
									out.println("</script>");
								}
							} 
						catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally {
							try {
								connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				    */	
				    }
				    else
				    {
				    	out.println("<script language=\"javascript\">" );
				    	out.println("alert('Invalid Id/Password Attempts Remaining '+ '"+login_attempts+"')");
					    out.println("location='index.html';");
					    out.println("</script>");
				    }
			   }
			}
	}

}

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.cognizant.helper.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>Vendor Product Details</h2>

<%
 ConnectionManager manager=new ConnectionManager();
Connection connection=manager.openConnection();
String id = "vendor1";
PreparedStatement ps = connection.prepareStatement("select * from vendor_product where vendor_id='"+id+"' ");
ResultSet rs = ps.executeQuery();

%>

<table border="1px">
<tr>
<th>SR No</th><th>Product ID</th><th>VENDOR ID</th><th>QUANTITY</th>
</tr>

<%

while(rs.next())
{
%>
	<tr>
		<td><%=rs.getString(1) %></td><td><%=rs.getString(2) %></td><td><%=rs.getString(3) %></td><td><%=rs.getString(4) %></td>
	</tr>
<% } %>
</table>
</center>
</body>
</html>
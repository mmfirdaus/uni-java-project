<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		String number = request.getParameter("total");
		double newnumber = Double.parseDouble(number);
		
		double usd = newnumber * 0.23;
		double ps = newnumber * 0.19;
		double sgd = newnumber * 0.33;
		double eur = newnumber * 0.21;
		double yen = newnumber * 25.50;

		out.println("<h2>Converting: RM" + newnumber +"</h2>");
		out.println("<h3>US Dollar: " + usd +"</h3>");
		out.println("<h3>Pound Sterling: " + ps +"</h3>");
		out.println("<h3>Singapore Dollar: " + sgd +"</h3>");
		out.println("<h3>Euro: " + eur +"</h3>");
		out.println("<h3>Japanese Yen: " + yen +"</h3>");
		out.println("<a href=\"#\" onclick=\"history.go(-1)\">Go Back</a>");
		out.println("<br><a href=\"home.html\">Home Page</a>");

		%>

</body>
</html>
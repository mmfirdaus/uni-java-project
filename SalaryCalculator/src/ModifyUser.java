import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyUser extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter pw = resp.getWriter();
	String uid = req.getParameter("modify");
	
	try {
		System.out.println("Driver is loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dburl = "jdbc:mysql://localhost:3306/salarycalculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection dbconn = DriverManager.getConnection(dburl,"root","root@123");
		System.out.println("Database is successfully connected");
		
		String sql = "SELECT * FROM worker where worker_id ='"+ uid+"'";
		Statement stmt1 = dbconn.createStatement();
		ResultSet rs = stmt1.executeQuery(sql);
		


		if (rs.next())
		{
		
		pw.println("<form action=\"modify2\" method =\"post\">");
		pw.println("<input type=\"text\" name=\"id\" value=\""+rs.getString(1)+"\" readonly<br><br>");
		pw.println("<input type=\"text\" name=\"name\" value=\""+rs.getString(2)+"\"<br><br>");
		pw.println("<input type=\"text\" name=\"addr\" value=\""+rs.getString(3)+"\"<br><br>");
		pw.println("<input type=\"text\" name=\"phone\" value=\""+rs.getString(4)+"\"<br><br>");
		pw.println("<input type=\"text\" name=\"hr\" value=\""+rs.getString(5)+"\"<br><br>");
		pw.println("<input type=\"text\" name=\"ot\" value=\""+rs.getString(6)+"\"<br><br>");
		pw.println("<input type=\"submit\" value=\"Submit\"><br><br></form>");

		}
		
		System.out.println("Records have been successfully displayed");
		stmt1.close();
		dbconn.close();
		pw.close();
		
		
	} catch (Exception e) {
		pw.println("Error: " + e);
	}
	
}
}

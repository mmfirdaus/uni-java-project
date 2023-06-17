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

public class ViewSalary extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter pw = resp.getWriter();
	String uid = req.getParameter("views");
	
	try {
		System.out.println("Driver is loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dburl = "jdbc:mysql://localhost:3306/salarycalculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection dbconn = DriverManager.getConnection(dburl,"root","root@123");
		System.out.println("Database is successfully connected");
		
		String sql = "SELECT * FROM worker where worker_id ='"+ uid+"'";
		Statement stmt1 = dbconn.createStatement();
		ResultSet rs1 = stmt1.executeQuery(sql);
		
		String sql1 = "SELECT * FROM salary where worker_id ='"+ uid+"'";
		Statement stmt = dbconn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);


		if (rs.next())
		{
		
			if (rs1.next())
			{
				pw.println("<br><h1>Welcome " + rs1.getString(2) + "</h1>");
				
			}
		pw.println("<style>table, th, td {border: 1px solid black;}</style>");
		pw.println("<table style=\"width:350px\"><tr><th>SALARY DATE</th><th>TOTAL SALARY</th></tr><tr>");
	    pw.println("<td>"+rs.getString(3)+"</td>");
	    pw.println("<td>"+rs.getString(2)+"</td>");
	    pw.println("</tr></table>");
	    pw.println("<a href=\"#\" onclick=\"history.go(-1)\">Go Back</a>");
		pw.println("<br><br><a href=\"home.html\">Home Page</a>");
	
		}
		
		System.out.println("Records have been successfully displayed");
		stmt.close();
		dbconn.close();
		pw.close();
		
		
	} catch (Exception e) {
		pw.println("Error: " + e);
	}

}
}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertRecord extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		String uid = req.getParameter("id");
		Integer hours = Integer.parseInt(req.getParameter("hr"));
		Integer overtime = Integer.parseInt(req.getParameter("ot"));

		try {
			System.out.println("Driver is loaded");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/salarycalculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection dbconn = DriverManager.getConnection(dburl,"root","root@123");
			System.out.println("Database is successfully connected");
			String sql1 = "SELECT * FROM worker where worker_id ='"+ uid+"'";
			Statement stmt = dbconn.createStatement();
			ResultSet rs = stmt.executeQuery(sql1);
			LocalDate now = LocalDate.now();
			String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 
	
			if (rs.next())
			{
	
			double workersalary = Double.parseDouble(rs.getString(5));
			double workerovertime = Double.parseDouble(rs.getString(6));
			
			double salary = workersalary*hours;
			double overtime2 = workerovertime*overtime;
			
			double total2 = salary + overtime2;
			double total = total2 + (total2*0.5);
			String totalstring =String.valueOf(total);  
			
			String sql2 = "INSERT INTO salary(worker_id, salary_total, salary_date) VALUES (?,?,?)";
			PreparedStatement ps = dbconn.prepareStatement(sql2);
			ps.setString(1, uid);
			ps.setString(2, totalstring);
			ps.setString(3, date);
			
			
			pw.println("<br><h1>Welcome " + rs.getString(2) + "</h1>");
			pw.println("<h2>Address:  " + rs.getString(3) + "</h2>");
			pw.println("<h2>Contact Number: 60" + rs.getString(4) + "</h2>");
			pw.println("<form action=\"currency\" method=\"post\">");
			pw.println("<h2>Your total salary is: RM"+"<input type=\"text\" value=\""+total +"\"name=\"total\" readonly style=\"width:100px;\">" + "</h2>");
			pw.println("<input type=\"submit\" value=\"Other Currencies\">");
			pw.println("</form>");
			
			pw.println("<form action=\"modify\" method=\"post\">");
			pw.println("<h2>Modify Information for: <input type=\"text\" value=\""+rs.getString(1) +"\"name=\"modify\" readonly style=\"width:100px;\">" );
			pw.println("<input type=\"submit\" value=\"Modify Info\">" + "</h2>");
			pw.println("</form>");
			
			pw.println("<form action=\"views\" method=\"post\">");
			pw.println("<h2>View Previous Salary for: <input type=\"text\" value=\""+rs.getString(1) +"\"name=\"views\" readonly style=\"width:100px;\">" );
			pw.println("<input type=\"submit\" value=\"View Previous Salary\">" + "</h2>");
			pw.println("</form>");
			
			pw.println("<br><br><a href=\"home.html\">Home Page</a><br>");
			
			ps.executeUpdate();
			ps.close();
			  

			}
			
			System.out.println("Records have been successfully displayed");
			stmt.close();
			dbconn.close();
			pw.close();
			
			
		} catch (Exception e) {
			pw.println("Only One Salary Submission Per Day. Error: " + e);
		}
	
	}

}

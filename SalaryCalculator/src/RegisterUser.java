import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUser extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter pw = resp.getWriter();
	String uid = req.getParameter("id");
	String fname = req.getParameter("fname");
	String addr = req.getParameter("address");
	String phone = req.getParameter("phone");
	Integer hours = Integer.parseInt(req.getParameter("ihr"));
	Integer overtime = Integer.parseInt(req.getParameter("iot"));
	
	
	
	try {
		
		System.out.println("Driver is loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dburl = "jdbc:mysql://localhost:3306/salarycalculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection dbconn = DriverManager.getConnection(dburl,"root","root@123");
		System.out.println("Database is successfully connected");
		
		String sql1 = "INSERT INTO worker(worker_id, worker_name, worker_addr, worker_phone, worker_salary, worker_overtime) VALUES (?,?,?,?,?,?)";
	
		PreparedStatement ps = dbconn.prepareStatement(sql1);
		ps.setString(1, uid);
		ps.setString(2, fname);
		ps.setString(3, addr);
		ps.setString(4, phone);
		ps.setLong(5, hours);
		ps.setLong(6, overtime);
		int t = ps.executeUpdate();
		
		if(t>0)
		{
			pw.println("<h2>Worker have been inserted into the database </h2><br>");
			pw.println("<br><br><a href=\"home.html\">Home Page</a>");
		}
		
		else
		{
			throw new Exception();
			
		}
		ps.close();
		dbconn.close();
		pw.close();
		
		
	} catch (Exception e) {
		pw.println("Error: " + e);
	}
	
	
}
}

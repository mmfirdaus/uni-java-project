import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyUser2 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		String uid = req.getParameter("id");
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		String phone = req.getParameter("phone");
		String hr = req.getParameter("hr");
		String ot = req.getParameter("ot");
		
		try {
			System.out.println("Driver is loaded");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/salarycalculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection dbconn = DriverManager.getConnection(dburl,"root","root@123");
			System.out.println("Database is successfully connected");
			
			String sql = "UPDATE worker SET worker_name='"+ name + "' ,worker_addr='"+ addr +"' ,worker_phone='"+ phone + "' ,worker_salary='"+ hr + "'"
					+ " ,worker_overtime='"+ ot + "' WHERE worker_id ='"+ uid+"'";
			PreparedStatement ps = dbconn.prepareStatement(sql);
			int t = ps.executeUpdate();
			
			if(t>0)
			{
				pw.println("<h2>Worker have been updated from the database </h2><br>");
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

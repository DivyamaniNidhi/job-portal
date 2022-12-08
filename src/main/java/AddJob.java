
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddJob")
public class AddJob extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
	public AddJob() {
		 super();
	        // TODO Auto-generated constructor stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String cid = request.getParameter("cid");	
		String jobId = request.getParameter("jobId");
		String status = request.getParameter("status");
		String role = request.getParameter("role");
		
		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
 		String username = "jobportalDB"; //MySQL username
 		String password = "Jobportal@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);
		
 		// check for duplicate job
		
 		PreparedStatement duplicate_job_check = con.prepareStatement("select * from jobs where job_id = ? and company_id=?" );
 		duplicate_job_check.setString(1,jobId);
 		duplicate_job_check.setString(2,cid);
 		ResultSet duplicate_check = duplicate_job_check.executeQuery();
  	    if (duplicate_check.next() == true) 
  	   {
  		 PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"Job ID already exist! \");");
			out.println("location=\"add_job.jsp?cid=" + cid + "\";");
			out.println("</script>");
  	    }
 		
 		
 		PreparedStatement add_job = con.prepareStatement("insert into jobs(job_id,company_id,status,job_role) values(?,?,?,?)");
 		add_job.setString(1,jobId);
 		add_job.setString(2,cid);
 		add_job.setString(3,status);
 		add_job.setString(4,role);

		int result = add_job.executeUpdate();
		
		
		if(result>0)
		{
			System.out.println("Job Added Successfully!");
			PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Job Added Successfully!\");");
 			out.println("location=\"CompanyViewJobs?cid=" + cid + "\";");
 			out.println("</script>");
		}
		else 
		{
			PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Something went wrong on the server!\");");
 			out.println("location=\"add_job.jsp?cid=" + cid + "\";");
 			out.println("</script>");
		}
	}
	catch(Exception e){
		PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"Something went wrong on the server side!\");");
			out.println("</script>");
		e.printStackTrace();			
	}
		
		}
		}


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


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/AddCompany")
public class AddCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCompany() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cname = request.getParameter("cname");
			String cid = request.getParameter("cid");
			String cemail = request.getParameter("cemail");
			String clocation = request.getParameter("clocation");
			String cpassword = request.getParameter("cpassword");
		
			// Password Validation
			if (cpassword.length()<5)  
			{
		  		String passMessage = "Password is too short!";
		  		PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + passMessage + "\");");
	 			out.println("location='company_signup.jsp';");
	 			out.println("</script>");
		  	}
			
			Connection con = null;
	 		String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String password = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
	 		
	 		// Check for duplicate student
	 		PreparedStatement duplicate_company_check = con.prepareStatement("select * from company where company_id = ?" );
	 		duplicate_company_check.setString(1,cid);
	 		ResultSet duplicate_check = duplicate_company_check.executeQuery();
	 		
	  	   if (duplicate_check.next() == true) 
	  	   {
	  		 PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Company already registered! \");");
 			out.println("location='company_login.jsp';");
 			out.println("</script>");
	  	    }
	  	   
	  	   // Check for duplicate username
		  	 PreparedStatement duplicate_username_check = con.prepareStatement("select * from company where cusername = ?" );
		 		duplicate_username_check.setString(1,cemail);
		 		ResultSet duplicate_uname = duplicate_username_check.executeQuery();
		 		
		  	   if (duplicate_uname.next() == true) 
		  	   {
		  		 System.out.println("Account with same email already created !");
		  		String dupMessage = "Account with same email already created !";
		  		PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + dupMessage + "\");");
	 			out.println("location='company_signup.jsp';");
	 			out.println("</script>");
		  	    }
	 		
		  	   // Insert if all constraints above are satisfied
	 		PreparedStatement company_register = con.prepareStatement("insert into company(company_id,company_name,cusername,cpassword,head_office_location) values(?,?,?,?,?)");
	 		company_register.setString(1,cid);
	 		company_register.setString(2,cname);
	 		company_register.setString(3,cemail);
	 		company_register.setString(4,cpassword );
	 		company_register.setString(5,clocation);
			int result = company_register.executeUpdate();
			if(result>0)
			{
				System.out.println("Company Registered Successfully!");
				PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Company Registered Successfully!\");");
	 			out.println("location='company_afterlogin.jsp';");
	 			out.println("</script>");
			}
			else 
			{
				PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Something went wrong on the server!\");");
	 			out.println("location='company_signup.jsp';");
	 			out.println("</script>");
			}
		}
		catch(Exception e){
			PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Something went wrong on the server side!\");");
 			out.println("location='company_signup.jsp';");
 			out.println("</script>");
			e.printStackTrace();			
		}
	}

}

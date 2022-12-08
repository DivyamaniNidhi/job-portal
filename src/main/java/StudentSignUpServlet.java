

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.*;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/StudentSignUpServlet")
public class StudentSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static boolean isValidMobileNo(String str)  
    {  
    //(0/91): number starts with (0/91)  
    //[7-9]: starting of the number may contain a digit between 0 to 9  
    //[0-9]: then contains digits 0 to 9  
    Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
    //the matcher() method creates a matcher that will match the given input against this pattern  
    Matcher match = ptrn.matcher(str);  
    //returns a boolean value  
    return (match.find() && match.group().equals(str));  
    }  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String sname = request.getParameter("sname");
			String sid = request.getParameter("sid");
			String semail = request.getParameter("semail");
			String scollege = request.getParameter("scollege");
			String sbranch = request.getParameter("sbranch");
			String sphone = request.getParameter("sphone");
			String spassword = request.getParameter("spassword");
			
			
			// Phone number format validation
			if (!isValidMobileNo(sphone))  
			{
		  		String phoneMessage = "Enter phone number in following format : 0XXXXXXXXXX !";
		  		PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + phoneMessage + "\");");
	 			out.println("location='student_signup.jsp';");
	 			out.println("</script>");
		  	}
			
			// Password Validation
			if (spassword.length()<5)  
			{
		  		String passMessage = "Password is too short!";
		  		PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + passMessage + "\");");
	 			out.println("location='student_signup.jsp';");
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
	 		PreparedStatement duplicate_student_check = con.prepareStatement("select * from student where student_id = ?" );
	 		duplicate_student_check.setString(1,sname);
	 		ResultSet duplicate_check = duplicate_student_check.executeQuery();
	 		
	  	   if (duplicate_check.next() == true) 
	  	   {
	  		 PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Student already registered! \");");
 			out.println("location='student_login.jsp';");
 			out.println("</script>");
	  	    }
	  	   
	  	   // Check for duplicate username
		  	 PreparedStatement duplicate_username_check = con.prepareStatement("select * from student where susername = ?" );
		 		duplicate_username_check.setString(1,semail);
		 		ResultSet duplicate_uname = duplicate_username_check.executeQuery();
		 		
		  	   if (duplicate_uname.next() == true) 
		  	   {
		  		 System.out.println("Account with same email already created !");
		  		String dupMessage = "Account with same email already created !";
		  		PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + dupMessage + "\");");
	 			out.println("location='student_signup.jsp';");
	 			out.println("</script>");
		  	    }
	 		
		  	   // Insert if all constraints above are satisfied
	 		PreparedStatement student_register = con.prepareStatement("insert into student(student_id,student_fname,susername,spassword,phone,college,branch) values(?,?,?,?,?,?,?)");
	 		student_register.setString(1,sid);
	 		student_register.setString(2,sname);
	 		student_register.setString(3,semail);
	 		student_register.setString(4,spassword );
	 		student_register.setString(5,sphone);
	 		student_register.setString(6,scollege);
	 		student_register.setString(7,sbranch);
			int result = student_register.executeUpdate();
			if(result>0)
			{
				System.out.println("Student Registered Successfully!");
				PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Student Registered Successfully!\");");
	 			HttpSession httpSession = request.getSession();
	            httpSession.setAttribute("sid", sid);
	 			out.println("location='student_afterlogin.jsp';");
	 			out.println("</script>");
			}
			else 
			{
				PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Something went wrong on the server!\");");
	 			out.println("location='student_signup.jsp';");
	 			out.println("</script>");
			}
		}
		catch(Exception e){
			PrintWriter out = response.getWriter();
 			out.println("<script type=\"text/javascript\">");
 			out.println("alert(\"Something went wrong on the server side!\");");
 			out.println("location='student_signup.jsp';");
 			out.println("</script>");
			e.printStackTrace();			
		}
	}

}

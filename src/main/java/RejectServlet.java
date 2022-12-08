

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentApply
 */
@WebServlet("/RejectServlet")
public class RejectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RejectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session ==null){
				String failMessage = "Company not logged in";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("companylogin.jsp");
			}
			System.out.println("Printing connection object ");
			String cid = (String)session.getAttribute("cid");
			if(cid ==null){
				String failMessage = "Company not logged in";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("companylogin.jsp");
			}
			
			
			String sid = request.getParameter("sid");
			String jobid = request.getParameter("job_id");
			Connection con = null;
			String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
	 		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st = con.prepareStatement("update applications set application_status = 'Rejected' where student_id = ? and job_id = ? and company_id = ?;");
	 		st.setString(1,sid);
	 		st.setString(2,jobid);
	 		st.setString(3,cid);
	 		int result = st.executeUpdate();
	 		response.setContentType("text/html");  
	        PrintWriter out=response.getWriter(); 
	        
	        if(result>0)
			{
				System.out.println("Application Status Updated Successfully!");
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Application Status Updated Successfully!\");");
	 			out.println("location=\"CompanyViewApplications?cid=" + cid + "\";");
	 			out.println("</script>");
			}
			else 
			{
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"Something went wrong on the server!\");");
	 			out.println("location=\"CompanyViewApplications?cid=" + cid + "\";");
	 			out.println("</script>");
			}
			
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
		}
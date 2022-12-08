

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
@WebServlet("/StudentViewJobsServlet")
public class StudentViewJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentViewJobsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			
			if(session ==null){
				String failMessage = "Student not logged in";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("student_login.jsp");
			}
			System.out.println("Printing connection object ");
			String sid = (String)session.getAttribute("sid");
			if(sid ==null){
				String failMessage = "Student not logged in";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("student_login.jsp");
			}
			

			Connection con = null;
			String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database1
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st = con.prepareStatement("select * from jobs");
			ResultSet rs = st.executeQuery();
			PrintWriter out = response.getWriter();  
	        response.setContentType("text/html");  
	        
	        out.print("<title>View Jobs</title><head><!-- CSS only -->\n"
	        		+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">"
	        		+ "<!-- JavaScript Bundle with Popper -->\n"
	        		+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>"
	        		+ "<link rel=\"stylesheet\" href=\"components/style.css\"></head><nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n"
	        		+ "  <div class=\"container-fluid\">\n"
	        		+ "    <a class=\"navbar-brand\" href=\"#\">JobPortal</a>\n"
	        		+ "    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
	        		+ "      <span class=\"navbar-toggler-icon\"></span>\n"
	        		+ "    </button>\n"
	        		+ "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
	        		+ "      <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n"
	        		+ "        <li class=\"nav-item\">\n"
	        		+ "          <a class=\"nav-link active\" aria-current=\"page\" href=\"home.jsp\"><i class=\"fas fa-home\"></i>Home</a>\n"
	        		+ "        </li>\n"
	        		+ "        <li class=\"nav-item\">\n"
	        		+ "          <a class=\"nav-link\" href=\"StudentViewJobsServlet?sid=" + sid + "\"><i class=\"fas fa-plus-circle\"></i>Apply/View Jobs</a>\n"
	        		+ "        </li>\n"
	        		+ "        <li class=\"nav-item\">\n"
	        		+ "          <a class=\"nav-link\" href=\"StudentViewApplicationServlet?sid=" + sid + "\"><i class=\"fas fa-layer-group\"></i> View Applications</a>\n"
	        		+ "        </li>\n"
	        		+ "		</ul>\n"
	        		+ "      <form class=\"form-inline my-2 my-lg-0\">\n"
	        		+ "        <a href=\"StudentLogoutServlet\" class=\"btn btn-light mr-10\"><i class=\"fas fa-sign-out-alt\"></i>Logout</a>\n"
	        		+ "        <a href=\"student.jsp\" class=\"btn btn-light\"><i class=\"fas fa-user-circle\"></i> Student</a>\n"
	        		+ "      </form>\n"
	        		+ "    </div>\n"
	        		+ "  </div>\n"
	        		+ "</nav><table border='1' width='100%'");  
	        
			if (!rs.isBeforeFirst()) 
	 		{    
	 			String failMessage = "No Job Openings Available Currently!!";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("student_afterlogin.jsp?sid="+sid);
	 		} 
	 		else 
	 		{
	 			out.println("<div class=\"card\"><div class=\"card-body\">");	 		      
	             while (rs.next()) 
	             {  
	                 String jobid = rs.getString("job_id");  
	                 String compid = rs.getString("company_id");  
	                 String jobrole = rs.getString("job_role");  
	                 PreparedStatement getCompName = con.prepareStatement("select * from company where company_id = ?");
	                 getCompName.setString(1, compid);
	     			 ResultSet rs_name = getCompName.executeQuery();
	     			 String compname = "";
	     			 while(rs_name.next())
	     			 {
	     				compname = rs_name.getString("company_name");  
	     			 }
	     			
	                 out.println("<body><h4 class=\"card-title\">" + jobid + " || " + jobrole + "</h4>");   
	                 out.println("<p class=\"card-text\">" + compname + "</p>");
	                 out.println("<a href=\"student_apply.jsp?job_id=" + jobid + "&comp_id=" + compid + "&sid=" + sid + "\"><button type=\"submit\" class=\"btn btn-primary btn-block center\">Apply</button></a>");
	             } 
	             
	             out.println("</div></div>");  
	             out.println("</body></html>");  
	            // request.setAttribute("jobid", jobid);
	 		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

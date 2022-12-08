

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
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Applications
 */
@WebServlet("/StudentViewApplicationServlet")
public class StudentViewApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentViewApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session == null){
				String failMessage = "Student not logged in";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("student_login.jsp");
			}
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
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st = con.prepareStatement("select * from applications where student_id=? order by job_id desc;");
	 		st.setString(1,sid);
	 		ResultSet rs = st.executeQuery();
	 		response.setContentType("text/html");  
	        PrintWriter out=response.getWriter(); 
	        //request.getRequestDispatcher("components/bootstrap.jsp").include(request, response);
	        out.print("<title>Welcome</title><head><!-- CSS only -->\n"
	        		+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
	        		+ "\n"
	        		+ "<!-- JavaScript Bundle with Popper -->\n"
	        		+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n"
	        		+ "\n"
	        		+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css\" integrity=\"sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n"
	        		+ "\n"
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
	        out.print("<table class=\"table\">");
	        out.print("<br><br><br><thead class=\"thead-dark\"><tr><th>Company Name</th><th>Job Id</th><th>Role</th><th>Why Hire?</th><th>Application Status</th><th>Withdraw?</th></tr><thead>"); 
	 		 while(rs.next()){
	 			 PreparedStatement getCompName = con.prepareStatement("select * from company where company_id = ?");
	 			 String compid = rs.getString("company_id");
                 getCompName.setString(1, compid);
    			 ResultSet rs_name = getCompName.executeQuery();
    			 String compname = "";
    			 while(rs_name.next())
    			 {
    				compname = rs_name.getString("company_name");  
    			 }
    			 
    			 PreparedStatement getJobRole = con.prepareStatement("select * from jobs where job_id = ?");
	 			 String jobid = rs.getString("job_id");
	 			 getJobRole.setString(1, jobid);
    			 ResultSet rs_role = getJobRole.executeQuery();
    			 String jobrole = "";
    			 while(rs_role.next())
    			 {
    				jobrole = rs_role.getString("job_role");  
    			 }
    			  
	 			out.print("<tr><td>"+compname+"</td><td>"+rs.getString("job_id")+"</td><td>"+jobrole+"</td> \n"+ 
	 	                 "<td>"+rs.getString("why_hire")+"</td><td>"+rs.getString("application_status")+"</td><td><a href=\"StudentWithdrawServlet?job_id=" + rs.getString("job_id") + "&comp_id=" + compid + "&sid=" + sid + "\"><button type=\"submit\" class=\"btn btn-primary btn-block center\">Withdraw</button></a></td></tr>");    
	            }   
        
        out.close();
        con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

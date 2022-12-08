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
@WebServlet("/CompanyViewJobs")
public class CompanyViewJobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CompanyViewJobs() {
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
			
			Connection con = null;
			String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database1
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st = con.prepareStatement("select * from jobs where company_id = ?");
	 		st.setString(1,cid);
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
	        		+ "          <a class=\"nav-link\" href=\"add_job.jsp?cid=" + cid + "\"><i class=\"fas fa-plus-circle\"></i>Add Job</a>\n"
	        		+ "        </li>\n"
	        		+ "        <li class=\"nav-item\">\n"
	        		+ "          <a class=\"nav-link\" href=\"CompanyViewJobs?cid=" + cid + "\"><i class=\"fas fa-plus-circle\"></i>View Jobs</a>\n"
	        		+ "        </li>\n" 
	        		
	   
	        		+ "        <li class=\"nav-item\">\n"
	        		+ "          <a class=\"nav-link\" href=\"CompanyViewApplications?cid=" + cid + "\"><i class=\"fas fa-layer-group\"></i> View Applications</a>\n"
	        		+ "        </li>\n"
	        		+ "		</ul>\n"
	        		+ "      <form class=\"form-inline my-2 my-lg-0\">\n"
	        		+ "        <a href=\"CompanyLogout?cid=" + cid + "\" class=\"btn btn-light mr-10\"><i class=\"fas fa-sign-out-alt\"></i>Logout</a>\n"
	        		+ "      </form>\n"
	        		+ "    </div>\n"
	        		+ "  </div>\n"
	        		+ "</nav><table border='1' width='100%'");  
	        out.print("<table class=\"table\">");
	        out.print("<br><br><br><thead class=\"thead-dark\"><tr><th>Job Id</th><th>Role</th><th>Status</th><thead>"); 
	        if (!rs.isBeforeFirst()) 
	 		{    
	 			String failMessage = "No Job Added!!";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
		  		response.sendRedirect("company_afterlogin.jsp?cid="+cid);
	 		} 
	        else {
	        	out.println("<div class=\"card\"><div class=\"card-body\">");	 		      
	             while (rs.next()) 
	             {  
	                 String jobId = rs.getString("job_id");  
	                 String status = rs.getString("status");  
	                 String role = rs.getString("job_role");
	                 
	     			 out.println("<html><head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js\"></script> </head>");
	                 

	                 out.print("<tr><td>"+jobId+"</td><td>"+role+"</td><td>"+status+"</td></tr>");
	                 }
	 	 			 out.println("</div></div>");  
		             out.println("</body></html>");  
		           
	             }
		}
			catch(Exception e){
				e.printStackTrace();
			}
    }
}
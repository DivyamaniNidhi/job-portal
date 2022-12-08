

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


@WebServlet("/CompanyLogin")
public class CompanyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CompanyLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cemail = request.getParameter("cemail");
			String cpassword = request.getParameter("cpassword");
						
			Connection con = null;
	 		String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st_user = con.prepareStatement("select * from company where cusername=?");
	 		st_user.setString(1,cemail);
			ResultSet rs_user = st_user.executeQuery();
	 		if (!rs_user.isBeforeFirst()) 
	 		{    
	 			String failMessage = "User doesn't exist! Please SignUp first!";
		  		System.out.println("<script type='text/javascript'>");
		  		System.out.println("alert(" + "'" + failMessage + "'" + ");</script>");
		  		System.out.println("</head><body></body></html>");
	 			response.sendRedirect("company_signup.jsp");
	 		} 
	 		else 
	 		{
	 			PreparedStatement st = con.prepareStatement("select * from company where cusername=? and cpassword=?");
		 		st.setString(1,cemail);
				st.setString(2,cpassword);
				ResultSet rs = st.executeQuery();
		 		if (!rs.isBeforeFirst()) 
		 		{    
		 			String failMessage = "Wrong Password!";
		 			PrintWriter out = response.getWriter();
		 			out.println("<script type=\"text/javascript\">");
		 			out.println("alert(\"" + failMessage + "\");");
		 			out.println("location='companylogin.jsp';");
		 			out.println("</script>");
		 		} 
		 		else 
		 		{
		 			HttpSession httpSession = request.getSession();
		            httpSession.setAttribute("susername", cemail);
		            
		 			String cid = "c1";
		 			while(rs.next()) {
		 				cid = rs.getString(1);
		 				break;
		 			}
		 			httpSession.setAttribute("cid", cid);
		 			System.out.println("Welcome Company!"+cid); 
		 			request.setAttribute("cid", cid);
		 			response.sendRedirect("company_afterlogin.jsp?s=cid="+cid);
		 		}

	 			
	 		}
	 		
	 		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

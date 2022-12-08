

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


@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public StudentLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String semail = request.getParameter("semail");
			String spassword = request.getParameter("spassword");
						
			Connection con = null;
	 		String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
	 		
	 		PreparedStatement st_user = con.prepareStatement("select * from student where susername=?");
	 		st_user.setString(1,semail);
			ResultSet rs_user = st_user.executeQuery();
	 		if (!rs_user.isBeforeFirst()) 
	 		{    
	 			String failMessageUser = "User doesn't exist! Please SignUp first!";
	 			System.out.println("Hii");
	 			PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + failMessageUser + "\");");
	 			
	 			out.println("location='student_signup.jsp';");
	 			out.println("</script>");
	 			System.out.println("Hii there");
	 			
	 		} 
	 		else
	 		{
	 			PreparedStatement st = con.prepareStatement("select * from student where susername=? and spassword=?");
		 		st.setString(1,semail);
				st.setString(2,spassword);
				ResultSet rs = st.executeQuery();
		 		if (!rs.isBeforeFirst()) 
		 		{    
		 			String failMessage = "Wrong Password!";
		 			PrintWriter out = response.getWriter();
		 			out.println("<script type=\"text/javascript\">");
		 			out.println("alert(\"" + failMessage + "\");");
		 			out.println("location='student_login.jsp';");
		 			out.println("</script>");
		 		} 
		 		else 
		 		{
		 			HttpSession httpSession = request.getSession();
		            httpSession.setAttribute("susername", semail);
		            
		 			String sid = "s1";
		 			while(rs.next()) {
		 				sid = rs.getString(1);
		 				break;
		 			}
		 			httpSession.setAttribute("sid", sid);
		 			System.out.println("Welcome Student!"+sid); 
		 			request.setAttribute("sid", sid);
		 			response.sendRedirect("student_afterlogin.jsp?sid="+sid);
		 		}

	 			
	 		}
	 		
	 		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

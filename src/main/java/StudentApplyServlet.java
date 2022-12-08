

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
 * Servlet implementation class StudentApply
 */
@WebServlet("/StudentApplyServlet")
public class StudentApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String sid = request.getParameter("sid");
			String jobid = (String)request.getParameter("job_id");
			String compid = (String)request.getParameter("comp_id");
			Double scpi = Double.parseDouble(request.getParameter("scpi"));
			String swh = (String)request.getParameter("swhyhire");
			
			System.out.println(jobid + " " + compid);

			Connection con = null;
			String url = "jdbc:mysql://localhost:3306/jobportal"; //MySQL URL and followed by the database name
	 		String username = "jobportalDB"; //MySQL username
	 		String pass = "Jobportal@123"; //MySQL password
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pass); //attempting to connect to MySQL database
	 		System.out.println("Printing connection object "+con);
			
			
			PreparedStatement st_check = con.prepareStatement("select * from applications where company_id=? and job_id=? and student_id=?");
			st_check.setString(1,compid);
			st_check.setString(2,jobid);
			st_check.setString(3,sid);
			ResultSet result_check = st_check.executeQuery();
			System.out.println(result_check);
			System.out.println("Hi");
			System.out.println("b");
			
	 		if(result_check.next())
			{
	 			String dupMessage = "Already applied for this job! ";
	 			System.out.println(dupMessage);
	 			PrintWriter out = response.getWriter();
	 			out.println("<script type=\"text/javascript\">");
	 			out.println("alert(\"" + dupMessage + "\");");
	 			System.out.println("Changedd");
	 			out.println("location=\"StudentViewApplicationServlet?sid=" + sid + "\";");
	 			out.println("</script>");
			}
	 		else
	 		{
	 			PreparedStatement st = con.prepareStatement("insert into applications values(?,?,?,?,?,?)");
				st.setString(1,compid);
				st.setString(2,jobid);
				st.setString(3,sid);
				st.setDouble(4,scpi);
				st.setString(5,swh);
				st.setString(6,"Applied");
				int result = st.executeUpdate();
				if(result>0) {
					String dupMessage = "Applied Successfully for this job! ";
		 			System.out.println(dupMessage);
		 			PrintWriter out = response.getWriter();
		 			out.println("<script type=\"text/javascript\">");
		 			System.out.println("Change");
		 			out.println("alert(\"" + dupMessage + "\");");
		 			out.println("location=\"StudentViewApplicationServlet?sid=" + sid + "\";");
		 			out.println("</script>");
					
				}
				else
				{
					String dupMessage = "Something went wrong on the server side! ";
		 			System.out.println(dupMessage);
		 			PrintWriter out = response.getWriter();
		 			out.println("<script type=\"text/javascript\">");
		 			out.println("alert(\"" + dupMessage + "\");");
		 			out.println("location=\"StudentViewApplicationServlet?sid=" + sid + "\";");
		 			out.println("</script>");
					
				}
	 			
	 		}
	 		
	 
	 		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

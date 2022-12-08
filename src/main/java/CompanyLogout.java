

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


@WebServlet("/CompanyLogout")
public class CompanyLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CompanyLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			  
	        // Set the content type of response to "text/html"
	        response.setContentType("text/html");
	  
	        // Invalidate the session.
	        request.getSession(false).invalidate();
	  
	        // Print success message to the user and close the print writer object.
	        System.out.println("<script type='text/javascript'>");
	  		System.out.println("alert(\"Thank you! You are successfully logged out! \");\");</script>");
	  		System.out.println("</head><body></body></html>");
 			response.sendRedirect("home.jsp");
	        out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

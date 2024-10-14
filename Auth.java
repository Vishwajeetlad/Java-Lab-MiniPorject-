import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/auth")
public class Auth extends HttpServlet {
	PreparedStatement pstmt ;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter() ;
		
		String name = req.getParameter("tname") ;
		String pass = req.getParameter("tpass") ;
		
		try {
			ServletContext context = getServletContext();
			Connection con = (Connection) context.getAttribute("mycon") ;
			if( con == null ) {
				pw.print("<h1>First Connect To Database Boi :)</h1>") ;
				pw.print("<a href = 'index.html' > Go To Home Page </a>") ;
				return ;
			}
			String query = "select * from student where name=? and password=?" ;
			pstmt = con.prepareStatement(query) ;
			pstmt.setString(1, name) ;
			pstmt.setString(2, pass) ;
			ResultSet rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				// redirecting to another page
				req.getRequestDispatcher("wc").forward(req, resp) ;
			}
			else {
				pw.print("<h1>Login Failed! Check Username & Password</h1>") ;
				req.getRequestDispatcher("index.html").include(req, resp) ;
			}
		}
		catch (SQLException e) {
			System.out.println("Problem : "+e);
		}
		
	}
}

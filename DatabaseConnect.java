import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbconnect")
public class DatabaseConnect extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded Success!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shreeram", "root", "");
			System.out.println("Connectiion Success...!");
			ServletContext context = getServletContext() ;
			context.setAttribute("mycon", con) ;
			pw.print("<h1>Databse COnnection SuccessFul Baby!</h1>") ;
			pw.print("<a href='index.html'>Go To Home Page</a>") ;
			pw.close() ;
		}
		catch( ClassNotFoundException e ) {
			System.out.println("Class Not Found - Driver Can't be Loaded! : "+e);
		} catch (SQLException e) {
			System.out.println("Authentication Failed : " + e);
		}
	}
	
}

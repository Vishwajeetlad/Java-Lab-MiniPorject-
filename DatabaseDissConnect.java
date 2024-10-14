import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbdissconnect")
public class DatabaseDissConnect extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter() ;
		ServletContext context = getServletContext() ;
		Connection con = (Connection) context.getAttribute("mycon") ;
		if( con == null ) {
			pw.print("<h1> Database Is Not Connected To Discconect it Man!!! :( </h1>") ;
		}
		else {
			context.removeAttribute("mycon") ;
			pw.print("<h1>Database DisConnection Success</h1>") ;
		}
		pw.print("<a href='index.html'> Go To Home Page</a>") ;
		pw.close() ;
	}
}


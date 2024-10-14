import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wc") 
public class Welcome extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		pw.print("<h1>Your Name : "+req.getParameter("tname")+"</h1>") ;
		pw.print("<h1>Login Success!!</h1>") ;
		pw.print("<h1>Welcome To Pccoe</h1>") ;
		pw.print("<a href='index.html'>Go To Home Page") ;
		pw.close();
	}
}

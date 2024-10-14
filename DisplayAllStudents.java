import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayStudents")
public class DisplayAllStudents extends HttpServlet {

	PreparedStatement pstmt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter() ;
		
		pw.print("<head>"
				+ "<style>"
				+ "table {"
				+ "  font-family: arial, sans-serif;"
				+ "  border-collapse: collapse;"
				+ "  width: 100%;"
				+ "}"
				+ "td, th {"
				+ "  border: 1px solid #dddddd;"
				+ "  text-align: left;"
				+ "  padding: 8px;"
				+ "}"
				+ "tr:nth-child(even) {"
				+ "  background-color: #dddddd;"
				+ "}"
				+ "</style>"
				+ "</head>") ;
		
		
		try {
			ServletContext context = getServletContext();
			Connection con = (Connection) context.getAttribute("mycon") ;
			if( con == null ) {
				pw.print("<h1>First Connect To Database Boi :)</h1>") ;
				pw.print("<a href = 'index.html' > Go To Home Page </a>") ;
				return ;
			}
			
			String query = "select * from student" ;
			pstmt = con.prepareStatement(query) ;
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData() ;
			
			pw.print("<h1>All Student Are</h1>") ;
			pw.print("<table>") ;
			
			if( rs.next() ) {
				pw.print("<tr>") ;
				for( int i = 1 ; i < rsmd.getColumnCount() ; i++ ) 
						pw.print("<th>"+rsmd.getColumnName(i).toUpperCase()+"</th>") ;
				
				pw.print("</tr>") ;
				rs.previous() ; // jar nahi kela tar ek row skip honar mhanun
					
				while( rs.next() ) {
					pw.print("<tr>") ;
					// here i could have taken condn -> i<= but i don't want to show passwords so...
					for( int i = 1 ; i < rsmd.getColumnCount() ; i++ ) 
						pw.print("<td>"+rs.getString(i)+"</td>") ;
					pw.print("</tr>") ;
				}
			}
			else {
				pw.print("<tr>") ;
				pw.print("<th>Students Records Not Found!!</th>") ;
				pw.print("</tr>") ;
			}
			pw.print("</table>") ;
		}
		catch (SQLException e) {
			System.out.println("Problem Found - : "+e);
		}
		
	}

}

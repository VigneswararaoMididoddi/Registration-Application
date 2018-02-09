import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class  RegistrationServlet  extends  GenericServlet
{
	Connection con;
	PreparedStatement ps;
	public void  init(ServletConfig config)
	{
		try
		{
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	       con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
           ps=con.prepareStatement("INSERT INTO REGISTARATION VALUES(?,?,?)");
		}
		catch (ClassNotFoundException e)
		{
           System.out.println(e);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}//init
	public void destroy()
	{
		try
		{
		    ps.close();
			con.close();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}//destroy
	public void service(ServletRequest request,ServletResponse response) throws  IOException
	{
       String  username=request.getParameter("user");
       String  password=request.getParameter("pwd");
       String  emailid=request.getParameter("mailid");
	   try
	   {
		  ps.setString(1,username);
		  ps.setString(2,password);
	      ps.setString(3,emailid);
	      ps.executeUpdate();
	   }
	   catch (SQLException e)
	   {
		   System.out.println(e);
	   }
	   response.setContentType("text/html");
	   PrintWriter out=response.getWriter();
	   out.println("<HTML>");
	   out.println("<BODY BGCOLOR=yellow>");
       out.println("<H1>Registration Successful</H1>");
	   out.println("</BODY>");
	   out.println("</HTML>");
	   out.close();
	}//service
}

package Servlet;

import Services.connection;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.lang.*;


/*@WebServlet("/login")*/
public class login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

    public login() {
        super();
       
    }
   
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		System.out.println("Received");
		
		String un = request.getParameter("email");
		String pw = request.getParameter("pass");
		
		
		Connection con = connection.conn();
		
		if(con != null)
		{
			System.out.println("connection OK");
			String qry = "SELECT * FROM user";
			
			try {
				
				Statement st = con.createStatement();
				ResultSet rslt = st.executeQuery(qry);
				
				Boolean rs = Validate(rslt,un,pw);
				
				System.out.println("Sended Username : "+un);
				System.out.println("Sended Password : "+pw);

				if(rs == true)
				{
					response.sendRedirect("http://google.com");
				}
				else
				{
					writer.println("Wrong un or pw");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else
		{
			System.out.println("connection Unsuccessfull");
		}
		
	}
	
	public Boolean Validate(ResultSet rs, String un, String pw) throws SQLException
	{
		String uname, pword;
		while(rs.next())
		{
			uname = rs.getString("uname");
			pword = rs.getString("password");
			
			if(uname.equals(un) && pword.equals(pw))
			{
				System.out.println("Found, Unsername : "+uname+"/t Password : "+pword);
				return true;
			}
		}
		return false;
	}


}

package Services;
import java.sql.*;

public class connection 
{
	public static Connection conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/itpm","root","");  
			return con;
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	

}

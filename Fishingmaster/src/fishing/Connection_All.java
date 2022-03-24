package fishing;

import java.sql.Connection;	
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection_All

{
	public static Connection tryConnection()
	{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jeon";
		String password = "1234";
		
		Connection conn = null;
		
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{
		try
		{
			if(conn != null)
			{
				conn.close();
			}
			if(pstmt != null)
			{
				pstmt.close();
			}
			if(rs != null)
			{
				rs.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	public static Connection conexion(String url, String user, String paswd)
	{
		try 
		{
			Connection con = DriverManager.getConnection(url,user,paswd);
			System.out.println("Conexion establecida");
			return con;
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static void desconexion(Connection con)
	{
		try 
		{
			if(con != null) 
			{
				con.close();
				System.out.println("Desconectado");
			}	
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
			
	}
}
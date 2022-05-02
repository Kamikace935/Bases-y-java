package america;

import utils.Conexion;
import utils.Constantes;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class DatAccess 
{
	public static void fillPeople(Connection con)
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD);
		
		String insercion = "INSERT INTO personaspaises(id, nombre, apellido, edad, pais, tamanio) "
				+ "VALUES (?,?,?,?,?,?)";
		
		try(PreparedStatement pstmt = con.prepareStatement(insercion);
				BufferedReader bf = new BufferedReader(new FileReader(Constantes.DATOS));
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery(bf.readLine()))
		{
			while(rs.next()) 
			{
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setString(2, rs.getString(2));
				pstmt.setString(3, rs.getString(3));
				pstmt.setInt(4, rs.getInt(4));
				pstmt.setString(5, rs.getString(5));
				pstmt.setString(6, rs.getString(6));
				
				pstmt.executeUpdate();
			}
			
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void growthAge(Connection con)
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD);
		
		try(Statement st = con.createStatement()){
			
			st.executeUpdate("UPDATE personaspaises "
							+ "SET edad = (edad + 1)");
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void prinTable(Connection con) 
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD);
		
		try(Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT * FROM personaspaises"))
		{
			System.out.println("Id\tNombre\tApellido\tEdad\tPais\t\tTamaño");
			
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"   \t"
									+rs.getInt(4)+"\t"+rs.getString(5)+"   \t"+rs.getString(6));
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
}
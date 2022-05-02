package instituto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Conexion;
import utils.Constantes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccesoDato 
{

	public static void useDB(Connection con,String db) 
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URL, Constantes.USER, Constantes.PWD);
		
		try(Statement st = con.createStatement())
		{
			
			st.execute("USE "+db);
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void delTable(Connection con, String tb)
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD);
		
		try(Statement st = con.createStatement()){
			st.execute("DROP TABLE IF EXISTS " + tb);
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void makeTable(Connection con) 
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD );
		
		try(BufferedReader bf = new BufferedReader(new FileReader(Constantes.TABLAS));
				Statement st = con.createStatement();)
		{
			String linea = bf.readLine();
			
			while(linea !=null && !linea.equals("")) 
			{
				System.out.println("Tabla\n"+linea+"\ncreada");
				st.execute(linea);
				
				linea = bf.readLine();
			}
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	public static void fillAVG(Connection con) 
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD );
		
		String insercion = "INSERT INTO notasfinales(mat, cod, notamedia) VALUES(?,?,?)";
		
		try(BufferedReader bf = new BufferedReader(new FileReader(Constantes.DATOS));
				PreparedStatement pstmt = con.prepareStatement(insercion);
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery(bf.readLine()))
		{
			
			while(rs.next()) 
			{
				pstmt.setString(1,rs.getString(1));
				pstmt.setInt(2, rs.getInt(2));
				pstmt.setDouble(3, rs.getDouble(3));
				
				pstmt.executeUpdate();
			}
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void listAlum(Connection con) 
	{
		if(con == null)
			con = Conexion.conexion(Constantes.URLF, Constantes.USER, Constantes.PWD );
		
		try(Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT al.apel_nom, a.nombre, nt.nota1, nt.nota2, nt.nota3, nf.notamedia "
												+ "FROM notas nt "
												+ "JOIN alumnos al ON(al.mat = nt.mat) "
												+ "JOIN asignaturas a ON(nt.cod = a.cod) "
												+ "JOIN notasfinales nf ON(nt.mat = nf.mat)");)
		{
			
			System.out.println("\t\tNombre Alumno\t\t\tNombre Asignatura\t\tNota1\t\tNota2\t\tNota3\t\tNota Media\n");
			while(rs.next()) 
			{
				System.out.println("\t\t"+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"
									+ rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5)+"\t\t"+rs.getDouble(6));
				
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
	}
}
package empresa;

import utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Manager 
{
	 
	
	public static Connection conexion()
	{
		try {
			
			Connection con = DriverManager.getConnection(Constantes.URLF, Constantes.USER, Constantes.PWD);
			
			JOptionPane.showMessageDialog(null, "Conexion establecida");
			
			return con;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	static void reconexion(Connection con)
	{
		int reconexion = JOptionPane.YES_NO_OPTION;
		
		JOptionPane.showMessageDialog(null,"Estas desconectado");
		JOptionPane.showConfirmDialog(null, "¿Quieres reconectarte ahora?",null,reconexion);
		
		if(reconexion ==JOptionPane.YES_OPTION)
			con = conexion();
		else
			JOptionPane.showMessageDialog(null, "Regresando al menu");
	}
	
	static void desconexion(Connection con)
	{
		try 
		{
			if(con != null)
				con.close();
			
				JOptionPane.showMessageDialog(null, "Cerrando el programa");
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	public static void insertarDepto(Connection con, int depto, String name, String loc)
	{
		if(con != null) 
		{
			String insercion = "INSERT INTO departamentos(dept_no, dnombre, loc) VALUES(?,?,?)";
			
			try(PreparedStatement pstmt = con.prepareStatement(insercion);)
			{
				pstmt.setDouble(1, depto);
				pstmt.setString(2, name);
				pstmt.setString(3, loc);
				
				pstmt.executeUpdate();
				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}catch(Exception e) {e.printStackTrace();}
			
			
		}else 
			reconexion(con);
	}

	public static void insertarDepto(Connection con, Departamento depto) 
	{
		if(con != null) 
		{
			String insercion = "INSERT INTO departamentos(dept_no, dnombre, loc) VALUES(?,?,?)";
			
			try(PreparedStatement pstmt = con.prepareStatement(insercion);)
			{
				pstmt.setDouble(1, depto.getDeptno());
				pstmt.setString(2, depto.getName());
				pstmt.setString(3, depto.getLoc());
				
				pstmt.executeUpdate();
				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}catch(Exception e) {e.printStackTrace();}
		}else 
			reconexion(con);
		
	}
	
	public static ArrayList<Departamento> listDepto(Connection con)
	{
		ArrayList<Departamento> deptos = new ArrayList<Departamento>();
		
		if(con != null) 
		{
			try(Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = st.executeQuery("SELECT * FROM departamentos"))
			{
				while(rs.next()) 
				{
					Departamento depto = new Departamento(rs.getInt(1), rs.getString(2), rs.getString(3));
					
					deptos.add(depto);
				}
				
				return deptos;
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}catch(Exception e) {e.printStackTrace();}
			
		}else 
			reconexion(con);
		
		return deptos;
	}
	
	public static void borrarDeptno(Connection con, int deptno)
	{
		if(con != null) 
		{
			try(Statement st = con.createStatement()){
				st.execute("DELETE FROM departamentos WHERE dept_no = "+deptno);
				JOptionPane.showMessageDialog(null, "Departamento "+deptno+" eliminado");
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}catch(Exception e) {e.printStackTrace();}
			
		}else 
			reconexion(con);
	}
	
	public static void actulzDepto(Connection con, int deptno,String loc)
	{
		if(con != null) 
		{
			System.out.println();
		}else 
			reconexion(con);
	}

	public static void actulzDepto(Connection con, Departamento depto)
	{
		if(con != null) {
			
		}else 
			reconexion(con);
	}
	
	public static void devolverDepto(Connection con, int deptno)
	{
		if(con != null) {
			
		}else 
			reconexion(con);
	}
	
	public static void subirSalario(Connection con, int sal, int deptno)
	{
		if(con != null) {
			
		}else 
			reconexion(con);
	}
}
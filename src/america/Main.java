package america;

import utils.Conexion;
import utils.Constantes;

import instituto.AccesoDato;

import java.sql.Connection;

public class Main 
{
	
	public static void main(String[] args) 
	{
		Connection con = Conexion.conexion(Constantes.URL, Constantes.USER, Constantes.PWD);
		
		AccesoDato.useDB(con, "america");
		AccesoDato.delTable(con , "personasPaises");
		AccesoDato.makeTable(con);
		DatAccess.fillPeople(con);
		DatAccess.growthAge(con);
		DatAccess.prinTable(con);
		
		Conexion.desconexion(con);
	}
	
	
}

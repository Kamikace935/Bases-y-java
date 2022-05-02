package instituto;

import java.sql.Connection;

import utils.Conexion;
import utils.Constantes;

public class Main 
{

	public static void main(String[] args) 
	{
		Connection con = Conexion.conexion(Constantes.URL, Constantes.USER, Constantes.PWD);
		
		AccesoDato.useDB(con, "instituto");
		AccesoDato.delTable(con, "NotasFinales");
		AccesoDato.makeTable(con);
		AccesoDato.fillAVG(con);
		AccesoDato.listAlum(con);

		Conexion.desconexion(con);
	}

}
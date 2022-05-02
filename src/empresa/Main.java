package empresa;

import java.sql.Connection;

import javax.swing.JOptionPane; 

import java.util.ArrayList;
public class Main
{

	public static void main(String[] args) 
	{
		menu();
	}

	static void menu()
	{
		int deptno;
		String name,loc;
		String ldept = "Lista de Departamentos\n";
		boolean run = true;
		Connection con = null;
		ArrayList<Departamento> deptos;
		
		int option = Integer.parseInt(JOptionPane.showInputDialog("Introduce una opción:"
				+ "1.-Conectar base de datos."
				+ "2.-Insertar un departamento."
				+ "3.-Listar departamentos"
				+ "4.-Eliminar un Departamento."
				+ "5.-"));
		
		while(run) 
		{
			switch(option)
			{
				case 0:
					Manager.desconexion(con);
					run = false;
					break;
				case 1:
					con = Manager.conexion();
					break;
				case 2:
					deptno = Integer.parseInt(JOptionPane.showInputDialog("Introdcue un número de departamento"));
					name = JOptionPane.showInputDialog("Introduce el nombre del departamento");
					loc = JOptionPane.showInputDialog("Introduce la localización");
					
					Manager.insertarDepto(con, deptno, name, loc);
					break;
				case 3:
					deptos = Manager.listDepto(con);
					
					for(int i=0; i<deptos.size();i++) 
						ldept += deptos.get(i).toString();
					
					JOptionPane.showMessageDialog(null, ldept);
					ldept = "Lista de Departamentos\n";
					break;
				case 4:
					deptno = Integer.parseInt(JOptionPane.showInputDialog("Introdcue el número de departamento"
							+ "que desea eliminar"));
					
					Manager.borrarDeptno(con, deptno);
					break;
				case 5:
					deptno = Integer.parseInt(JOptionPane.showInputDialog("Introdcue un número de departamento"
							+ "al que quieres actualizar la localización"));
					loc = JOptionPane.showInputDialog("Introduce la nueva localización");
					
					Manager.actulzDepto(con, deptno, loc);
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					//InfoDB();
				case 10:
					//InfoTables();
				case 11:
					//String query = JOptionPane.showInputDialog("Introduce una consulta");
					//dataTable(String query);
				default:
					JOptionPane.showMessageDialog(null, "Opción introducida no disponible");
			}
		}
	}
}
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
		double sal;
		String name,loc;
		String ldept = "Lista de Departamentos\n";
		boolean run = true;
		Connection con = null;
		ArrayList<Departamento> deptos;
		
		int option = Integer.parseInt(JOptionPane.showInputDialog("Introduce una opción:"
				+ "\n1.-Conectar base de datos."
				+ "\n2.-Insertar un departamento."
				+ "\n3.-Listar departamentos"
				+ "\n4.-Eliminar un Departamento."
				+ "\n5.-Actualizar la localización de un departamento"
				+ "\n6.-Subir el salario a un departamento"));
		
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
					deptno = Integer.parseInt(JOptionPane.showInputDialog("Introdcue el número de departamento "
							+ "al que quieras subir el sueldo"));
					sal = Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad que quieres aumentar"));
					
					Manager.subirSalario(con, sal, deptno);
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
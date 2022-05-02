package empresa;

public class Departamento 
{
	private int deptno;
	private String name;
	private String loc;
	
	public Departamento() {}
	
	public Departamento(int deptno, String name, String loc)
	{
		this.deptno = deptno;
		this.name = name;
		this.loc = loc;
	}
	
	
	public int getDeptno() {
		return deptno;
	}
	
	public void setDeptno(int deptno)
	{
		this.deptno = deptno;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getLoc() 
	{
		return loc;
	}
	
	public void setLoc(String loc) 
	{
		this.loc = loc;
	}
	
	public String toString() 
	{
		return "Número: "+deptno+"\nNombre: "+name+"\nLocalización: "+loc+"\n\n";
	}
}
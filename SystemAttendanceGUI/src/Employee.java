
public class Employee {
	private int ID;
	private String name;
	
	public Employee() {}
	public Employee(int ID,String name) {
		this.ID = ID;
		this.name = name;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "ID: "+ID+"\n"+"name: "+name+"\n";
	}
}

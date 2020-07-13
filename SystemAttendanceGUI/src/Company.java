import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Company{
	List<Employee> employees = new ArrayList<Employee>();
	Map<Integer,DakaInfo>attendanceMessage = new HashMap<Integer,DakaInfo>();
	
	public Company() {
		this.employees=new ArrayList<Employee>();
	}
	
	//添加员工
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
		System.out.println("员工信息添加成功");
	}
	
	//删除员工
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
		System.out.println("员工信息删除成功");
	}
	
	//查找员工
	public Employee getEmployee(int ID) {
		for(Employee employee : this.employees) {
			if(employee.getID()==ID) {
				return employee;
			}
		}
		return null;
	}
	
	//遍历员工信息
	public void showEmployee() {
		for(Employee employee : this.employees) {
			System.out.println(employee.toString());
		}	
	}
}

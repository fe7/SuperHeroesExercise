
/**
 * @author Faizan
 * 
 * Class for employees object
 * 
 */

public class Employee {
	private String name;
	private int eID, mID;

	public Employee(int employeeID, String employeeName, int managerID) {
		super();
		this.eID = employeeID;
		this.name = employeeName;
		this.mID = managerID;
 	}

	public void setEmployeeID(int employeeID) {
		this.eID = employeeID;
	}
	
	public void setEmployeeName(String employeeName) {
		this.name = employeeName;
	}
	
	public void setManagerID(int managerID) {
		this.mID = managerID;
	}
	
	public int getEmployeeID() {
		return eID;
 	}
	
	public String getEmployeeName() {
		return name;
	}
 	
 	public int getManagerID() {
 		return mID;
 	}
}
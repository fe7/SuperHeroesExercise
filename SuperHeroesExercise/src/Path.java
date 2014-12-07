import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Faizan
 * 
 * Finds the path between 2 employees using the employee list
 * 
 */

public class Path {

	// toReturnPath is used in self-calling recursive function findEmployeePath
	static ArrayList<Employee> toReturnPath = new ArrayList<Employee>();

	// Handler method calls other methods and prints the answer (does not return)
	public static void handler(String employee1, String employee2, ArrayList<Employee> employeeList){

		// check if employee names actually exist in the list provided (employeeList)
		if (employeeExist(employee1, employeeList) && employeeExist(employee2, employeeList)){
			ArrayList<Employee> tempEmployee1 = getEmployeeByName(employee1,employeeList);
			ArrayList<Employee> tempEmployee2 = getEmployeeByName(employee2,employeeList);
			ArrayList<Employee> tempEmployeePath1, tempEmployeePath2;

			/* checks all employees given
			 * makes sure if different employee with same name exist are also exploited
			 */
			for (Employee e1: tempEmployee1){
				for (Employee e2: tempEmployee2){

					// to reset the path in memory before checking for other employee ALWAYS BEFORE findEmployeePath()
					toReturnPath = new ArrayList<Employee>();
					// path from employee1 to ancestors found and stored temporarily 
					tempEmployeePath1 = findEmployeePath(e1, employeeList, null);

					// to reset the path in memory before checking for other employee
					toReturnPath = new ArrayList<Employee>();
					// path from employee2 to ancestors found and stored temporarily 
					tempEmployeePath2 = findEmployeePath(e2, employeeList, null);

					// outputs the path between employees
					System.out.println(buildFullPath(findCommonAncestor(tempEmployeePath1, tempEmployeePath2), tempEmployeePath1, tempEmployeePath2));
				}
			}
		} else { // when employee(s) do not exist in file, to help user of program

			// when employee1 does not exist in file:
			if (!employeeExist(employee1, employeeList)){
				System.out.println(employee1 + " does not exist in file.");
			}

			// when employee2 does not exist in file:
			if (!employeeExist(employee2, employeeList)){
				System.out.println(employee2 + " does not exist in file.");
			}

		}// end if else
	}// end handler class

	/* build path from list one (l1) + common ancestor (common) + list two (l1)
	 * input parameters l1 and l2 will always be lists of employees to common ancestor
	 * parameter common is the common ancestor lowest in chain employees 1 and 2 have in common
	 */
	public static String buildFullPath(Employee common, ArrayList<Employee> l1, ArrayList<Employee> l2){
		/* Overall structure of this method:
		 * take l1 keep adding its elements until reach common ancestor.
		 * add common ancestor
		 * reverse and skip items including and up till common ancestor from l2 until, add rest elements
		 * return elements in form of string 
		 */

		// to return string in which elements will be added
		StringBuilder ans = new StringBuilder();

		boolean l1found= false;
		for (Employee e1: l1){
			if (e1 != common && !l1found){
				ans.append(e1.getEmployeeName()+" ("+e1.getEmployeeID()+") -> ");
			} else {
				if (e1 == common){
					ans.append(e1.getEmployeeName()+" ("+e1.getEmployeeID()+")");
					l1found = true;
				}
			}
		}

		Collections.reverse(l2);
		boolean l2continue = false;
		for (Employee e2: l2){
			if (!l2continue){
				if (e2 == common) {
					l2continue = true;
				}
			} else {
				ans.append(" <- "+e2.getEmployeeName()+" ("+e2.getEmployeeID()+")");
			}

		}
		return ans.toString();
	}

	/* for debugging purposes: prints every employee name from list
	public static void printPath(ArrayList<Employee> l){
		for (Employee e1: l){
			System.out.println(e1.getEmployeeName());
		}
	}
	 */

	// from 2 lists in parameters, finds the common ancestor else returns null
	public static Employee findCommonAncestor(ArrayList<Employee> list1, ArrayList<Employee> list2){
		for (Employee e1: list1){
			for (Employee e2: list2){
				if (e1.getEmployeeID()==e2.getEmployeeID()){
					return e1;
				}
			}
		}
		return null;
	}

	/* recursive self-calling method
	 * returns arrayList of employees in order from employee e until reach root (ie. highest employee in chain)
	 */
	public static ArrayList<Employee> findEmployeePath(Employee e, ArrayList<Employee> list, ArrayList<Employee> toReturn){
		// ASSUMPTION: manager ID cannot be a negative value as all employee ID are also assumed to be positive integer values
		if (e != null && list != null && e.getManagerID() >= 0){
			toReturnPath.add(e);
			for (int i = 0; i < list.size(); i++) {
				// if the employeeID of the next person up in the chain is same as managerID of current employee:
				if (list.get(i).getEmployeeID() == e.getManagerID()) {
					findEmployeePath(list.get(i), list, toReturn);
				}
			}
		}
		return toReturnPath;
	}

	// checks if String (ie. name) given is present in employee list from file
	public static boolean employeeExist(String name, ArrayList<Employee> list){
		for(Employee e: list){
			if(stringsEqual(name, e.getEmployeeName())){
				return true;
			}
		}
		return false;		
	} 

	/* returns employees with string name in employee list from file
	 * can return more than one employee with same name in the Arraylist
	 */
	public static ArrayList<Employee> getEmployeeByName(String name, ArrayList<Employee> list){
		ArrayList<Employee> toReturnEmployee = new ArrayList<Employee>();

		// for each loop
		// for each employee e in the employee List do {...}
		for(Employee e: list){
			if(stringsEqual(e.getEmployeeName(), name)){
				toReturnEmployee.add(e);
			}
		}
		return toReturnEmployee;		
	}

	// return employee from given employee ID
	public static Employee getEmployeeByID(int employeeID, ArrayList<Employee> list){
		// for each loop (for each e in employee list)...
		for(Employee e: list){
			if(e.getEmployeeID() == employeeID){
				return e;
			}
		}
		return null;
	}

	/* Checks if two strings are same
	 * SPECIFICATION: 	quote: "When comparing names, the case of letters is not significant, 
	 * 		  	and neither are leading or trailing spaces or runs of multiple spaces." 
	 * 			so 'JAmEs', 'James' and ' james ' are all equivalent 
	 * 			However 'James' and 'J ames' are not equivalent as split the actual word
	 * ASSUMPTION: upper and lower cases are treated same,
	 * 				name entered will be full name and will not be asked 
	 * 				to compare 'firstname' vs 'firstname lastname' will fail in such test,
	 * 				removes spaces from edges and replaces 2 or more spaces within with one
	 */
	public static boolean stringsEqual(String s1, String s2) {
		return s1.trim().replaceAll(" +"," ").equalsIgnoreCase(s2.trim().replaceAll(" +"," "));
	}// end stringsEqual

}// end handler

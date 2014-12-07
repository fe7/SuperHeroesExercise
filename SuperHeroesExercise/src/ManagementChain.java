import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author Faizan
 *
 */

public class ManagementChain {

	// error messages predefined
	static String errorInput = "Invalid input. Please refer to README file.";
	static String errorFile = "Input file has invalid syntax for data. Please check and refer to the README file.";

	public static void main(String[] input) throws Exception {
		if (input.length > 0) {
			if(input[0] != null && input[1] != null && input[2] != null) {
				try {
					String file = input[0];
					String employee1 = input[1];
					String employee2 = input[2];

					// passing employee names and arraylist of all employees from list to find path
					Path.handler(employee1, employee2, readTextFile(file));

				} catch (Exception e) {
					System.out.println("Coding error: bug found. Sorry for the inconvenience.");
				}
			}
		} else {
			System.out.println(errorInput);
		}
	}

	// reads input file and returns arrayList with all the Employees from input file
	public static ArrayList<Employee> readTextFile(String input) throws IOException{
		ArrayList<Employee> toReturnEmployees = new ArrayList<Employee>();
		try {
			FileReader file = new FileReader (input);
			BufferedReader reader = new BufferedReader(file);
			String line, employeeName;
			int employeeID, managerID;
			int x = 0; 
			/*
			 * SPECIFICATION: separated by | are in order employee ID, employee name and employees managerID on each line 
			 * 					managerID will be an integer if given an ID, otherwise empty field means employee has no manager
			 * ASSUMPTION: first line is always headings and does not contain other data
			 */
			while ((line = reader.readLine()) != null){
				if (x >= 1){
					String temp1 = line.split("\\|")[1].trim();
					String temp2 = line.split("\\|")[2].trim();
					String temp3 = line.split("\\|")[3].trim();

					/* 
					 * SPECIFICATION & ASSUMPTION: employee ID is integer value, employee name is not integer, manager Id is integer if present.
					 */
					if (isInteger(temp1)){
						employeeID = Integer.parseInt(temp1);
					} else {
						System.out.println(errorFile);
						break;
					}

					if (!isInteger(temp2)){
						employeeName = temp2;
					} else {
						System.out.println(errorFile);
						break;
					}

					if (isInteger(temp3)){
						managerID = Integer.parseInt(temp3);
					} else {
						managerID = 0;
					}

					// tempIntern is just employee stored temporarily and added to list toReturnEmployees
					Employee tempIntern = new Employee(employeeID, employeeName, managerID);
					toReturnEmployees.add(tempIntern);
				}
				x++;
			}
			reader.close(); // to release system resources
		} catch (Exception e){
			e.printStackTrace();
		}
		return toReturnEmployees;
	}

	// checks if the given String Input is int(ie. number) or string(ie. name word etc)
	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}

}
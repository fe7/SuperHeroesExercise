import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Faizan
 * 
 * Tests for Path class
 * 
 */

public class PathTest<Junit> {

	Employee e1 = new Employee(1, "Dangermouse", 0);
	Employee e2 = new Employee(2, "Gonzo the Great", 1);
	Employee e3 = new Employee(3, "Invisible Woman", 1);
	Employee e4 = new Employee(6, "Black Widow", 2);
	Employee e5 = new Employee(12, "Hit Girl", 3);
	Employee e6 = new Employee(15, "Super Ted", 3);
	Employee e7 = new Employee(16, "Batman", 6);
	Employee e8 = new Employee(17, "Catwoman", 6);
	Employee e9 = new Employee(18, "Catwoman", 6);
	Employee e10 = new Employee(17, "Batman Surname", 6);
	
	ArrayList<Employee> l1 = new ArrayList<Employee>(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10));
	ArrayList<Employee> l2NoNamesake = new ArrayList<Employee>(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8));
	
	
	@Test
	public void testEmployeeExist() {
		Assert.assertTrue(Path.employeeExist(e1.getEmployeeName(), l1));
		Assert.assertTrue(Path.employeeExist("Gonzo the  Great", l1));
		Assert.assertTrue(Path.employeeExist("                        Gonzo the  Great", l1));
		Assert.assertFalse(Path.employeeExist("Gon zo the Great", l1));
		Assert.assertTrue(Path.employeeExist("Gonzo the  Great   ", l1));
	
	}
	
	@Test
	public void testCommonAncestor() {
		ArrayList<Employee> t1 = Path.findEmployeePath(e7, l1, null);
		ArrayList<Employee> t2 = Path.findEmployeePath(e6, l1, null);
		
		Employee common1 = Path.findCommonAncestor(t1, t2);
		Employee common2 = Path.findCommonAncestor(t1, t2);
		Assert.assertTrue(common1.equals(common2));
		
	}
	
	@Test
	public void testGetEmployeeByName() {
		
		// Dangermouse is e1
		ArrayList<Employee> expected = new ArrayList<Employee>(Arrays.asList(e1));
		ArrayList<Employee> testResult = Path.getEmployeeByName("Dangermouse", l1);
		Assert.assertEquals(testResult,expected);
	}

	@Test
	public void testGetEmployeeByID() {
		
		// Dangermouse is e1
		Employee testEmployee = Path.getEmployeeByID(e1.getEmployeeID(), l1);
		Assert.assertEquals(testEmployee,e1);
	}

	
}

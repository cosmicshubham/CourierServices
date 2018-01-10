package courierServices.ContainerClasses;

import java.io.Serializable;
import java.util.ArrayList;

import courierServices.baseClasses.Employee;
import courierServices.helperClasses.EmpType;
import courierServices.helperClasses.IO;
import courierServices.helperClasses.Location;

/**
 * Class representing all employees.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Employee Initializer
 * Array list of Employee class objects
 * 
 * @MemberMethods
 * Constructor,
 * Get array index function,
 * Add new employee,
 * Authenticate Employee,
 * Copy object function,
 * Print All Employees,
 * Remove Employee,
 * 
 * @author Shubham Kumar
 *
 */
public class Employees implements Serializable {

	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Employee Initializer:
	 * Provides base (minimum) value for employee ID
	 * Also stores last created employee ID.
	 */
	public static long EMPLOYEE_INITIALIZER = 6587425;

	/**
	 * Prints table header,
	 * When list of all employees is printed as ToString 
	 * this function prints table header.
	 */
	public static void printListHeader() {
		System.out.println("EMP_ID" + "||" + "EMP_NAME" + "||" + "EMP_TYPE"
				+ "||" + "CITIZEN_ID" + "||" + "CITY" + "||" + "LOCAL_ADDRESS"
				+ "||" + "PHONE" + "||" + "EMAIL_ID");
	}

	/**
	 * Array list of employees,
	 * Contains Employee Class objects.
	 */
	protected ArrayList<Employee> employeesList;

	/**
	 * Constructor : Initialize arraylist to empty list
	 */
	public Employees() {
		this.employeesList = new ArrayList<>();
	}

	/**
	 * Finds and returns Employees arraylist's index for a particular customer, 
	 * @param empId - Employee ID
	 * @return  Arraylist Index (if found), -1 (if not found)
	 */
	private int getArrayIndex(long empId) {
		for (int i = 0; i < this.employeesList.size(); i++) {
			if (this.employeesList.get(i).get_empId() == empId) {
				return i;
			}
		}

		return -1;

	}

	/**
	 * Add New Employee:
	 * Takes input from console and generates new Employee
	 * by adding newly created Employee class object to arraylist.
	 * 
	 * New Employee ID is selected using static EMPLOYEE INITIALIZER variable,
	 * after creation EMPLOYEE INITIALIZER variable is incremented by 1.
	 * 
	 * @return Employee ID of created Employee.
	 */
	public long addNewEmployee() {

		long empId;
		String empPassword;
		EmpType type;
		String citizenID;
		String empName;
		Location city;
		String localAddress;
		String phone;
		String emailID;

		try {
			empName = IO.getInput("Name : ");
			citizenID = IO.getInput("Citizen ID : ");
			empPassword = IO.getInput("Create Password : ");
			type = EmpType.getEmpType(IO.getInput(
					"Enter Type flag character (a-admin,o-office,f-field) : ")
					.charAt(0));
			city = Location.getLocation(IO.getInput("City: "));
			localAddress = IO.getInput("Local Address : ");
			phone = IO.getInput("Phone : ");
			emailID = IO.getInput("E-mail ID : ");
			empId = EMPLOYEE_INITIALIZER;

		} catch (NumberFormatException e) {
			System.out.println("Wrong input format, " + e.getMessage());
			return -1;
		}

		this.employeesList.add(new Employee(empId, empPassword, type,
				citizenID, empName, city, localAddress, phone, emailID));
		System.out.println(" Employee Account created with EmployeeID  : "
				+ EMPLOYEE_INITIALIZER);
		EMPLOYEE_INITIALIZER++;
		return empId;

	}

	/**
	 * Checks whether details provided exists.
	 * @param empId - Employee ID
	 * @param empPass - Password
	 * @return EmpType (Enum class)
	 */
	public EmpType authenticateEmployee(long empId, String empPass) {

		// default admin
		if (empId == 1234 && empPass.equals("password")) {
			return EmpType.ADMIN;
		}

		for (int i = 0; i < this.employeesList.size(); i++) {
			if (this.employeesList.get(i).get_empId() == empId
					&& this.employeesList.get(i).get_empPassword()
							.equals(empPass)) {
				return this.employeesList.get(i).get_type();
			}
		}

		return EmpType.UNSPECIFIED;
	}

	/**
	 * Function to copy Employees object.
	 * It assigns arraylist of passed object to caller object. 
	 * @param object - Employee object whose value to be equated
	 * @return this - in case of a condition a = b.copy(c);
	 */
	public Employees copy(Employees object) {
		this.employeesList = object.employeesList;
		return this;
	}

	/**
	 * Prints all Employee details, with all details of single Employee
	 * in a single row.
	 * It calls ToString method of Employee class for every Employee. 
	 */
	public void printAllEmployees() {
		for (int i = 0; i < this.employeesList.size(); i++) {
			System.out.println(this.employeesList.get(i));
		}
		return;
	}

	/**
	 * Removes employee from employees list by checking empId
	 * @param empId - Employee ID
	 * @return boolean value (true/false)
	 */
	public boolean removeEmployee(long empId) {
		int index = this.getArrayIndex(empId);
		if (index == -1) {
			System.out.println("Employee ID is invalid");
			return false;
		}
		this.employeesList.remove(index);
		return true;
	}

}
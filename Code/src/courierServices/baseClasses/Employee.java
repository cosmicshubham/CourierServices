package courierServices.baseClasses;

import java.io.Serializable;

import courierServices.helperClasses.EmpType;
import courierServices.helperClasses.Location;

/**
 * Class representing a single employee.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Employee ID, Employee Password, Employee Type, Employee's Citizen ID,
 * Employee's Name, City, Local Address, Phone Number, Email ID,
 * 
 * @MemberMethods
 * Constructor,
 * Display Customer Details,
 * Get Employee ID,
 * Get Employee Password,
 * Get Employee Type,
 * ToString,
 * 
 * @author Shubham Kumar
 * 
 */
public class Employee implements Serializable {

	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	protected long _empId;
	protected String _empPassword;
	protected EmpType _type;
	protected String _citizenID;
	protected String _empName;
	protected Location _city;
	protected String _localAddress;
	protected String _phone;
	protected String _emailID;

	/**
	 * Constructor: Takes details as arguments and assigns them to class variables
	 * @param _empId - Employee ID
	 * @param _empPassword - Employee Password
	 * @param _type - Employee Type (admin/office/field)
	 * @param _citizenID - Citizen ID of employee
	 * @param _empName - Employee Name
	 * @param _city - City
	 * @param _localAddress - Local Address
	 * @param _phone - Phone Number
	 * @param _emailID - Email ID
	 */
	public Employee(long _empId, String _empPassword, EmpType _type,
			String _citizenID, String _empName, Location _city,
			String _localAddress, String _phone, String _emailID) {
		this._empId = _empId;
		this._empPassword = _empPassword;
		this._type = _type;
		this._citizenID = _citizenID;
		this._empName = _empName;
		this._city = _city;
		this._localAddress = _localAddress;
		this._phone = _phone;
		this._emailID = _emailID;
	}

	/**
	 * Displays employee details:
	 * ID, CitizenID, Name, Employee type, Address, Phone, E-mail ID,
	 */
	public void displayEmpDetails() {
		System.out.println("Empolyee ID : " + _empId);
		System.out.println("Citizen ID :" + _citizenID);
		System.out.println("Name :" + _empName);
		System.out.println("Employee Type :" + _type);
		System.out.println("Local Address : " + _localAddress);
		System.out.println("City : " + _city);
		System.out.println("Phone : " + _phone);
		System.out.println("Email ID : " + _emailID);
	}

	public long get_empId() {
		return _empId;
	}

	public String get_empPassword() {
		return _empPassword;
	}

	public EmpType get_type() {
		return _type;
	}

	@Override
	public String toString() {
		// ToString method for displaying object directly.
		String s = (this._empId + "||" + this._empName + "||" + this._type
				+ "||" + this._citizenID + "||" + this._city + "||"
				+ this._localAddress + "||" + this._phone + "||" + this._emailID);
		return s;
	}
}

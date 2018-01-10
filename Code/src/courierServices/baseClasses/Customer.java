package courierServices.baseClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import courierServices.helperClasses.Location;

/**
 * Class representing a single customer.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Customer ID, Customer Password, Customer's Citizen ID,
 * Customer's Name, City, Local Address, Phone Number, Email ID,
 * and list of order IDs of orders that customer has placed.
 * 
 * @MemberMethods
 * Constructor,
 * Add order ID to list,
 * Display Customer Details,
 * Get Customer ID,
 * Get Customer Password,
 * Print Order IDs,
 * ToString
 * 
 * @author Shubham Kumar
 * 
 */
public class Customer implements Serializable {

	
	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	protected long _custId;
	protected String _custPassword;
	protected String _citizenID;
	protected String _custName;
	protected Location _city;
	protected String _localAddress;
	
	/**
	 * ArrayList containing Order IDs of orders placed by this customer
	 */
	protected ArrayList<Long> _orders;
	protected String _phone;
	protected String _emailID;

	/**
	 * Constructor: Takes details as arguments and assigns them to class variables
	 * @param _custId - Customer ID
	 * @param _custPassword - Customer Password
	 * @param _citizenID - Citizen ID of Customer
	 * @param _custName - CustomerName
	 * @param _city - City
	 * @param _localAddress - Local Address
	 * @param _phone - Phone Number
	 * @param _emailID - Email ID
	 */
	public Customer(long _custId, String _custPassword, String _citizenID,
			String _custName, Location _city, String _localAddress,
			String _phone, String _emailID) {
		this._custId = _custId;
		this._custPassword = _custPassword;
		this._citizenID = _citizenID;
		this._custName = _custName;
		this._city = _city;
		this._localAddress = _localAddress;
		this._phone = _phone;
		this._emailID = _emailID;
		this._orders = new ArrayList<>();
	}

	/**
	 * Add Order ID in customer details
	 * @param orderID
	 * Order ID of the order that customer has placed 
	 */
	public void addOrderInCustDetails(long orderID) {
		this._orders.add(orderID);
	}

	
	/**
	 * Displays customer details:
	 * ID, CitizenID, Name, Address, Phone, E-mail ID,
	 * list of orders (IDs).
	 */
	public void displayCustomerDetails() {
		System.out.println("Customer ID : " + _custId);
		System.out.println("Citizen ID :" + _citizenID);
		System.out.println("Name :" + _custName);
		System.out.println("Local Address : " + _localAddress);
		System.out.println("City : " + _city);
		System.out.println("Phone : " + _phone);
		System.out.println("Email ID : " + _emailID);

		System.out.print("Orders(IDs) : ");
		this.printOrders();

	}

	public long get_custId() {
		return _custId;
	}

	public String get_custPassword() {
		return _custPassword;
	}

	/**
	 * Prints order IDs of orders that customer has placed.
	 */
	public void printOrders() {
		Iterator<Long> iterator = _orders.iterator();
		if (!(iterator.hasNext())) {
			System.out.println("<no orders yet>");
			return;
		}
		while (iterator.hasNext()) {
			long orderID = iterator.next();
			System.out.print(orderID + " ");
		}
		System.out.println("");
		return;
	}

	@Override
	public String toString() {
		// ToString method for displaying object directly.
		String s = (this._custId + "||" + this._custName + "||"
				+ this._citizenID + "||" + this._city + "||"
				+ this._localAddress + "||" + this._phone + "||" + this._emailID);
		return s;

	}
}

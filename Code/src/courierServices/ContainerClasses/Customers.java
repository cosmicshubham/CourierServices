package courierServices.ContainerClasses;

import java.io.Serializable;
import java.util.ArrayList;

import courierServices.baseClasses.Customer;
import courierServices.helperClasses.IO;
import courierServices.helperClasses.Location;

/**
 * Class representing all customers.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Customer Initializer
 * Array list of Customer Class objects
 * 
 * @MemberMethods
 * Constructor,
 * Get ArrayIndex,
 * Add new Customer,
 * Add order list,
 * Copy object function,
 * Print Customer's Order IDs,
 * Print Customer details,
 * Print All customers
 * 
 * @author Shubham Kumar
 *
 */
public class Customers implements Serializable {

	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Customer Initializer:
	 * Provides base (minimum) value for customer ID
	 * Also stores last created customer ID.
	 */
	public static long CUSTOMER_INITIALIZER = 1547852;

	/**
	 * Prints table header,
	 * When list of all customers is printed as ToString 
	 * this function prints table header.
	 */
	public static void printListHeader() {
		System.out.println("CUST_ID" + "||" + "CUST_NAME" + "||" + "CITIZEN_ID"
				+ "||" + "CITY" + "||" + "LOCAL_ADDRESS" + "||" + "PHONE"
				+ "||" + "EMAIL_ID");
	}

	/**
	 * Array list of customers,
	 * Contains Customer Class objects.
	 */
	protected ArrayList<Customer> customersList;

	/**
	 * Constructor : Initialize arraylist to empty list
	 */
	public Customers() {
		this.customersList = new ArrayList<>();
	}
	
	
	/**
	 * Finds and returns Customers arraylist's index for a particular customer, 
	 * @param custId - Customer ID
	 * @return  Arraylist Index (if found), -1 (if not found)
	 */
	private int getArrayIndex(long custId) {
		for (int i = 0; i < this.customersList.size(); i++) {
			if (this.customersList.get(i).get_custId() == custId) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Add New Customer:
	 * Takes input from console and generates new customer
	 * by adding newly created Customer class object to arraylist.
	 * 
	 * New customer ID is selected using static CUSTOMER INITIALIZER variable,
	 * after creation CUSTOMER INITIALIZER variable is incremented by 1.
	 * 
	 * @return Customer ID of created customer.
	 */
	public long addNewCustomer() {

		long custId;
		String citizenID;
		String custPassword;
		String custName;
		Location city;
		String localAddress;
		String phone;
		String emailID;

		try {
			citizenID = IO.getInput("Citizen ID : ");
			custName = IO.getInput("Name : ");
			custPassword = IO.getInput("Create Password : ");
			city = Location.getLocation(IO.getInput("City: "));
			localAddress = IO.getInput("Local Address : ");
			phone = IO.getInput("Phone : ");
			emailID = IO.getInput("E-mail ID : ");
			custId = CUSTOMER_INITIALIZER;

		} catch (NumberFormatException e) {
			System.out.println("Wrong input format, " + e.getMessage());
			return -1;
		}

		this.customersList.add(new Customer(custId, custPassword, citizenID,
				custName, city, localAddress, phone, emailID));
		System.out.println(" Customer Account Created, Your Customer ID is : "
				+ CUSTOMER_INITIALIZER);
		CUSTOMER_INITIALIZER++;
		return custId;
	}
	
	/**
	 * Adds OrderID to customer account
	 * @param orderId - Order ID
	 * @param custId - Customer ID
	 */
	public void addOrderList(long orderId, long custId) {
		int index = getArrayIndex(custId);
		this.customersList.get(index).addOrderInCustDetails(orderId);
		return;
	}

	/**
	 * Checks whether details provided exists.
	 * @param custId - Customer ID
	 * @param custPass - Password
	 * @return boolean value (true/false)
	 */
	public boolean authenticateCustomer(long custId, String custPass) {
		for (int i = 0; i < this.customersList.size(); i++) {
			if (this.customersList.get(i).get_custId() == custId
					&& this.customersList.get(i).get_custPassword()
							.equals(custPass)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function to copy Customers object.
	 * It assigns arraylist of passed object to caller object. 
	 * @param object - Customers object whose value to be equated
	 * @return this - in case of a condition a = b.copy(c);
	 */
	public Customers copy(Customers object) {
		this.customersList = object.customersList;
		return this;
	}

	/**
	 * Displays all orders (IDs) for given customer,
	 * It compares passed customer ID with all customer IDs, 
	 * and then calls PrintOrder function of Customer class
	 * to print all orders.
	 * @param custId - Customer ID
	 */
	public void displayCustomerOrderIDs(long custId) {
		int index = getArrayIndex(custId);
		System.out.print("ALL ORDERS(IDs) : ");
		this.customersList.get(index).printOrders();
	}

	/**
	 * Displays All details of given customer.
	 * It compares passed customer ID with all customer IDs, 
	 * and then calls displayCustomerDetails function of Customer class
	 * @param custId - Customer ID
	 */
	public void displayMyDetails(long custId) {
		int index = getArrayIndex(custId);
		System.out.println("Customer Details ---- ");
		this.customersList.get(index).displayCustomerDetails();
	}

	/**
	 * Prints all customer details, with all details of single customer
	 * in a single row.
	 * It calls ToString method of Customer class for every customer. 
	 */
	public void printAllCustomers() {
		for (int i = 0; i < this.customersList.size(); i++) {
			System.out.println(this.customersList.get(i));
		}
		return;
	}
}

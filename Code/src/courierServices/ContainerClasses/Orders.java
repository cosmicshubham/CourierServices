package courierServices.ContainerClasses;

import java.io.Serializable;
import java.util.ArrayList;

import courierServices.baseClasses.Order;
import courierServices.helperClasses.Calculations;
import courierServices.helperClasses.IO;
import courierServices.helperClasses.Location;

/**
 * Class representing all orders.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Order Initializer
 * Array list of Order Class objects
 * 
 * @MemberMethods
 * Constructor,
 * Get ArrayIndex,
 * Add new Order,
 * Add tracked locations,
 * Cancel order,
 * Copy object function,
 * Print Order details,
 * Print customer All orders,
 * Print all orders
 * Print tracked locations
 * Print unprocessed orders
 * Set Order In_Process flag
 * 
 * @author Shubham Kumar
 *
 */
public class Orders implements Serializable {

	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Order Initializer:
	 * Provides base (minimum) value for order ID
	 * Also stores last created order ID.
	 */
	public static long ORDER_INITIALIZER = 954126;

	/**
	 * Prints table header,
	 * When list of all orders is printed as ToString 
	 * this function prints table header.
	 */
	public static void printListHeader() {
		System.out.println("ORDER_ID" + "||" + "CUST_ID" + "||" + "DESCRIPTION"
				+ "||" + "SOURCE_CITY" + "||" + "SOURCE_LOCAL" + "||"
				+ "DEST_CITY" + "||" + "DEST_LOCAL" + "||" + "WEIGHT" + "||"
				+ "SIZE" + "||" + "PRICE" + "||" + "IS_DELIVERED" + "||"
				+ "ORDER_DATE" + "||" + "IS_IN_PROCESS");
	}

	/**
	 * Array list of orders,
	 * Contains Order Class objects.
	 */
	protected ArrayList<Order> ordersList;

	/**
	 * Constructor : Initialize arraylist to empty list
	 */
	public Orders() {
		this.ordersList = new ArrayList<>();
	}

	/**
	 * Finds and returns Orders arraylist's index for a particular Order ID, 
	 * @param orderID - Order ID
	 * @return  Arraylist Index (if found), -1 (if not found)
	 */
	private int getArrayIndex(long orderId) {
		for (int i = 0; i < this.ordersList.size(); i++) {
			if (this.ordersList.get(i).get_orderId() == orderId) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finds and returns Orders arraylist's index for a particular Order ID and Customer, 
	 * @param orderID - Order ID
	 * @param custID - Customer ID
	 * @return  Arraylist Index (if found), -1 (if not found)
	 */
	private int getArrayIndex(long orderId, long custId) {
		for (int i = 0; i < this.ordersList.size(); i++) {
			if (this.ordersList.get(i).get_custId() == custId
					&& this.ordersList.get(i).get_orderId() == orderId) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Add New Order:
	 * Takes input from console and generates new Order
	 * by adding newly created Order class object to arraylist.
	 * 
	 * New Order ID is selected using static ORDER INITIALIZER variable,
	 * after creation ORDER INITIALIZER variable is incremented by 1.
	 * 
	 * @return Order ID of created Employee.
	 */
	public long addNewOrder(long custID) {

		long orderId;
		String orderDescription;
		Location source;
		String sourceLocal;
		Location destination;
		String destLocal;
		double weight;
		double packetSize;
		int price;

		try {
			orderDescription = IO.getInput("Order Description : ");
			source = Location.getLocation(IO.getInput("Source City: "));
			sourceLocal = IO.getInput("Source Local Address : ");
			destination = Location.getLocation(IO
					.getInput("Destination City : "));
			destLocal = IO.getInput("Destination Local Address : ");
			weight = Double.parseDouble(IO
					.getInput("Enter weight (in grams) : "));

			if(weight > 25000 || weight < 1) {
				System.out.println("Invalid weight! Weight should be less than 25Kg.");
				return -1;
			}

			packetSize = Double.parseDouble(IO
					.getInput("Enter size (in cubic centimeters) : "));

			if(packetSize > 2000000 || packetSize < 1) {
				System.out.println("Invalid Size! Size should be less than 2 cubic meters.");
				return -1;
			}
			price = (int) Calculations.priceCalculator(source, destination,
					weight, packetSize);
			orderId = ORDER_INITIALIZER;

		} catch (NumberFormatException e) {
			System.out.println("Wrong input format, " + e.getMessage());
			return -1;
		}

		ordersList.add(new Order(orderId, custID, orderDescription, source,
				sourceLocal, destination, destLocal, weight,
				packetSize, price));
		System.out.println("Order is placed sucessfully, Order ID is : "
				+ orderId + ", and cost of delivery is : Rs " + price);
		ORDER_INITIALIZER++;
		return orderId;

	}

	/**
	 * Adds tracked location to an Order.
	 * It takes location name as string, converts it to Location (enum),
	 * and adds it to given order.
	 * @param locName - Name of Location as String
	 * @param orderID - Order ID
	 * @return boolean value (true/false)
	 */
	public boolean addTrackedLocation(String locName, long orderID) {
		int index = getArrayIndex(orderID);
		if (index == -1) {
			System.out.println("Invalid Order ID");
			return false;
		}
		for (Location loc : Location.values()) {
			if (loc.equalString(locName)) {
				this.ordersList.get(index).addTrackedLocation(loc);
				return true;
			}
		}
		System.out.println("Invalid Location");
		return false;
	}

	
	/**
	 * Cancels an order before order being processed.
	 * Takes order ID and customer ID, checks orders' In_process flag.
	 * Then it deletes the order if In_process flag is false.
	 * @param orderId - Order ID
	 * @param custId - Customer ID
	 * @return boolean value (true/false)
	 */
	public boolean cancelOrder(long orderId, long custId) {
		int index = this.getArrayIndex(orderId, custId);
		if (index == -1) {
			System.out.println("no order match for given details.");
			return false;
		}
		if (this.ordersList.get(index).is_inProcess()) {
			System.out.println("Order is dispatched, can not be cancelled.");
			return false;
		} else {
			this.ordersList.remove(index);
			System.out.println("Order " + orderId
					+ " is cancelled successfully.");
			return true;
		}

	}

	/**
	 * Function to copy Orders object.
	 * It assigns arraylist of passed object to caller object. 
	 * @param object - Orders object whose value to be equated
	 * @return this - in case of a condition a = b.copy(c);
	 */
	public Orders copy(Orders object) {
		this.ordersList = object.ordersList;
		return this;
	}

	/**
	 * Displays All details of given order.
	 * It checks passed customer ID and Order ID, 
	 * and then calls displayOrderDetails function of Order class
	 * @param custId - Customer ID
	 * @return boolean value true if given details are valid, else false.
	 */
	public boolean displayOrderDetails(long orderId, long custId) {
		int index = this.getArrayIndex(orderId, custId);
		if (index == -1) {
			return false;
		}
		this.ordersList.get(index).displayOrderDetails();
		return true;
	}
	
	/**
	 * Displays details of all orders of a given customer using Order's toString function.
	 * It shows a single order in a single row.
	 * It compares passed customer ID with all customer IDs, 
	 * and then uses toString function of Order class
	 * @param custId - Customer ID
	 */
	public void printCustomerAllOrders(long custId) {
		for (int i = 0; i < this.ordersList.size(); i++) {
			if (this.ordersList.get(i).get_custId() == custId) {
				System.out.println(this.ordersList.get(i));
			}
		}
		return;
	}

	/**
	 * Displays details of all orders using Order's toString function.
	 * It shows a single order in a single row.
	 */
	public void printAllOrders() {
		for (int i = 0; i < this.ordersList.size(); i++) {
			System.out.println(this.ordersList.get(i));
		}
		return;
	}

	/**
	 * Prints tracked location of given order for a particular customer.
	 * @param orderId - Order ID
	 * @param custId - Customer ID
	 * @return boolean value : false if passed details are valid, else true.
	 */
	public boolean printTrackedLocations(long orderId, long custId) {
		int index = this.getArrayIndex(orderId, custId);
		if (index == -1) {
			return false;
		}
		System.out.print("Tracked Locations : ");
		this.ordersList.get(index).printTrackedLocations();
		return true;
	}

	/**
	 * Prints all orders that are unprocessed,
	 * by checking In_process flag of the order.
	 */
	public void printUnprocessedOrders() {
		for (int i = 0; i < this.ordersList.size(); i++) {
			if (this.ordersList.get(i).is_inProcess() == false) {
				System.out.println(this.ordersList.get(i));
			}
		}
		return;
	}

	/**
	 * Sets the value of InProcess flag as true.
	 * Firstly, it checks the order id and returns false if it is invalid.
	 * @param orderId - Order ID
	 * @return boolean value (true/false)
	 */
	public boolean setOrderInProcessFlag(long orderId) {
		int index = this.getArrayIndex(orderId);
		if (index == -1) {
			System.out.println("Invalid Order ID");
			return false;
		}
		this.ordersList.get(index).set_inProcess(true);
		System.out.println("Order " + orderId + " is marked as InProcess");
		return true;

	}

}

package courierServices.baseClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import courierServices.helperClasses.Location;

/**
 * Class representing a single order.
 * This class also implements Serializable interface for writing object to file.
 * 
 * @MemberVariables
 * Order ID, Customer ID, Order Description, Source location, source local address
 * Destination, Destination Local Address, Tracking Locations, Packet Size,
 * Price, Is Delivered flag, Ordering Date, Delivery Date, Is in process flag
 * 
 * 
 * @MemberMethods
 * Constructor,
 * Add tracked location to list,
 * Display Order Details,
 * Get Customer ID,
 * Get Order ID,
 * Check Is_in_Process
 * Print Tracked Locations,
 * Set Is_in_process
 * ToString
 * 
 * @author Shubham Kumar
 *
 */
public class Order implements Serializable {

	/**
	 * Serial Version UID, Required to implement serializable interface
	 */
	private static final long serialVersionUID = 1L;

	protected long _orderId;
	protected long _custId;
	protected String _orderDescription;
	protected Location _source;
	protected String _sourceLocalAddress;
	protected Location _destination;
	protected String _destinationLocalAddress;
	
	/**
	 * ArrayList containing tracked locations of orders
	 */
	protected ArrayList<Location> _trackingLocations;
	protected double _weight;
	protected double _packetSize;
	protected int _price;
	protected boolean _isDelivered;
	protected Date _orderingDate;
	protected Date _deliveryDate;
	protected boolean _inProcess;

	/**
	 * Constructor: Takes details as arguments and assigns them to class variables
	 * @param _orderId - Order ID
	 * @param _custId - Customer ID
	 * @param _orderDescription - Order Description
	 * @param _source - Source City
	 * @param _sourceLocalAddress - Source Local Address
	 * @param _destination - Destination City
	 * @param _destinationLocalAddress - Destination Local Address
	 * @param _weight - Weight
	 * @param _packetSize - Packet Size (Volume)
	 * @param _price - Cost of delivery
	 */
	public Order(long _orderId, long _custId, String _orderDescription,
			Location _source, String _sourceLocalAddress,
			Location _destination, String _destinationLocalAddress,
			double _weight, double _packetSize, int _price) {

		this._orderId = _orderId;
		this._custId = _custId;
		this._orderDescription = _orderDescription;
		this._source = _source;
		this._destination = _destination;
		this._weight = _weight;
		this._packetSize = _packetSize;
		this._price = _price;
		this._trackingLocations = new ArrayList<>();
		this._isDelivered = false;
		this._orderingDate = new Date(); // Current Date
		this._deliveryDate = null;
		this._inProcess = false;
	}

	/**
	 * saves tracked location 
	 * @param location - Location City
	 */
	public void addTrackedLocation(Location location) {
		if (location.equals(_destination)) {
			this._isDelivered = true;
			this._deliveryDate = new Date(); // Current Date
		}
		this._trackingLocations.add(location);
	}

	/**
	 * Displays Order details:
	 * Order ID, Customer ID, Order Description, Source Location, Destination,
	 * Tracked location, weight, cost of delivery, is_delivered flag
	 * 
	 */
	public void displayOrderDetails() {
		System.out.println("Order ID : " + _orderId);
		System.out.println("Customer ID : " + _custId);
		System.out.println("Order Description :" + _orderDescription);
		System.out.println("Source Location :" + _source + " ("
				+ _sourceLocalAddress + ")");
		System.out.println("Destination : " + _destination + " ("
				+ _destinationLocalAddress + ")");

		System.out.print("Tracked Location : ");
		this.printTrackedLocations();

		System.out.println("Weight : " + _weight);
		System.out.println("Price : " + _price);
		System.out.println("Is Delivered : " + _isDelivered);
	}

	public long get_custId() {
		return _custId;
	}

	public long get_orderId() {
		return _orderId;
	}

	public boolean is_inProcess() {
		return _inProcess;
	}

	/**
	 * Prints the locations where order is reached
	 */
	public void printTrackedLocations() {
		Iterator<Location> iterator = _trackingLocations.iterator();
		if (!(iterator.hasNext())) {
			System.out.println("<no information about tracking>");
			return;
		}
		while (iterator.hasNext()) {
			Location location = (Location) iterator.next();
			System.out.print(location + " ");
		}
		System.out.println("");
		return;
	}

	public void set_inProcess(boolean _inProcess) {
		this._inProcess = _inProcess;
	}

	@Override
	public String toString() {
		// ToString method for displaying object directly.
		String s = (this._orderId + "||" + this._custId + "||"
				+ this._orderDescription + "||" + this._source + "||"
				+ this._sourceLocalAddress + "||" + this._destination + "||"
				+ this._destinationLocalAddress + "||" + this._weight + "||"
				+ this._packetSize + "||" + this._price + "||"
				+ this._isDelivered + "||" + this._orderingDate + "||" + this
				.is_inProcess());
		return s;
	}
}

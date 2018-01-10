package courierServices.Runner;

import courierServices.ContainerClasses.Customers;
import courierServices.ContainerClasses.Employees;
import courierServices.ContainerClasses.Orders;
import courierServices.helperClasses.Calculations;
import courierServices.helperClasses.EmpType;
import courierServices.helperClasses.IO;
import courierServices.helperClasses.Location;

/**
 * Runner class: Contains Main Method
 * @version 1.3
 * @author Shubham Kumar
 *
 */
public class Runner {

	/**
	 * Main Method : Program launch beginner
	 * @param args - Arguments (optional) to be passed while launching the program
	 */
	public static void main(String[] args) {
		System.out
		.println("----------------- Starting Program!! ---------------");

		Customers customers_sheet1 = new Customers();
		Employees employees_sheet1 = new Employees();
		Orders orders_sheet1 = new Orders();

		// Load data from files
		fileReading(customers_sheet1, employees_sheet1, orders_sheet1);
		
		boolean exitFlag = false; // For Exiting Do While loop
		do {
			MenuDisplayMethods.displayMainMenu();
			char key = IO.getInput("Enter your choice : ").charAt(0);
			switch (key) {
			case '1':
				// Customer Portal
				handleCustomerPortal(customers_sheet1, orders_sheet1);
				break;
			case '2':
				// Employee Portal
				handleEmployeePortal(customers_sheet1, orders_sheet1,
						employees_sheet1);
				break;
			case '3':
				// About
				MenuDisplayMethods.displayAboutMessage();
				break;
			case '4':
				// Save current session
				fileWriting(customers_sheet1, employees_sheet1, orders_sheet1);
				break;
			case '5':
				// Exit without saving
				exitFlag = true;
				break;
			case '6':
				// Save and Exit
				fileWriting(customers_sheet1, employees_sheet1, orders_sheet1);
				exitFlag = true;
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
			}

		} while (exitFlag == false);

		System.out.println("----------------- Exiting Program!! ---------------");
		
		return;

	}

	/**
	 * File Reading: Calls IO Class file functions for file reading
	 * @param customers_sheet1 - Customers Object
	 * @param employees_sheet1 - Employees Object
	 * @param orders_sheet1 - Orders Object
	 */
	private static void fileReading(Customers customers_sheet1,
			Employees employees_sheet1, Orders orders_sheet1) {
		long[] initializers = { -1, -1, -1 };
		if (IO.readFromFile(customers_sheet1, employees_sheet1, orders_sheet1,
				initializers, "CourierServices_data.dat")) {
			System.out.println("Files loaded sucessfully");
		} else {
			System.out.println("(First Time Run) OR (Error in loading files.)");

		}
		if (initializers[0] != -1) {
			Customers.CUSTOMER_INITIALIZER = initializers[0];
		}
		if (initializers[1] != -1) {
			Employees.EMPLOYEE_INITIALIZER = initializers[1];
		}
		if (initializers[2] != -1) {
			Orders.ORDER_INITIALIZER = initializers[2];
		}
	}

	/**
	 * File Writing: Calls IO Class file functions for file writing
	 * @param customers_sheet1 - Customers Object
	 * @param employees_sheet1 - Employees Object
	 * @param orders_sheet1 - Orders Object
	 */
	private static void fileWriting(Customers customers_sheet1,
			Employees employees_sheet1, Orders orders_sheet1) {

		long[] initializers = { Customers.CUSTOMER_INITIALIZER,
				Employees.EMPLOYEE_INITIALIZER, Orders.ORDER_INITIALIZER };
		if (IO.writeToFile(customers_sheet1, employees_sheet1, orders_sheet1,
				initializers, "CourierServices_data.dat")) {
			System.out.println("Files written Sucessfully!");
		} else {
			System.out.println("Error writing sheets!");
		}
	}

	
	/**
	 * Function that handles Customer Portal menu and its SwitchCase statements
	 * @param customers_sheet1 - Customers Object
	 * @param orders_sheet1 - Orders Object
	 */
	private static void handleCustomerPortal(Customers customers_sheet1,
			Orders orders_sheet1) {
		boolean exitFlag = false;

		do {
			MenuDisplayMethods.displayCustomerPortal();
			char key = IO.getInput("Enter your choice : ").charAt(0);
			switch (key) {

			// New Customer
			case '1': {
				Long custId = customers_sheet1.addNewCustomer();
				if (custId == -1) {
					System.out.println("Error, Try Again");
					break;
				}
				break;
			}

			// Existing Customer
			case '2': {
				long custId;
				String custPass;
				try {
					custId = Long
							.parseLong(IO.getInput("Enter customer ID : "));
					custPass = IO.getInput("Enter Password : ");
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!customers_sheet1.authenticateCustomer(custId, custPass)) {
					System.out.println("no match for entered details.");
					break;
				} else {
					handleExistingCustomer(custId, customers_sheet1,
							orders_sheet1);
					;
					break;
				}
			}

			// Price Calculator
			case '3': {
				handlePriceCalculator();
				break;

			}

			// Back
			case '4': {
				exitFlag = true;
				return;

			}
			default:
				System.out.println("Invalid Choice!");
				break;
			}

		} while (exitFlag == false);

	}

	/**
	 * Function that handles Employee Portal menu and its SwitchCase statements
	 * @param customers_sheet1 - Customers Object
	 * @param orders_sheet1 - Orders Object
	 * @param employees_sheet1 - Employees Object
	 */
	private static void handleEmployeePortal(Customers customers_sheet1,
			Orders orders_sheet1, Employees employees_sheet1) {

		long empId;
		String empPass;
		try {
			empId = Long.parseLong(IO.getInput("Enter Emp ID : "));
			empPass = IO.getInput("Enter password : ");
		} catch (NumberFormatException e) {
			System.out.println("Wrong input format, " + e.getMessage());
			return;
		}
		EmpType empType = employees_sheet1.authenticateEmployee(empId, empPass);

		if (empType == EmpType.UNSPECIFIED) {
			System.out
			.println("You don't have permission to access Employee Portal.");
			return;
		}
		boolean exitFlag = false;
		do {
			MenuDisplayMethods.displayEmployeePortal();
			char key = IO.getInput("Enter your choice : ").charAt(0);
			switch (key) {

			// New Employee
			case '1': {
				if (empType != EmpType.ADMIN) {
					System.out.println("Access denied!");
					break;
				}
				Long empId_c = employees_sheet1.addNewEmployee();
				if (empId_c == -1) {
					System.out.println("Error, Try Again");
					break;
				}
				break;
			}

			// Remove Employee
			case '2': {
				if (empType != EmpType.ADMIN) {
					System.out.println("Access denied!");
					break;
				}
				long empId_d;

				try {
					empId_d = Long.parseLong(IO.getInput("Enter Emp ID : "));
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}

				if (employees_sheet1.removeEmployee(empId_d)) {
					System.out.println("Employee Removed!");
					if (empId_d == empId) {
						// Employee removed himself/herself
						exitFlag = true;
					}
				} else {
					System.out.println("ERROR");
				}
				break;
			}

			// Display All Employees
			case '3': {
				if (empType != EmpType.ADMIN) {
					System.out.println("Access denied!");
					break;
				}
				Employees.printListHeader();
				employees_sheet1.printAllEmployees();
				break;
			}

			// Display All Orders
			case '4': {
				if (empType == EmpType.FIELD) {
					System.out.println("Access denied!");
					break;
				}
				Orders.printListHeader();
				orders_sheet1.printAllOrders();
				break;

			}

			// Display All customers
			case '5': {
				if (empType == EmpType.FIELD) {
					System.out.println("Access denied!");
					break;
				}
				Customers.printListHeader();
				customers_sheet1.printAllCustomers();
				break;
			}

			// Display Unprocessed Orders
			case '6': {
				Orders.printListHeader();
				orders_sheet1.printUnprocessedOrders();
				break;
			}

			// mark order for process
			case '7': {
				if (empType == EmpType.OFFICE) {
					System.out.println("Access denied!");
					break;
				}
				long orderId;
				try {
					orderId = Long.parseLong(IO.getInput("Enter Order ID : "));
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!(orders_sheet1.setOrderInProcessFlag(orderId))) {
					System.out.println("ERROR");
				}
				break;
			}

			// Add tracking location
			case '8': {
				if (empType == EmpType.OFFICE) {
					System.out.println("Access denied!");
					break;
				}
				long orderID;
				String locName;
				try {
					orderID = Long.parseLong(IO.getInput("Enter Order ID : "));
					locName = IO.getInput("Enter Location Name : ");
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!(orders_sheet1.addTrackedLocation(locName, orderID))) {
					System.out.println("ERROR");
				}
				break;
			}

			// Back
			case '9': {
				exitFlag = true;
				break;
			}

			default:
				System.out.println("Invalid Choice!");
				break;
			}

		} while (exitFlag == false);

	}

	/**
	 * Function that handles Existing customer menu and its SwitchCase statements
	 * @param custID - Customer ID
	 * @param customers_sheet1 - Customers Object
	 * @param orders_sheet1 - Orders Object
	 */
	private static void handleExistingCustomer(long custId,
			Customers customers_sheet1, Orders orders_sheet1) {

		boolean exitFlag = false;
		do {
			MenuDisplayMethods.displayExistingCustomerMenu();
			char key = IO.getInput("Enter your choice : ").charAt(0);
			switch (key) {

			// Place new order
			case '1': {
				Long orderId = orders_sheet1.addNewOrder(custId);
				if (orderId == -1) {
					System.out.println("Error, Try Again");
					break;
				} else {
					customers_sheet1.addOrderList(orderId, custId);
				}
				break;
			}

			// Cancel Order
			case '2': {
				long orderId;
				try {
					orderId = Long.parseLong(IO.getInput("Enter Order ID : "));
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!orders_sheet1.cancelOrder(orderId, custId)) {
					System.out.println("ERROR");
				}
				break;
			}

			// Track Order
			case '3': {
				long orderId;
				try {
					orderId = Long.parseLong(IO.getInput("Enter Order ID : "));
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!orders_sheet1.printTrackedLocations(orderId, custId)) {
					System.out.println("invalid Order ID");
				}
				break;
			}

			// Display Order Details
			case '4': {
				long orderId;
				try {
					orderId = Long.parseLong(IO.getInput("Enter Order ID : "));
				} catch (NumberFormatException e) {
					System.out.println("Wrong input format, " + e.getMessage());
					return;
				}
				if (!orders_sheet1.displayOrderDetails(orderId, custId)) {
					System.out.println("invalid Order ID");
				}
				break;
			}

			// Display All orders
			case '5': {
				customers_sheet1.displayCustomerOrderIDs(custId);
				System.out.println("");
				Orders.printListHeader();
				orders_sheet1.printCustomerAllOrders(custId);

				break;
			}

			// Display My details
			case '6': {
				customers_sheet1.displayMyDetails(custId);
			}

			// back
			case '7': {
				exitFlag = true;
				break;

			}
			default:
				System.out.println("Invalid Choice!");
				break;
			}

		} while (exitFlag == false);
	}

	
	/**
	 * Price calculator:
	 * Calculates cost of the packet by taking inputs from user.
	 * It takes input and calls static Price Calculator function of calculation class,
	 * by passing arguments.
	 */
	private static void handlePriceCalculator() {
		System.out.println("PRICE CALCULATOR ----");
		try {
			String s = IO.getInput("Enter source city : ");
			String d = IO.getInput("Enter Destination city : ");
			double weight = Double.parseDouble(IO
					.getInput("Enter weight (in grams) : "));
			double length = Double.parseDouble(IO
					.getInput("Enter breadth (in cm) : "));
			double breadth = Double.parseDouble(IO
					.getInput("Enter height (in cm) : "));
			double height = Double.parseDouble(IO
					.getInput("Enter length (in cm) : "));

			if(weight > 25000 || weight < 1) {
				System.out.println("Invalid weight! Weight should be less than 25Kg.");
				return;
			}

			double packetSize = length * breadth * height;

			if(packetSize > 2000000 || packetSize < 1) {
				System.out.println("Invalid Size! Size should be less than 2 cubic meters.");
				return;
			}
			Location source = Location.getLocation(s);
			Location destination = Location.getLocation(d);
			int price = (int) Calculations.priceCalculator(source, destination,
					weight, packetSize);
			System.out.println("Cost for your packet delivery will be Rs "
					+ price);
		} catch (NumberFormatException e) {
			System.out.println("Wrong input format, " + e.getMessage());
			return;
		}
	}

}

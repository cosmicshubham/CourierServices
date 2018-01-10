package courierServices.Runner;


/**
 * Class containing methods to display menus,
 * when required in Main.
 * 
 * @author Shubham Kumar
 */
public class MenuDisplayMethods {
	
	public static void displayMainMenu() {

		System.out.println("\n=============================================");
		System.out.println("SK's COURIER SERVICES  >>\n");
		System.out.println("1). Customer Portal / Place New Order");
		System.out.println("2). Employee Portal");
		System.out.println("3). About");
		System.out.println("4). Save session");
		System.out.println("5). Exit without saving");
		System.out.println("6). Save and Exit\n");
	}

	
	public static void displayCustomerPortal() {
		System.out.println("\n---------------------------------------------");
		System.out.println("SK's COURIER SERVICES  >> CUSTOMER PORTAL >>\n");
		System.out.println("\t 1). New Customer ");
		System.out.println("\t 2). Existing Customer ");
		System.out.println("\t 3). Price Calculator ");
		System.out.println("\t 4). Back\n");

	}

	public static void displayExistingCustomerMenu() {
		System.out.println("\n---------------------------------------------");
		System.out.println("..  > CUSTOMER PORTAL >> EXISTING CUSTOMER >>\n");
		System.out.println("\t\t 1). Place new order");
		System.out.println("\t\t 2). Cancel Order");
		System.out.println("\t\t 3). Track Order");
		System.out.println("\t\t 4). Display Order Details");
		System.out.println("\t\t 5). Display All Orders");
		System.out.println("\t\t 6). Display my details");
		System.out.println("\t\t 7). Back\n");

	}
	
	public static void displayEmployeePortal() {
		System.out.println("\n---------------------------------------------");
		System.out.println("SK's COURIER SERVICES  >> EMPLOYEE PORTAL >>\n");
		System.out.println("\t 1). Add new Employee (admin)");
		System.out.println("\t 2). Remove an Employee (admin)");
		System.out.println("\t 3). Display All Employees (admin)");
		System.out.println("\t 4). Display All Orders");
		System.out.println("\t 5). Display All Customers");
		System.out.println("\t 6). Display unprocessed Orders");
		System.out.println("\t 7). Mark an order for process");
		System.out.println("\t 8). Report order tracking location");
		System.out.println("\t 9). Back\n");
	}

	public static void displayAboutMessage() {
		System.out.println("\n\n---------------------------------------------");
		System.out.println("SK's COURIER SERVICES  >> ABOUT >>");

		System.out.println("\nAbout software ---------");
		System.out.println("Size (in LOC) = 2130");
		System.out.println("Size (Source) = 58.5 kilobytes");
		System.out.println("Size (Binaries) = 48.2 kilobytes");
		System.out.println("Development Started  : 26.06.2015");
		System.out.println("Development Finished (this version) : 28.06.2015");
		System.out.println("Current Version : 1.3, Beta Release 1.7");
		System.out.println("Copyrights (c) Shubham Kumar");
		System.out.println("Unauthorised distribution of the source code of this program is not appreciable");
		System.out.println("Binaries are freed for sharing.");

		System.out.println("\nDeveloper and Author ---------");
		System.out.println("Name : Shubham Kumar");
		System.out.println("Email : shubham.kumar.sci@gmail.com");
		System.out.println("Current Education : B.Tech in Computer Science (3rd year,2015)\n\n");


	}

}

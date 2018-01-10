package courierServices.helperClasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import courierServices.ContainerClasses.Customers;
import courierServices.ContainerClasses.Employees;
import courierServices.ContainerClasses.Orders;

/**
 * IO Class:
 * Contains static functions for input and output.
 * 
 * @Functions
 * Get Input from console
 * Read objects from file
 * Write objects to file
 * 
 * @author Shubham Kumar
 */
public class IO {

	/**
	 * Get Input from console:
	 * Takes input from console as string,
	 * displays input prompt message before taking input.
	 * 
	 * @param prompt - Message to be displayed for input
	 * @return String taken as input from console
	 */
	public static String getInput(String prompt) {
		String input = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.print(prompt);
			System.out.flush();
			input = reader.readLine();
		} catch (IOException e) {
			System.out.println("Exception in reading from console - "
					+ e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return input;
	}

	/**
	 * Read Objects from file,
	 * Customers, Employees, Orders, Initializers are pass as parameters,
	 * and values read are copied into them.
	 * File Name is also passed as parameter
	 * @param object_c - Object of Customers
	 * @param object_e - Object of Employees
	 * @param object_o - Object of Orders
	 * @param initializers - Array containing initializers(Customer/Employee/Order)
	 * @param fileName - FileName from which objects are to be read
	 * @return boolean value (true if reading is successful, else false)
	 */
	public static boolean readFromFile(Customers object_c, Employees object_e,
			Orders object_o, long[] initializers, String fileName) {

		// Backup Creation
		Path current = Paths.get(fileName);
		Path backup = Paths.get(fileName + ".BAKatREAD");
		if (Files.exists(current)) {
			try {
				Files.copy(current, backup, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.out.println("[At Read] Backup creation failed! ("
						+ e.getMessage() + ")");
			} catch (Exception e) {
				System.out.println("Exception : " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}

		// Reading 
		try {
			ObjectInputStream objectInputSream = new ObjectInputStream(
					new FileInputStream(fileName));
			initializers[0] = objectInputSream.readLong();
			initializers[1] = objectInputSream.readLong();
			initializers[2] = objectInputSream.readLong();
			object_c.copy((Customers) objectInputSream.readObject());
			object_e.copy((Employees) objectInputSream.readObject());
			object_o.copy((Orders) objectInputSream.readObject());
			objectInputSream.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			System.out.println("Exception in IO (" + e.getMessage() + ")");
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Exception in finding class (" + e.getMessage()
					+ ")");
			return false;
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Write Objects to file,
	 * Customers, Employees, Orders, Initializers are pass as parameters,
	 * and are written into file in a particular sequence.
	 * File Name is also passed as parameter
	 * @param object_c - Object of Customers
	 * @param object_e - Object of Employees
	 * @param object_o - Object of Orders
	 * @param initializers - Array containing initializers(Customer/Employee/Order)
	 * @param fileName - FileName to which objects are to be written
	 * @return boolean value (true if writing is successful, else false)
	 */
	public static boolean writeToFile(Customers object_c, Employees object_e,
			Orders object_o, long[] initializers, String fileName) {

		// Backup Creation
		Path current = Paths.get(fileName);
		Path backup = Paths.get(fileName + ".BAKatWRITE");
		if (Files.exists(current)) {
			try {
				Files.copy(current, backup, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.out.println("[At Write] Backup creation failed! ("
						+ e.getMessage() + ")");
			} catch (Exception e) {
				System.out.println("Exception : " + e.getMessage());
			}
		}

		// Delete previous file 
		try {
			Files.deleteIfExists(current);
		} catch (IOException e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		// Writing
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(fileName));
			objectOutputStream.writeLong(initializers[0]);
			objectOutputStream.writeLong(initializers[1]);
			objectOutputStream.writeLong(initializers[2]);
			objectOutputStream.writeObject(object_c);
			objectOutputStream.writeObject(object_e);
			objectOutputStream.writeObject(object_o);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			System.out.println("Exception : " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return false;
		}

		return true;
	}

}

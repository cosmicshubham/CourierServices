package courierServices.helperClasses;

/**
 * CustomException class:
 * a simple class that extends Java Exception class.
 * It just receives the message as string at displays it at the time of exception.
 * @author Shubham Kumar
 *
 */
public class CustomException extends Exception {

	/**
	 * Serial Version UID, required if Exception class is extended
	 */
	private static final long serialVersionUID = 42L; // Random

	private String _msg;

	public CustomException() {
		this._msg = this.getMessage();
	}

	/**
	 * Constructor:
	 * receives string and assigns it to error message. 
	 * @param message - Exception message that is to be displayed
	 */
	public CustomException(String message) {
		this._msg = message;
	}

	@Override
	public String toString() {
		// To string function : to display the message even if object is displayed.
		return this._msg;
	}

}

package courierServices.helperClasses;

/**
 * Enumeration class that contains predefined Employee Type.
 * 
 * @Types
 * Admin, Office, Field, Unspecified
 * 
 * @author Shubham Kumar
 *
 */
public enum EmpType {

	ADMIN("Administrator", 'a'),
	OFFICE("Office Employee", 'o'),
	FIELD("Field Employee", 'f'),
	UNSPECIFIED("Not Specified!", 'n');

	public static EmpType getEmpType(char flag) {
		if (flag == 'a')
			return EmpType.ADMIN;
		if (flag == 'o')
			return EmpType.OFFICE;
		if (flag == 'f')
			return EmpType.FIELD;
		return EmpType.UNSPECIFIED;
	}

	private String _permissionAsString;

	@SuppressWarnings("unused")
	private char _flag;

	private EmpType(String permissionString, char flag) {
		this._permissionAsString = permissionString;
		this._flag = flag;
	}

	@Override
	public String toString() {
		return this._permissionAsString;
	}

}

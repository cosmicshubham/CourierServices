package courierServices.helperClasses;

/**
 * Enumeration class that contains predefined Locations.
 * 
 * @Types
 * Major cities of India
 * 
 * @author Shubham Kumar
 *
 */
public enum Location {

	SRINAGAR("Srinagar",1),
	SHIMLA("Shimla",2),
	CHANDIGARH("Chandigarh",3),
	DEHRADOON("Dehradoon",4),
	DELHI("Delhi",5),
	AGRA("Agra", 6),
	JAIPUR("Jaipur",7),
	LUCKNOW("Lucknow",8),
	PATNA("Patna",9),
	BHOPAL("Bhopal",10),
	GANDHINAGAR("Gandhinagar",11),
	RANCHI("Ranchi",12),
	KOLKATA("Kolkata",13),
	RAIPUR("Raipur", 14),
	BHUBHNESHWAR("Bhubneshwar",16),
	PANJI("Panji", 17),
	HYDRABAAD("Hydrabaad",18),
	BANGLORE("Banglore",19),
	CHENNAI("Chennai",20),
	THIRUVANANTHPURAM("Thiruvananthpuram", 21),

	ARBITRARY("Nil",22);
	
	/**
	 * Get Location:
	 * Returns the city enum if location matches,
	 * else returns ARBITRARY
	 * 
	 * @param location - enum class member
	 * @return
	 */
	public static Location getLocation(String location) {
		for (Location loc : Location.values()) {
			if (loc.equalString(location)) {
				return loc;
			}
		}
		return Location.ARBITRARY;
	}

	private String _name;
	private int _priceId;

	private Location(String name, int priceId) {
		this._name = name;
		this._priceId = priceId;
	}

	public boolean equals(Location location) {
		if (this._name.equalsIgnoreCase(location._name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equalString(String name) {
		if (this._name.equalsIgnoreCase(name)) {
			return true;
		} else {
			return false;
		}
	}

	public int getpriceID() {
		return this._priceId;
	}

	@Override
	public String toString() {
		return this._name;
	}

}
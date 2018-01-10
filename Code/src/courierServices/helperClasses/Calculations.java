package courierServices.helperClasses;

/**
 * Calculation class : Contains static functions involving calculations,
 * can be used across entire program.
 * 
 * @author Shubham Kumar
 */
public class Calculations {

	/**
	 * Price Calculator Function:
	 * Takes information about the order and calculates its cost
	 * 
	 * @param source - Source Location (enum)
	 * @param destination - Destination Location (enum)
	 * @param weight - Weight in grams
	 * @param packetSize - Volume of packet in Cubic centimeters
	 * @return Cost (as integer)
	 */
	public static int priceCalculator(Location source, Location destination,
			double weight, double packetSize) {

		/**
		 * Price Calculations:
		 * 
		 * 	MODIFICATION 1 :
		 * 
		 * 1). Difference between source and destination
		 * 		price is used to represent distance between source and destination.
		 * 2). For a unit distance and per (20cm x 20cm x 20cm) volume, Price of
		 * 		1KG delivery is 100 Rupees.
		 * 3). Price multiplies with same factor as
		 * 		volume, weight and distance does.
		 * 4). Hence price equation is
		 * 		(distanceFactor) x (weight/10) x (volume/(20x20x20))
		 * 
		 * 	MODIFICATION 2 :
		 * 
		 * 1). Price were too high acc to previous calculations,
		 * 		so now there are reduced by a factor of 100.
		 * 2). Upper limit and lower limit is set  
		 * 
		 * 
		 */

		int distanceFactor = (Math.abs(source.getpriceID()
				- destination.getpriceID()));

		// Delivery in same city then minimum charge will be calculated
		if (source.equals(destination)) {
			distanceFactor = 1;
		}

		// in case both source and destination is Location.ARBITRARY (any)
		if (distanceFactor == 0) {
			distanceFactor = 20;
		}

		int price = (int) (((double) distanceFactor) * weight * packetSize * (0.000125)/100 );
		
		if (price < 50) return 50;
		if (price > 1000) return 1000;
		return price;

	}

}

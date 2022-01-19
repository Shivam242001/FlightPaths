import java.util.ArrayList;
// --== CS400 File Header Information ==--
// Name: Dhananjayan Pallavur Naduvakkat
// Email: pallavurnadu@wisc.edu
// Team: EF red
// Role: Data Wrangler
// TA: Yelun Bao
// Lecturer: Florian Heimerl

import java.util.List;

/**
 * Airport class that stores the data for an aiport including its 3 digit
 * airport code, airport city and a list of all the flights that depart from
 * this airport
 */
public class Airport {
	String code;
	String city;
	List<Flight> departingFlights;

	public Airport(String city, String code) {
		this.city = city;
		this.code = code;
		this.departingFlights = new ArrayList<Flight>();
	}
}

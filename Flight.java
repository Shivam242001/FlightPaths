// --== CS400 File Header Information ==--
// Name: Dhananjayan Pallavur Naduvakkat
// Email: pallavurnadu@wisc.edu
// Team: EF red
// Role: Data Wrangler
// TA: Yelun Bao
// Lecturer: Florian Heimerl

/**
 * Flight class that stores data regarding every flight with its source and
 * destination airports along with its distance
 */
public class Flight {
	Airport source;
	Airport destination;
	int distance;

	public Flight(Airport source, Airport destination, int distance) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

}

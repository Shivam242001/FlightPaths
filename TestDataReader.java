// --== CS400 File Header Information ==--
// Name: Dhananjayan Pallavur Naduvakkat
// Email: pallavurnadu@wisc.edu
// Team: EF red
// Role: Data Wrangler
// TA: Yelun Bao
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.util.List;

/**
 * Class that tests the functionality of the Data Reader class
 * 
 * @author Dhananjayan Pallavur Naduvakkat
 */
public class TestDataReader {

	/**
	 * Tests that the DataReader returns the correct number of Flight objects
	 * returned from the routes-large.csv dataset
	 */
	@Test
	public void testFlightsReturned() {
		try {
			DataReader dataReader = new DataReader(new String[] { "routes-large.csv" });
			List<Flight> flights = dataReader.getAllFlights();
			assertEquals(36224, flights.size());
		} catch (Exception e) {
			fail("Unexpected Exception was thrown by DataReader");
		}
	}

	/**
	 * Tests that the DataReader returns the correct number of Airport objects
	 * returned from the routes-large.csv dataset
	 */
	@Test
	public void testAirportsReturned() {
		try {
			DataReader dataReader = new DataReader(new String[] { "routes-large.csv" });
			List<Airport> airports = dataReader.getAllAirports();
			assertEquals(483, airports.size());
		} catch (Exception e) {
			fail("Unexpected Exception was thrown by DataReader");
		}
	}

	/**
	 * Tests that the DataReader returns the correct Airport objects in a city
	 */
	@Test
	public void testAirportsInCity() {
		try {
			DataReader dataReader = new DataReader(new String[] { "routes-large.csv" });
			List<Airport> airports = dataReader.getAllAirports("Madison, WI");
			assertEquals(1, airports.size());
			assertEquals("MSN", airports.get(0).code);
			assertEquals("Madison, WI", airports.get(0).city);
		} catch (Exception e) {
			fail("Unexpected Exception was thrown by DataReader");
		}
	}

	/**
	 * Tests that the DataReader returns the correct number of cities from the
	 * routes-large.csv dataset
	 */
	@Test
	public void testCitiesReturned() {
		try {
			DataReader dataReader = new DataReader(new String[] { "routes-large.csv" });
			List<String> cities = dataReader.getAllCities();
			assertEquals(399, cities.size());
		} catch (Exception e) {
			fail("Unexpected Exception was thrown by DataReader");
		}
	}

	/**
	 * Tests that the Data Reader throws an excpetion if passed in an invalid
	 * dataset file
	 */
	@Test
	public void testInvalidFile() {
		try {
			DataReader dataReader = new DataReader(new String[] { "routes-rickastley.csv" });
			fail("Exception was not thrown by DataReader");
		} catch (Exception e) {
			// Expected behaviour
		}
	}
}

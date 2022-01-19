// --== CS400 File Header Information ==--
// Name: Dhananjayan Pallavur Naduvakkat
// Email: pallavurnadu@wisc.edu
// Team: EF red
// Role: Data Wrangler
// TA: Yelun Bao
// Lecturer: Florian Heimerl

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * DataReader class used to parse the data from the dataset
 */
public class DataReader {
	List<Flight> allFlights;
	Hashtable<String, Airport> allAirports;
	List<String> allCities;

	/**
	 * Constructor that takes file name through a list of arguments to initialize a
	 * Data Reader object
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DataFormatException
	 */
	public DataReader(String[] args) throws FileNotFoundException, IOException, DataFormatException {
		// initializing class variables
		String line;
		this.allFlights = new ArrayList<Flight>();
		this.allAirports = new Hashtable<String, Airport>();
		this.allCities = new ArrayList<String>();
		// create reader object from file passed in as argument
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		reader.readLine(); // read header file
		while ((line = reader.readLine()) != null) {
			// split the line with regex to consider the formatting of string in csv
			String[] lineData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			Airport origin;
			Airport destination;
			Flight flight;
			// don't create a flight if the source and destination are the same
			if (lineData[1].equals(lineData[2])) {
				continue;
			}
			// if Airports already created grab it from hashtable, else create new one
			if (this.allAirports.containsKey(lineData[1])) {
				origin = this.allAirports.get(lineData[1]);
			} else {
				origin = new Airport(lineData[3].replaceAll("^\"|\"$", ""), lineData[1]);
			}
			if (this.allAirports.containsKey(lineData[2])) {
				destination = this.allAirports.get(lineData[2]);
			} else {
				destination = new Airport(lineData[4].replaceAll("^\"|\"$", ""), lineData[2]);
			}
			// create new Flight object with data from the file
			flight = new Flight(origin, destination, Integer.valueOf(lineData[5]));
			// add flight to departing flights list of the origin airport
			origin.departingFlights.add(flight);
			// update aiports and add flight to array
			this.allAirports.put(lineData[1], origin);
			this.allAirports.put(lineData[2], destination);
			this.allFlights.add(flight);
		}
		reader.close(); // close the reader object
		// read all flights and add all unique cities to list
		for (String code : this.allAirports.keySet()) {
			String city = this.allAirports.get(code).city;
			if (!this.allCities.contains(city)) {
				this.allCities.add(city);
			}
		}
	}

	/**
	 * Returns ArrayList of all unique airports from the dataset
	 * 
	 * @return List<Airport>
	 */
	List<Airport> getAllAirports() {
		List<Airport> result = new ArrayList<Airport>();
		for (String code : this.allAirports.keySet()) {
			result.add(this.allAirports.get(code));
		}
		return result;
	}

	/**
	 * Returns ArrayList of all unique airports in a particular city from the
	 * dataset
	 * 
	 * @param city
	 * @return List<Airport>
	 */
	List<Airport> getAllAirports(String city) {
		List<Airport> result = new ArrayList<Airport>();
		for (String code : this.allAirports.keySet()) {
			Airport airport = this.allAirports.get(code);
			if (airport.city.equals(city)) {
				result.add(airport);
			}
		}
		return result;
	}

	/**
	 * Returns ArrayList of all the unique cities in the dataset
	 * 
	 * @return List<String>
	 */
	List<String> getAllCities() {
		return this.allCities;
	}

	/**
	 * Returns ArrayList of all flights from the dataset
	 * 
	 * @return List<Flight>
	 */
	List<Flight> getAllFlights() {
		return this.allFlights;
	}
}

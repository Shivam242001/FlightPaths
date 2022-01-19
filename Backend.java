// --== CS400 File Header Information ==--
// Name: Yash Rustagi
// Email: yrustagi@wisc.edu
// Team: EF red
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * Backend class that manages the creation and updation of 
 * Dijkstra's algorithm
 * @author yashrustagi
 */
public class Backend implements BackendInterface {

  private CS400Graph<Airport> graph;
  private DataReader dataRead;

  /**
   * One argument constructor that instantiates the class and reads the data with
   * the given file path
   * @param args
   * @throws FileNotFoundException
   * @throws IOException
   * @throws DataFormatException
   */
  public Backend(String[] args) throws FileNotFoundException, IOException, DataFormatException {
    this.dataRead = new DataReader(args);
    this.graph = new CS400Graph<Airport>();
    try {
      this.buildGraph();
    } catch (Exception e) {
    }
  }

  /**
   * Returns an ArrayList of all cities
   */
  @Override
  public ArrayList<String> cities() {
    return (ArrayList<String>) dataRead.getAllCities();
  }

  /**
   * Returns an ArrayList of airport codes for a given city
   * @param city
   */
  @Override
  public ArrayList<String> getAirportCodes(String city) {
    ArrayList<Airport> airports = (ArrayList<Airport>) dataRead.getAllAirports(city);
    ArrayList<String> airportCodes = new ArrayList<String>();
    for (Airport air : airports) {
      airportCodes.add(air.code);
    }
    return airportCodes;
  }

  /**
   * Will create a graph for the algorithm using insertVertex and insertEdge
   * 
   * @param file the reading file
   * @return
   * @throws FileNotFoundException
   */
  @Override
  public void buildGraph() {

    for (Airport a : dataRead.getAllAirports()) {
      graph.insertVertex(a);
    }
    for (Flight f : dataRead.getAllFlights()) {
      graph.insertEdge(f.source, f.destination, f.distance);
    }
  }

  /**
   * Finds the shortest path between the given origin and destination
   * and returns a list
   * @param origin
   * @param target
   */
  public List<Airport> implementDijkstras(String origin, String target) {

    Airport startAirport = null;
    Airport endAirport = null;

    for (Airport air : graph.vertices.keySet()) {
      if (air.code.equals(origin)) { //if the code equals origin's code
        startAirport = air;
      }

      if (air.code.equals(target)) { //if the code equals destination's code
        endAirport = air;
      }
    }

    if ((startAirport == null) || (endAirport == null)) {
      throw new NoSuchElementException("The given airport codes are invalid. Kindly enter valid airport codes.");
    }

    return graph.shortestPath(startAirport, endAirport);
  }

  /**
   * Returns the total cost of the path between the given origin
   * and the destination
   * @param origin
   * @param target
   */
  @Override
  public Integer dijkstrasCost(String origin, String target) {
    Airport startAirport = null;
    Airport endAirport = null;

    for (Airport air : graph.vertices.keySet()) {
      if (air.code.equals(origin)) { //if the code equals origin's code
        startAirport = air;
      }

      if (air.code.equals(target)) { //if the code equals destination's code
        endAirport = air;
      }
    }

    if ((startAirport == null) || (endAirport == null)) {
      throw new NoSuchElementException("");
    }

    return graph.getPathCost(startAirport, endAirport);
  }

}

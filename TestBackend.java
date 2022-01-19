// --== CS400 File Header Information ==--
// Name: Yash Rustagi
// Email: yrustagi@wisc.edu
// Team: EF red
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Junit5 test class
 * 
 * @author yashrustagi
 *
 */
public class TestBackend {

  /**
   * Test the total number of cities in the input file
   */
  @Test
  public void testCities() {
    Backend back = null;
    List<String> cities = null;
    try {
      back = new Backend(new String[] { "routes-medium.csv" });
      cities = back.cities();

    } catch (Exception e) {
      fail("File not found or did not execute");
    }
    assertEquals(369, cities.size());
  }

  /**
   * Checks for the ArrayList of all airports within a specific city from the
   * medium csv file
   */
  @Test
  void testCitySpecificAirports() {
    Backend back = null;
    List<String> airports = null;
    try {
      back = new Backend(new String[] { "routes-medium.csv" });
      airports = back.getAirportCodes("San Jose, CA");
    } catch (Exception e) {
      fail("File not found or did not execute");
    }
    ArrayList<String> cityairports = new ArrayList<String>();
    cityairports.add("SJC");
    assertEquals(airports, cityairports);
  }

  /**
   * Checks for the total path cost for the given origin and destination
   */
  @Test
  void testDijkstrasCost() {
    Backend back = null;
    try {
      back = new Backend(new String[] { "routes-tiny.csv" });

    } catch (Exception e) {
      fail("File not found or did not execute");
    }
    assertEquals(back.dijkstrasCost("KTN", "SNS"), 1377);
  }

  /**
   * Checks the implementDijkstra's method. Checks for the shortest path for the
   * origin to the destination
   */
  @Test
  void testImplementDijkstras() {
    Backend back = null;
    List<String> airports = null;

    try {
      back = new Backend(new String[] { "routes-tiny.csv" });
      List<Airport> results = back.implementDijkstras("KTN", "SNS");
      assertEquals(3, results.size());
      assertEquals("KTN", results.get(0).code);
      assertEquals("Ketchikan, AK", results.get(0).city);
      assertEquals("OAK", results.get(1).code);
      assertEquals("Oakland, CA", results.get(1).city);
      assertEquals("SNS", results.get(2).code);
      assertEquals("Salinas, CA", results.get(2).city);

    } catch (Exception e) {
      fail("File not found or did not execute");
    }
  }

  /**
   * Checks for all airports within a specific city from the large csv file
   */
  @Test
  void testFromLargeFile() {
    Backend back = null;
    List<String> airports = null;
    try {
      back = new Backend(new String[] { "routes-large.csv" });
      airports = back.getAirportCodes("Madison, WI");
    } catch (Exception e) {
      fail("File not found or did not execute");
    }
    ArrayList<String> cityairports = new ArrayList<String>();
    cityairports.add("MSN");
    assertEquals(airports, cityairports);

  }
}

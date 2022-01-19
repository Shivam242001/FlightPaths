// --== CS400 File Header Information ==--
// Name: Yash Rustagi
// Email: yrustagi@wisc.edu
// Team: EF red
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl

import java.util.ArrayList;
import java.util.List;

public interface BackendInterface {
  
  public ArrayList<String> cities();
  
  public ArrayList<String> getAirportCodes(String city);
  
  public void buildGraph();
  
  public List<Airport> implementDijkstras(String origin, String target);
    
  public Integer dijkstrasCost(String origin, String target);
  
}


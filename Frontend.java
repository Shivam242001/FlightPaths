// --== CS400 File Header Information ==--
// Name: Aayush Dani
// Email: ardani@wisc.edu
// Team: EF Red
// Role: Frontend Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
 

/**
 * This is the class that renders the User Interface that allows the user to communicate 
 * and use the Airline Simulator application.
 */
public class Frontend {

    /**
     * This is the method that is responsible for rendering the frontend and allowing 
     * the user to effectively interact with the application.  
     *
     * @param args The list of String values passed with the application
     */
    public static void main(String[] args) {

        // creates the backend interface variable
        Backend backend = null;

        // attempts to initialize a backend object, and returns an exception if the given file cannot be found
        try {
            backend = new Backend(args);
        } catch (Exception e) {
            System.out.println("ERROR: The given file could not be found!");
            System.exit(0);
        }
        
        // initializes the scanner object that accepts all inputs from the user
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------------------\n");
        System.out.println("      WELCOME TO OUR AIRLINE SIMULATOR (US EDITION)!\n");
        System.out.println("----------------------------------------------------------");

        // the boolean value that depicts whether the program is currently running or not
        boolean programRunning = true;

        // the while loop that houses the working of the application
        while (programRunning == true) {
            System.out.println("\n\n===============  MAIN MENU ==================\n");
            System.out.println("Choose one of the following options (1-2):");
            System.out.println("1) Find optimal path between airports");
            System.out.println("2) Search for airport codes and find the optimal path");
            System.out.println("(type 'exit' if you want to terminate the simulator)");

            // takes in the input for the main menu from the user (as depicted above)
            String mainMenuSelection = scanner.nextLine();

            // allows the user to terminate the program at this point
            if (mainMenuSelection.equals("exit")) {
                break;
            }

            Integer integerMainMenuSelection = 0;

            // checks if the input is a valid integer (since it is definitely not 'exit')
            try {
                integerMainMenuSelection = Integer.valueOf(mainMenuSelection);
            } catch (Exception e) {
                System.out.println("\nERROR: The given input is invalid!");
                continue;
            }

            // the user picked the option to find the optimal route between two known airport codes
            if (integerMainMenuSelection.equals(1)) {

                boolean optimalPath = true;

                // runs this particular choice of the users till termination
                while (optimalPath == true) {
                    
                    // sets departure airport code
                    System.out.print(
                            "\nEnter Departure Airport Code (or 'M' for Main Menu or 'exit' to terminate the simulator): ");
                    String startingAirportCode = scanner.nextLine();

                    // allows the user to return to the Main Menu or simply terminate the program
                    if (startingAirportCode.equals("M")) {
                        break;
                    } else if (startingAirportCode.equals("exit")) {
                        programRunning = false;
                        break;
                    }

                    // sets arrival airport code
                    System.out.print(
                            "Enter Arrival Airport Code (or 'M' for Main Menu or 'exit' to terminate the simulator): ");
                    String arrivalAirportCode = scanner.nextLine();

                    // allows the user to return  to the Main Menu or simply terminate the program
                    if (arrivalAirportCode.equals("M")) {
                        break;
                    } else if (arrivalAirportCode.equals("exit")) {
                        programRunning = false;
                        break;
                    }

                    // attempts to calculate the shortest/optimal path between the two given airports
                    // if the given codes are invalid or a path cannot be found, then an error is returned and allows
                    // the user to re-enter new airport codes
                    try {

                        // applies Dijstra's algorithm
                        List<Airport> path = backend.implementDijkstras(startingAirportCode, arrivalAirportCode);
                        String pathString = "";

                        // cleans and displays the path in a readable format
                        for (int i = 0; i < path.size(); i++) {
                            if (i == path.size() - 1) {
                                pathString += (path.get(i).city + " (" + path.get(i).code + ")");
                            } else {
                                pathString += (path.get(i).city + " (" + path.get(i).code + ")" + " -> ");
                            }
                        }
                        
                        // displays the optimal path 
                        System.out.println("\n\n==================== OPTIMAL PATH FOUND ====================\n");
                        System.out.println(pathString);
                        
                        // calculates the cost/distance travelled along the path
                        Integer distance = backend.dijkstrasCost(startingAirportCode, arrivalAirportCode);
                        System.out.println("Distance Traveled for Path Found: " + distance + " km");

                        // allows the user to either try to find an optimal path between a different set of airports
                        // or return to the main menu, or exit the program
                        while (true) {
                            System.out.println("\n\nChoose one of the following options (1-2)");
                            System.out.println("1) Find optimal path between airports");
                            System.out.println("2) Main Menu");
                            System.out.println("(type 'exit' if you want to terminate the simulator)");

                            String subMenuSelection = scanner.nextLine();

                            // sets the running of this particular flow to false, and sets the program as done
                            if (subMenuSelection.equals("exit")) {
                                optimalPath = false;
                                programRunning = false;
                                break;
                            }

                            Integer integerSubMenuSelection = 0;
                            
                            // checks if input is valid
                            try {
                                integerSubMenuSelection = Integer.valueOf(subMenuSelection);
                            } catch (Exception e) {
                                System.out.println("\nERROR: The given input is invalid!");
                                continue;
                            }

                            if (integerSubMenuSelection.equals(1)) {
                                break;
                            } else if (integerSubMenuSelection.equals(2)) {
                                optimalPath = false;
                                break;
                            } else {
                                System.out.println("\nERROR: The given input is invalid!");
                            }
                        }

                    } catch (NoSuchElementException nse) {
                        System.out.println("\nERROR: " + nse.getMessage());
                        continue;
                    }
                }
            } else if (integerMainMenuSelection.equals(2)) {
                // allows the user to search for aiport codes, and find the optimal path between those airports

                // gets the list of all cities from the backend
                boolean searchForStartAirportCodes = true;
                ArrayList<String> cities = backend.cities();

                // initializes the variables the will store the starting and destination airport code
                String selectedStartingAirportCode = null;
                String selectedEndAirportCode = null;

                // allows the user to search for and set the starting airport
                while (searchForStartAirportCodes == true) {
                    System.out.println("\n=====================================");
                    System.out.println("     Searching for Origin Airport     ");
                    System.out.println("======================================");

                    System.out.println("\nChoose from any of the following cities (1-" + cities.size() + ")");
                    int count = 1;

                    for (String city : cities) {
                        System.out.println("[" + count + "] " + city);
                        count++;
                    }
                    System.out.println("(type 'exit' if you want to terminate the simulator)");

                    // allows the user to the select a city, or terminate the entire program
                    String subMenuSelection = scanner.nextLine();

                    if (subMenuSelection.equals("exit")) {
                        searchForStartAirportCodes = false;
                        programRunning = false;
                        break;
                    }

                    Integer integerSubMenuSelection = 0;

                    try {
                        integerSubMenuSelection = Integer.valueOf(subMenuSelection);
                    } catch (Exception e) {
                        System.out.println("\nERROR: The given input is invalid!");
                        continue;
                    }

                    // checks if the given interger is in the list of cities, i.e, valid input
                    if (integerSubMenuSelection >= 1 && integerSubMenuSelection <= cities.size()) {
                        String selectedCity = cities.get(integerSubMenuSelection - 1);
                        ArrayList<String> codes = null;

                        // attempts to get the airport codes for all airports in the city
                        try {
                            codes = backend.getAirportCodes(selectedCity);
                        } catch (NoSuchElementException nse) {
                            System.out.println(
                                    "\nThe given city is not in our current database. Please try another city!");
                        }

                        System.out.println("\n==============================================");
                        System.out.println("        Airport codes for " + selectedCity);
                        System.out.println("==============================================\n");

                        int codeCount = 1;

                        for (String code : codes) {
                            System.out.println("[" + codeCount + "] " + code);
                            codeCount++;
                        }

                        // allows the user to choose a particular aiport code from the chosen city
                        while (true) {
                            System.out.println("\nChoose one of the above airport codes (1-" + codes.size() + ")");
                            System.out.println("(type 'exit' if you want to terminate the simulator)");

                            String secondSubMenuSelection = scanner.nextLine();

                            if (secondSubMenuSelection.equals("exit")) {
                                searchForStartAirportCodes = false;
                                programRunning = false;
                                break;
                            }

                            Integer integerSecondSubMenuSelection = 0;

                            try {
                                integerSecondSubMenuSelection = Integer.valueOf(secondSubMenuSelection);
                            } catch (Exception e) {
                                System.out.println("\nERROR: The given input is invalid!");
                                continue;
                            }

                            // confirms origin aiport selection
                            if (integerSecondSubMenuSelection >= 1 && integerSecondSubMenuSelection <= codes.size()) {
                                selectedStartingAirportCode = codes.get(integerSecondSubMenuSelection - 1);

                                System.out.println("\nOrigin Airport Code Selected: " + selectedStartingAirportCode);

                                searchForStartAirportCodes = false;
                                break;
                            } else {
                                System.out.println("\nERROR: The given input is invalid!");
                                continue;
                            }
                        }
                    } else {
                        System.out.println("\nERROR: The given input is invalid! Kindly re-enter a valid input.");
                        continue;
                    }
                }

                boolean searchForEndAirportCodes = true;

                // applies the exact same logic as above for loop, except for the destination airport
                while (searchForEndAirportCodes == true) {
                    System.out.println("\n=========================================");
                    System.out.println("     Searching for Destination Airport     ");
                    System.out.println("===========================================");

                    System.out.println("\nChoose from any of the following cities (1-" + cities.size() + ")");
                    int count = 1;

                    for (String city : cities) {
                        System.out.println("[" + count + "] " + city);
                        count++;
                    }
                    System.out.println("(type 'exit' if you want to terminate the simulator)");

                    String subMenuSelection = scanner.nextLine();

                    if (subMenuSelection.equals("exit")) {
                        searchForEndAirportCodes = false;
                        programRunning = false;
                        break;
                    }

                    Integer integerSubMenuSelection = 0;

                    try {
                        integerSubMenuSelection = Integer.valueOf(subMenuSelection);
                    } catch (Exception e) {
                        System.out.println("\nERROR: The given input is invalid!");
                        continue;
                    }

                    if (integerSubMenuSelection >= 1 && integerSubMenuSelection <= cities.size()) {
                        String selectedCity = cities.get(integerSubMenuSelection - 1);
                        ArrayList<String> codes = null;

                        try {
                            codes = backend.getAirportCodes(selectedCity);
                        } catch (NoSuchElementException nse) {
                            System.out.println(
                                    "\nThe given city is not in our current database. Please try another city!");
                        }

                        System.out.println("\n==============================================");
                        System.out.println("        Airport codes for " + selectedCity);
                        System.out.println("==============================================\n");

                        int codeCount = 1;

                        for (String code : codes) {
                            System.out.println("[" + codeCount + "] " + code);
                            codeCount++;
                        }

                        while (true) {
                            System.out.println("\nChoose one of the above airport codes (1-" + codes.size() + ")");
                            System.out.println("(type 'exit' if you want to terminate the simulator)");

                            String secondSubMenuSelection = scanner.nextLine();

                            if (secondSubMenuSelection.equals("exit")) {
                                searchForEndAirportCodes = false;
                                programRunning = false;
                                break;
                            }

                            Integer integerSecondSubMenuSelection = 0;

                            try {
                                integerSecondSubMenuSelection = Integer.valueOf(secondSubMenuSelection);
                            } catch (Exception e) {
                                System.out.println("\nERROR: The given input is invalid!");
                                continue;
                            }

                            if (integerSecondSubMenuSelection >= 1 && integerSecondSubMenuSelection <= codes.size()) {
                                selectedEndAirportCode = codes.get(integerSecondSubMenuSelection - 1);

                                System.out.println("\nDestination Airport Code Selected: " + selectedEndAirportCode);

                                searchForEndAirportCodes = false;
                                break;
                            } else {
                                System.out.println("\nERROR: The given input is invalid!");
                            }
                        }
                    } else {
                        System.out.println("\nERROR: The given input is invalid! Kindly re-enter a valid input.");
                        continue;
                    }
                }

                // calculates the optimal path and distance between the two chosen airports
                try {
                    List<Airport> path = backend.implementDijkstras(selectedStartingAirportCode,
                            selectedEndAirportCode);
                    String pathString = "";

                    for (int i = 0; i < path.size(); i++) {
                        if (i == path.size() - 1) {
                            pathString += (path.get(i).city + " (" + path.get(i).code + ")");
                        } else {
                            pathString += (path.get(i).city + " (" + path.get(i).code + ")" + " -> ");
                        }
                    }

                    System.out.println("\n\n==================== OPTIMAL PATH FOUND ====================\n");
                    System.out.println(pathString);

                    Integer distance = backend.dijkstrasCost(selectedStartingAirportCode, selectedEndAirportCode);
                    System.out.println("Distance Traveled for Path Found: " + distance + " km");

                    while (true) {
                        System.out.println("\n\nChoose one of the following options (1-2)");
                        System.out.println("1) Main Menu");
                        System.out.println("2) Quit");
                        System.out.println("(type 'exit' if you want to terminate the simulator)");

                        String subMenuSelection = scanner.nextLine();

                        if (subMenuSelection.equals("exit")) {
                            programRunning = false;
                            break;
                        }

                        Integer integerSubMenuSelection = 0;

                        try {
                            integerSubMenuSelection = Integer.valueOf(subMenuSelection);
                        } catch (Exception e) {
                            System.out.println("\nERROR: The given input is invalid!");
                            continue;
                        }

                        if (integerSubMenuSelection.equals(1)) {
                            break;
                        } else if (integerSubMenuSelection.equals(2)) {
                            programRunning = false;
                            break;
                        } else {
                            System.out.println("\nERROR: The given input is invalid!");
                        }
                    }
                } catch (NoSuchElementException nse) {
                    System.out.println("\nERROR: " + nse.getMessage());
                    continue;
                }
            } else {
                System.out.println("\nERROR: The given input is invalid!");
                continue;
            }
        }

        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("      THANK YOU FOR USING OUR SIMULATOR!\n");
        System.out.println("----------------------------------------------------------");

        scanner.close();
    }
}

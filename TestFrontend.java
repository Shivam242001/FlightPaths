// --== CS400 File Header Information ==--
// Name: Aayush Dani
// Email: ardani@wisc.edu
// Team: EF Red
// Role: Frontend Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.PrintStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class contains the junit test suite for the Airport Simulator Frontend class
 */
public class TestFrontend {

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'exit' as a command. When the front end terminates, the tests succeeds. 
   * The test fails explicitely if the application does not terminate.
   */

  @Test
  public void testEnterExitToExit() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;

    try {
      // set the input stream to our input
      String input = "exit";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      String[] args = {"routes-tiny.csv"};
      Frontend.main(args);

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);

      String appOutput = outputStreamCaptor.toString();
      String expectedOutput = "\n----------------------------------------------------------\n\n"
          + "      THANK YOU FOR USING OUR SIMULATOR!\n\n" + "----------------------------------------------------------";

      if (appOutput.contains(expectedOutput))
        appOutput = expectedOutput;

      assertEquals("Program failed to terminate correctly upon entering the 'exit' command.", 
        expectedOutput, appOutput);
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);

      fail(e.getMessage());
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in '1' + \n' + 'exit' as a command. The test succeeds if the front
   * end moves down the correct program flow. It fails if the program either goes down 
   * the wrong flow or terminates prematurely.
   */

  @Test
  public void testMainMenuEnter1() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;

    try {
      // set the input stream to our input 
      String input = "1" + System.lineSeparator() + "exit";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      String[] args = {"routes-tiny.csv"};
      Frontend.main(args);

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);

      String appOutput = outputStreamCaptor.toString();
      String expectedOutput = "Enter Departure Airport Code (or 'M' for Main Menu or 'exit' to terminate the simulator): ";

      if (appOutput.contains(expectedOutput))
        appOutput = expectedOutput;

      assertEquals("Program failed at choosing option 1 on the main menu.", expectedOutput, appOutput);
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);

      fail(e.getMessage());
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in '2' + '\n' + 'exit' as a command.
   * The test succeeds if the front end moves down the correct program flow. It 
   * fails if the program either goes down the wrong flow or terminates prematurely.
   */

  @Test
  public void testMainMenuEnter1AndExit() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input 
      String input = "1" + System.lineSeparator() + "exit";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      String[] args = {"routes-tiny.csv"};
      Frontend.main(args);

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);

      String appOutput = outputStreamCaptor.toString();
      String expectedOutput = "THANK YOU ";

      if (appOutput.contains(expectedOutput))
        appOutput = expectedOutput;

      assertEquals("Program failed at choosing option 1 from the main menu and exiting from the application.", expectedOutput, appOutput);
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);

      fail(e.getMessage());
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in '1' + '\n' + 'MLU' + '\n' + 'TUL' + '\n' + 'exit' as a command.
   * The test succeeds if it returns the optimal path between the listed airports, and 
   * fails otherwise.
   */

  @Test
  public void testFindOptimalPath() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;

    try {
      // set the input stream to our input
      String input = "1"+ System.lineSeparator() + "MLU" + System.lineSeparator() 
              + "TUL" + System.lineSeparator() + "exit";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      String[] args = {"routes-tiny.csv"};
      Frontend.main(args);

      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      // add all tests to this method

      String appOutput = outputStreamCaptor.toString();
      String expectedOutput = "Monroe, LA (MLU) -> Tulsa, OK (TUL)";

      if (appOutput.contains(expectedOutput))
        appOutput = expectedOutput;

      assertEquals( "Program failed to provide the correct optimal path for the input: MLU -> TUL", 
        expectedOutput, appOutput);
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
    
      fail(e.getMessage());
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in '2' + '\n' + '102' + '\n' + '1' + '\n' + '75' + '\n' + '1' + '\n' + 'exit' as a command. The test succeeds if it chooses the correctly listed airports and
   * returns the optimal path between them, and fails otherwise.
   */

  @Test
  public void testSearchForAirports() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input
      String input = "2" + System.lineSeparator() + "102" + System.lineSeparator() + "1" + System.lineSeparator() 
              + "75" + System.lineSeparator() + "1" + System.lineSeparator() + 
      "exit";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      String[] args = {"routes-tiny.csv"};
      Frontend.main(args);

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);

      String appOutput = outputStreamCaptor.toString();
      String expectedOutput = "Monroe, LA (MLU) -> Tulsa, OK (TUL) -> Greenville, SC (GSP) -> Houston, TX (IAH) -> Albuquerque, NM (ABQ) -> Chicago, IL (ORD) -> Peoria, IL (PIA) -> Omaha, NE (OMA) -> Charleston, WV (CRW) -> Huntsville, AL (HSV) -> Hickory, NC (HKY) -> Richmond, VA (RIC)";

      if (appOutput.contains(expectedOutput))
        appOutput = expectedOutput;

      assertEquals( "Program failed to search for and showcase the optimal path for inputs: MLU -> RIC",
        expectedOutput, appOutput);
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      
      fail(e.getMessage());
    }
  }
}

import org.junit.*;
import static org.junit.Assert.*;
// import org.junit.BeforeClass;
// import org.junit.Before;
// import org.junit.After;
// import org.junit.Test;
// import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;


public class Lab01Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Lab01Tests");
  } 

  static ByteArrayOutputStream localOut, localErr;
  static PrintStream sysOut, sysErr;
  static String [] empty = {};

  @BeforeClass
  public static void setUp() throws Exception {
    sysOut = System.out;
    sysErr = System.err;
  }

  // Before every test is run, reset the streams to capture
  // stdout/stderr
  @Before
  public void setUpStreams() {
    localOut = new ByteArrayOutputStream();
    localErr = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    System.setErr(new PrintStream(localErr));
  }

  // After every test, restore stdout/stderr
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
    System.setOut(sysOut);
    System.setErr(sysErr);
  }

  // Determine what the newline is on the running system
  String newline = System.getProperty("line.separator");

  // Test the CSHelloLab class
  @Test public void HelloLab_main() {
    HelloLab.main(empty);
    assertEquals("Hello, Lab!"+newline, localOut.toString());
  }

  // Test the CSWisdom class
  @Test public void CSWisdom_main() {
    String output = 
      "If in physics there's something you don't understand, you can always"+newline +
      "hide behind the uncharted depths of nature. You can always blame"+newline +
      "God. You didn't make it so complex yourself. But if your program"+newline +
      "doesn't work, there is no one to hide behind. You cannot hide behind"+newline +
      "an obstinate nature. If it doesn't work, you've messed up."+newline +
      ""+newline +
      "- Edsger Dijkstra"+newline;
    CSWisdom.main(empty);
    assertEquals(output, localOut.toString());
  }

}

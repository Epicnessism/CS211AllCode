public class CheckSum {
  //this method calculates the checksum of an array of integers
  public static int [] computeCheckSum(int [] data) {
    //initiate variables
    int sum1 = 0; 
    int sum2 = 0;
    
    //for loop to create checksum
    for (int i=0; i <= (data.length-1); i++) {
      sum1 = sum1 + data[i];
      sum2 = sum2 + sum1;
    }
    
    //initiate array results with length 2 and set elements equal to sums
    int[] result = new int[2];
    result[0] = sum1;
    result[1] = sum2;
    return result;
  }
  //this method converts a string into an integer array and then calculates the checksum
  public static int [] computeCheckSum(String message) {
    //initiate array ints as the method stringToInts from the class RunCheckSum with variable message
    int[] ints = RunCheckSum.stringToInts(message);
    //return the value of the method computeCheckSum from this class with variable ints
    return computeCheckSum(ints);
  }
  //this method returns a boolean after verifiying that the checksums match
  public static boolean verifyCheckSum(int [] data, int [] expected) {
    //initialize checking array as the method computeCheckSum from this class with variable data
    int[] checking = computeCheckSum(data);
    
    //check if elements in checking is same as elements in expected
    if (checking[0] == expected[0] && checking[1] == expected[1]) {
      return true;
    }
    else {
      return false;
    }
  }
  //returns boolean after verifiying that the converted checksums of a string and hex string match
  public static boolean verifyCheckSum(String message, String expectedHex) {
    //initialize strint as method stringToInts from class RunCheckSum
    int[] strint = RunCheckSum.stringToInts(message);
    //initialize hexint as method hexStringToInts from class RunCheckSum
    int[] hexint = RunCheckSum.hexStringToInts(expectedHex);
    //return value of verifyCheckSum method in this class with variable strint and hexint
    return verifyCheckSum(strint,hexint);
  }
}
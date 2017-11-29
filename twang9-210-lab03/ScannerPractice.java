import java.util.Scanner;
import java.io.File;

public class ScannerPractice {
  public static double sumReals(Scanner input) {
    double sum = 0.0;
    boolean check = false;
    
    while (input.hasNext()) {
      if(input.hasNextDouble()) {
        sum+= input.nextDouble();
      }
      else if(input.hasNext()){
        input.next();
      }
    }
    return sum;
  }
  
  public static double sumReals(String parseString) {
    Scanner arg = new Scanner (parseString);
     
    return sumReals(arg);
  }
  
  public static double sumRealsInFile(String filename) throws Exception {
    Scanner readfile = new Scanner (new File (filename));
    
    return sumReals(readfile);
  }
  
}

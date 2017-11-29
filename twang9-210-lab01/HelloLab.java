//import java.util.Scanner;

public class HelloLab {
  public static void main(String [] args) {
    
    //Scanner sc = new Scanner (System.in);
    
    System.out.println("Hello, Lab!");
    makeQueue(10);
    /*
    int i = 5;  
    for(i=0;i<10;i++){  
      i=i+1;
      //System.out.println(i);
    }  
    System.out.println(i);
    */
  }
    public static int[] makeQueue(int capacity) {
      int[] ans = new int[capacity];
      for (int i=0; i <= ans.length; i++) {
        ans[i] = -1;
        System.out.println(ans[i]);
      }
      System.out.println(ans);
      return ans;
    }
}
public class CountDescents {
  //this method counts the number of descents in integer array xs and returns that number
  public static int countDescents (int[] xs) {
    int countd=1; //declares countd and defaults countd to 1
    
    if (xs.length < 1) { //special case: check if xs has anything in it
      return 0;
    }
    
    for (int i=1; i < xs.length; i++) { //general cases
      if (xs[i-1] < xs[i]) { //checks if the previous number of i was less than i now
        countd++;
      }
    }
    return countd;
  }
}
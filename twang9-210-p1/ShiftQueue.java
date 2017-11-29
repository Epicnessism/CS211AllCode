public class ShiftQueue {
  //this method makes an array of a certain size capacity with values -1
  public static int[] makeQueue(int capacity) {
    //check if capactiy is lessthan 0
    if (capacity < 0) {
      return null;
    }
    //create new array ans with capacity amount of spaces
    int[] ans = new int[capacity];
    for (int i=0; i < ans.length; i++) {
      //for each iteration of loop, set each space to value -1
      ans[i] = -1;
    }
    return ans;
  }
  //this method adds a value newItem to the end of queue
  public static boolean addToEndOfQueue(int[] queue, int newItem) {
    //as long as newItem isn't value -1, for each element, add newItem to first value -1
    if (newItem != -1) {
      for (int i=0; i < queue.length; i++) {
        if (queue[i] == -1) {
          queue[i] = newItem;
          return true;
        }
      }
    }
    return false;
  }
  //this method removes the first element from a queue and shifts all values left one spot
  public static boolean removeFromFrontOfQueue(int[] queue) {
    //check length of queue is more than 0 and first element in queue is not value -1
    if (queue.length != 0 && queue[0] != -1) {
      for (int i=1; i <= queue.length; i++) {
        //as long as the element before is not value -1
        if (queue[i-1] != -1) {
          //make element before this same as element after
          queue[i-1] = queue[i];
          if (i == queue.length-1) {
            //makes last element in queue value -1
            queue[i] = -1;
          }
        }
        else {
          return true;
        }
      }
    }
    return false;
  }
  //adds all elements in queue not of value -1
  public static String queueString(int[] queue) {
    //initiate empty string ans
    String ans = "";
    for (int i=0; i < queue.length; i++) {
      //as long as element in queue is not value -1, add element value to ans string
      if (queue[i] != -1) {
        ans += queue[i] + " ";
      }
  }
    return ans;
}
}








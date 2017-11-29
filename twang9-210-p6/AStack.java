import java.util.*;

public class AStack<T> {
  private Object[] stack; //general object array to hold generics later
  private int cap; //make a variable for capacity, ease of coding
  private int size; //make a variable for size, ease of coding
  
  public AStack() {
    stack = new Object[10]; //sets default to 10
    this.cap = 10; //sets capacity to 10
  }
  
  public AStack(int n) {
    stack = new Object[n]; //when given n, make stack n big
    this.cap = n; //set capacitry to n
  }
  
  public int size() {
    return size; //return the size of array
  }
  
  public int getCapacity() {
    return this.cap; //return max capacity of array
  }
  
  public void push(T x) { //checks for space, if not makes space, then adds input to the stack
    if(this.size() == cap) { //check if full
      Object[] temp = new Object[2*cap]; //double capacity if full
      for(int i=0; i < cap;i++) { //move everything over to new stack
        temp[i] = stack[i]; 
      }
      this.cap = 2*cap; //double the capacity val
      this.stack = temp; //make temp the new official stack
    }
    stack[this.size()] = x; //add input x to the end of the size of stack
    size++; //add 1 to value of stack;
  }
  
  public T pop() { //removes the most recent element of stack
    if (this.size() == 0) { //checks if stack is empty
      throw new RuntimeException("Stack empty"); //throws runtimeexception
    }
    int last = this.size()-1; //finds the last value of the stack size
    @SuppressWarnings("unchecked") //suppress warnings from java
    T ans = (T) stack[last]; //cast the stack element to the type T and give to ans
    stack[last] = null; //remove element last of stack
    size--; //decrease size by one
    return ans; //return ans
  }
  
  public T top() { //finds whatever is last on the stack
    if (this.size() == 0) { //if the stack is empty
      throw new RuntimeException("Stack empty"); //throw runtimeexpcetion
    }
    int last = this.size()-1; //find last index of stack
    @SuppressWarnings("unchecked") //suppress warnings from java
    T ans = (T) stack[last]; //give stack element last to ans, casting to T type
    return ans; //return ans
  }
  
  public String toString() { //return a string of the stack
    String ans = "["; //starting string
    for (int i=0; i < this.size();i++) { //for size of stack
      ans += stack[i]; //add each element to ans string
      if (i != this.size()-1) { //as long as not last element in stack
        ans += ", "; //add a comma and space
      }
    }
    ans +="]"; //add closing string
    return ans; //return ans
  }
  
  public List<T> toList() { //converting stack to a list type
    List<T> ans = new ArrayList<T>(); //make a new list type T ans
    for(int i=0; i < this.size();i++) { //loop through stack
      @SuppressWarnings("unchecked") //supress warnings from java
      T tempval = (T) stack[i]; //grab the current element of stack
      ans.add(tempval); //add to arraylist ans
    }
    return ans; //return ans
  }
  
}
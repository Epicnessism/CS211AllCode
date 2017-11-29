import java.util.ArrayList;
public class ALUtils{
  // Creates a copy of the parameter a. Reverses the order of elements
  // in the copy and returns the reversed copy. Assumes a is non-null.
  public static <T> ArrayList<T> reverse(ArrayList<T> a){
    // Your definition here 
    ArrayList<T> copya = new ArrayList<T>();
    for (int i= a.size()-1; i >= 0; i--) {
      copya.add(a.get(i));
    }
    return copya;
  }

  // Creates a copy of the given ArrayList a and rotates the copy to
  // the right by the given shift.  Elements at high indicies wrap
  // around to lower indices.  Assumes parameter a is non-null and
  // that shift is a non-negative number. Returns the rotated copy.
  public static <T> ArrayList<T> rotate(ArrayList<T> a, int shift){
    // Your definition here 
    if (shift == 0) {
      return a;
    }
    if (a.size() == 0) {
      return a;
    }
    while (shift > a.size()) {
      shift -= a.size();
    }
    ArrayList<T> copya = new ArrayList<T>();
    for (int i= a.size()-shift; i < a.size();i++) {
      copya.add(a.get(i));
    }
    for (int i=0; i < a.size()-shift;i++) {
      copya.add(a.get(i));
    }
    return copya;
  }
}

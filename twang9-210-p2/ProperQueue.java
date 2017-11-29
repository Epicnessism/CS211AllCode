public class ProperQueue {
  private int size;
  private Integer[] elements;
  
  public ProperQueue (int maxCapacity) {
    if (maxCapacity <= 0) { //checks if maxcapacity is negative, if so set to size 0
      elements = new Integer[0];
    }
    else {
      elements = new Integer[maxCapacity];
    }
  }
  public int getSize() {
    return size;
  }
  public int getCapacity() {
    return elements.length;
  }
  public boolean isFull() {
    if (elements[elements.length-1] != null) {
      return true;
    }
    return false;
  }
  public boolean isEmpty() {
    if (elements[0] == null) {
      return true;
    }
    else {
      return false;
    }
  }
  public String toString() {
    String str = "";
    if (elements.length == 0) {
      return str;
    }
    if (elements[0] == null) {
      return str;
    }
    else {
      str += elements[0];
    }
    for (int i=1;i < elements.length;i++) {
      if (elements[i] == null) {
        return str;
      }
      str+=" ";
      str+= elements[i];
    }
    return str;
  }
  public boolean add (Integer e) {
    if (e == null) {
      throw new RuntimeException("Cannot add null");
    }
    else if (elements.length != 0) {
    for (int i=0; i < elements.length; i++) {
      if (elements[i] == null) {
        elements[i] = e;
        size++;
        return true;
      }
    }
  }
    throw new RuntimeException("Queue full");
  }
  public boolean offer (Integer e) {
    if (e == null) {
      return false;
    }
    for (int i=0; i < elements.length; i++) {
      if (elements[i] == null) {
        elements[i] = e;
        size++;
        return true;
      }
    }
    return false;
  }
  public Integer remove() {
    if (elements[0] == null || size == 0) {
      throw new RuntimeException("Queue empty");
    }
    int ans = elements[0];
    for(int i=1; i < elements.length; i++) {
      elements[i-1] = elements[i];
      elements[i] = null;
    }
    return ans;
  }
  public Integer poll() {
    if (elements[0] == null || size == 0) {
      return null;
    }
    int ans = elements[0];
    for(int i=1; i < elements.length; i++) {
      elements[i-1] = elements[i];
      elements[i] = null;
    }
    return ans;
  }
  public Integer element() {
    if (elements[0] == null) {
      throw new RuntimeException("Queue empty");
    }
    return elements[0];
  }
  public Integer peek() {
    if (elements[0] == null) {
      return null;
    }
    return elements[0];
  }
}
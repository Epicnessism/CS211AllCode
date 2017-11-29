public class Alphabet {
  private String symbols; //preset field for string of alphabet input
  public static final Alphabet DEFAULT = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 1234567890!@#$%^&*()_+-=[]{}\\|;:'\",./?<>");
  
  //constructor that sets smybols
  public Alphabet(String symbols) {
    this.symbols = symbols;
  }
  //returns the index in the alphabet of char c
  public int indexOf(char c){
    int index;
    index = symbols.indexOf(c);
    if (index == -1) { //when c is not the index, gives -1 which then throws an exception
      throw new NotInAlphabetException(c,DEFAULT);
    }
    return index;
  }
  //returns the char in the alphabet at index i
  public char get(int i){
    if (i > symbols.length() || i < 0) { //if the input int is out of range or negative throw exception
      throw new NotInAlphabetException('c',DEFAULT);
    }
    return symbols.charAt(i);
  }
  //returns the length of the alphabet as int
  public int length() {
    return symbols.length();
  }
  //returns the alphabet
  public String getSymbols() {
    return symbols;
  }
  //returns a string format of the alphabet
  public String toString() {
    return String.format("Alphabet(%s)",symbols);
  }
  //makes a method to see if two objects are equal
  public boolean equals(Object other) {
    
    if (this == other) {
      return true;
    }
    else {
      return false;
    }
  }
  
}
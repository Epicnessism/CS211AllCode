public class NotInAlphabetException extends RuntimeException {
  public final String msg; //final message to return
  public final char offender; //char that raised error
  public final Alphabet a; //alphabet used, searched through
  
  //constructor for NotInAlphabetException sets input variables
  public NotInAlphabetException(String msg, char offender, Alphabet a) {
    this.msg = msg;
    this.offender = offender;
    this.a = a;
  }
  //second constructor using only 2 input variables, makes preset value for msg
  public NotInAlphabetException(char offender, Alphabet a) {
    this.offender = offender;
    this.a = a;
    this.msg = String.format("Not in alphabet: '%c' not found in %s.",this.offender,this.a);
  }
  //returns the string format of msg
  public String toString() {
    return this.msg;
  }
  
}

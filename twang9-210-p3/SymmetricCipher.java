public abstract class SymmetricCipher extends Cipher {
  protected Alphabet alphabet; //alphabet object being used in this class
  
  //constructor that sets input alphabet
  public SymmetricCipher(Alphabet alphabet) {
    this.alphabet = alphabet;
  }
  //takes input int and wraps it around alphabet appropriately
  public int wrapInt(int i) {
    while (i >= alphabet.length()) { //if i is too big, minus length of alphabet
      i -= alphabet.length();
    }
    while (i < 0) { //if i is negative, add lenght of alphabet to convert to a positive num
      i += alphabet.length();
    }
    return i;
  }
  //takes start index and shift key, adds them together and wraps if needed
  public int rotate(int index, int shift) {
    int results = index+shift;
    if (results >= alphabet.length()) { //if bigger than alphabet length wrap
     int wrapped = wrapInt(results);
     return wrapped;
    }
    else if (results < 0) { //if negative, wrap
      int wrapped = wrapInt(results);
      return wrapped;
    }
    
    return results;
  }
  //returns alphabet
  public Alphabet getAlphabet() {
    return alphabet;
  }
  //takes a string s and encrypts it by iterating through and encrypting one char at a time
  public String encrypt(String s) {
    String newstring = "";
    for (int i=0; i < s.length(); i++) {
      char n = encrypt1(s.charAt(i));
      newstring += n;
    }
    return newstring;
  }
  //takes string s and decrypts it by iterating through and decrypting one char at a time
  public String decrypt(String s) {
    String newstring = "";
    for (int i=0; i < s.length(); i++) {
      char n = decrypt1(s.charAt(i)); //calls decrypt1 each time it loops
      newstring += n;
    }
    return newstring;
  }
  protected abstract char encrypt1(char c); //abstract class, no definition but must be inherited by child class
  protected abstract char decrypt1(char c); //abstract class, no definition but must be inherited by child class
}

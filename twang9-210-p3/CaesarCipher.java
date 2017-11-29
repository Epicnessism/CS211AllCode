public class CaesarCipher extends SymmetricCipher {
  protected int shift; //undefined variable to hold shifting value
  
  //constructor taking shift value and alphabet
  public CaesarCipher(int shift, Alphabet alphabet) {
    super(alphabet); //calls parent constructor to set alphabet
    this.shift = shift;
  }
  //constructor that takes shift value and uses default alphabet
  public CaesarCipher(int shift) {
    super(Alphabet.DEFAULT); //calls parent constructor for alphabet using default alphabet
    this.shift = shift;
  }
  //overrides encrypt1 to encrypt the char c shift places
  @Override public char encrypt1(char c) {
    if (alphabet.indexOf(c) < 0) { //throws exception if index of c is out of range
      throw new NotInAlphabetException(c,alphabet);
    }
    int results = super.rotate(alphabet.indexOf(c), shift); //calls parent method rotate with inputs indeoxofc and shift
    int wrapped = super.wrapInt(results); //calls parent method wrapint with results as input
    return alphabet.get(wrapped);
    
  }
  //overrides decrypt1 dcrypt char c shift places
  @Override public char decrypt1(char c) {
    if (alphabet.indexOf(c) < 0) { //throw exception if out of range
      throw new NotInAlphabetException(c,alphabet);
    }
    int results = super.rotate(alphabet.indexOf(c), (0-shift)); //calls inherited method rotate
    int wrapped = super.wrapInt(results);
    return alphabet.get(wrapped);
  }
  
  public String toString() {
    return String.format("Caesar Cipher (shift=%d)", shift);
  }
  
}

public class VigenereCipher extends SymmetricCipher {
  protected String password; //given password field
  protected int passwordPos; //current position iterating over in password
  
  //constructor that takes string and alphabet inputs and defines them apporiately
  public VigenereCipher(String password, Alphabet alphabet) {
    super(alphabet); //calls parent method to deal with alphabet
    this.password = password;
  }
  //constructor that only takes string input, uses default alphabet
  public VigenereCipher(String password) {
    super(Alphabet.DEFAULT); //calls parent method to deal with default alphabet
    this.password = password;
  }
  //return password
  public String getPassword() {
    return password;
  }
  //overrides encrypr1, changes the super.rotate inputs and increments passwordPos as needed
  @Override protected char encrypt1(char c) {
    if (alphabet.indexOf(c) < 0) {
      throw new NotInAlphabetException(c,alphabet);
    }
    int results = super.rotate(alphabet.indexOf(c), alphabet.indexOf(password.charAt(passwordPos)));
    int wrapped = super.wrapInt(results);
    if (passwordPos < this.password.length()-1) { //increment passwordPos and sets to zero if at end of password
      passwordPos++;
    }
    else {
      passwordPos = 0;
    }
    return alphabet.get(wrapped);
  }
  //overrides decrypt1, changes the super.rotate inputs and increments passwordPos as needed
  @Override protected char decrypt1(char c) {
    if (alphabet.indexOf(c) < 0) {
      throw new NotInAlphabetException(c,alphabet);
    }
    int results = super.rotate(alphabet.indexOf(c), (0-alphabet.indexOf(password.charAt(passwordPos))));
    int wrapped = super.wrapInt(results);
    //increment passpos
    if (passwordPos < this.password.length()-1) { //increment passwordPos and sets to zero at end of password
      passwordPos++;
    }
    else {
      passwordPos = 0;
    }
    return alphabet.get(wrapped);
  }
  //overrides encrpyt, sets passwordPos to 0 and calls inherited encrypt method
  @Override public String encrypt(String s) {
    passwordPos = 0;
    return super.encrypt(s);
  }
  //overrides decrypt, sets passwordPos to 0 and calls inherited decrypt method
  @Override public String decrypt(String s) {
    passwordPos = 0;
    return super.decrypt(s);
  }
  //returns a formatted string
  public String toString() {
    return String.format("Vigenere Cipher (password='%s')",password);
  }
  
}

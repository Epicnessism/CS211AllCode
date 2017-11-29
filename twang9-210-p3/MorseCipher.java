class MorseCipher extends Cipher {
  Alphabet a; //alphabet field
  public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  public static final String[] codes = {
    ".-",    /* A */
    "-...",  /* B */    
    "-.-.",  /* C */    
    "-..",   /* D */
    ".",     /* E */    
    "..-.",  /* F */    
    "--.",   /* G */    
    "....",  /* H */
    "..",    /* I */    
    ".---",  /* J */    
    "-.-",   /* K */    
    ".-..",  /* L */
    "--",    /* M */    
    "-.",    /* N */    
    "---",   /* O */    
    ".--.",  /* P */
    "--.-",  /* Q */    
    ".-.",   /* R */    
    "...",   /* S */    
    "-",     /* T */
    "..-",   /* U */    
    "...-",  /* V */    
    ".--",   /* W */    
    "-..-",  /* X */
    "-.--",  /* Y */    
    "--..",  /* Z */    
    ".----", /* 1 */    
    "..---", /* 2 */
    "...--", /* 3 */    
    "....-", /* 4 */    
    ".....", /* 5 */    
    "-....", /* 6 */
    "--...", /* 7 */    
    "---..", /* 8 */    
    "----.", /* 9 */    
    "-----", /* 0 */
  };
  //constructor that builds variable a as an alphabet
  public MorseCipher() {
    a = new Alphabet(letters);
  }
  //this method takes plaintext and encrypts it to morse code
  public String encrypt(String plainText) {
    String uptext = plainText.toUpperCase(); //first convert string to uppercase
    String mstring = "";
    
    for (int i=0; i < uptext.length(); i++) { //loop through uptext until it hits a space
      if (uptext.charAt(i) == ' ') {
        mstring += "    "; //7 spaces - (3 default after every character)
      }
      else { //otherwise find the codes value of uptext(i)
        mstring += codes[a.indexOf(uptext.charAt(i))];
        if (i < uptext.length()-1) { //as long as not last letter, add 3 spaces
          mstring += "   ";
        }
      }
    }
    return mstring;
  }
  //this method takes a string of morse code and decrypts it into plaintext
  public String decrypt(String cryptText) {
    String mstring = "";
    
    for(String word : cryptText.split("       ")) { //use split to break up string at each 7 spaces
      for (String letter : word.split("   ")) { //then using split at 3 spaces, each letter in the word
        for (int i=0; i < codes.length;i++) { //loops through codes to find if the letter string is an actual morse value
          if (codes[i].equals(letter)) { //if so, take that index value and call alphabet a's .get method to get char
            mstring += a.get(i);
            break;
          }
        }
      }
      mstring += " "; //add a space after each word cycle
    }
    mstring = mstring.substring(0, mstring.length()-1); //gets rid of last space
    return mstring;
  }
  
}
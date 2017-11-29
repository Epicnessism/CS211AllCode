import java.io.*;

public class CensoredWriter extends PrintWriter {
  private String toReplace;
  private String censor = "%!^*#@";
  
  //constructors
  public CensoredWriter(OutputStream o, String c) { //outputstream reads the input file
    super(o);
    toReplace = c;
  }
  public CensoredWriter(File f, String c) throws Exception {
    super(f);
    toReplace = c;
  }
  public CensoredWriter(String s, String c) throws Exception {
    super(s);
    toReplace = c;
  }
  //special method
  public String transform(String s) {
    return s.replaceAll(toReplace, censor);
  }
  //overrided print methods
  @Override public void print(String s) {
    String censored = transform(s);
    super.print(censored);
  }
  
  @Override public void println(String s) {
    String censored = transform(s);
    super.println(censored);
  }
  
}

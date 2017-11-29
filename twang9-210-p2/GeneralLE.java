public class GeneralLE {
  double aval;
  double bval;
  double cval;
  double xval;
  double yval;
  
  public GeneralLE(double a, double b, double c, double x) {
    aval  = a;
    bval  = b;
    cval  = c;
    xval  = x;
    yval  = (cval-aval*xval)/bval;
  }
  public double value() {
    return cval;
  }
  public double getX() {
    return xval;
  }
  public double getY() {
    return yval;
  }
  public void setX(double x) {
    xval = x;
    yval = ((cval-aval*xval)/bval);
  }
  public void setY(double y) {
    yval = y;
    xval = ((cval-bval*yval)/aval);
  }
  public String toString() {
    return String.format("%.2f * x + %.2f * y = %.2f", aval, bval, cval);
  }
  public SlopeInterceptLE toSlopeInterceptLE() {
    double slope = -aval/bval;
    double yint =  cval/bval;
    SlopeInterceptLE rehwrg = new SlopeInterceptLE(slope,yint,xval);
    return rehwrg;
  }
}
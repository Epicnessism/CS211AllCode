// Fill in the definition for this class
public class SlopeInterceptLE {
  // Fields required for the class

  private double newx;
  private double newy;
  private double slope;
  private double yint;
  
  // Fill in the definitions of all methods
  public SlopeInterceptLE(double m, double b){
    //setX(0);
    newx = 0;
    yint = b;
    slope = m;
    newy = slope*newx+yint;
  }

  public SlopeInterceptLE(double m, double b, double x){
    newx = x;
    yint = b;
    slope = m;
    newy = slope*newx+yint;
  }
  
  public double value(){
    return newy;
  }
  
  public double getX(){
    return newx;
  }
  public double getY(){
    return newy;
  }

  public void setX(double x){
    newx = x;
    newy = slope*newx+yint;
  }
  public void setY(double y){
    newy = y;
    newx = (newy-yint)/slope;
  }

  public String toString(){
    return String.format("y = %.2f * x + %.2f", slope, yint);
  }
}

public enum Quadrant {
  
Q1(1,1),Q2(-1,1),Q3(-1,-1),Q4(1,-1);

private int x;
private int y;

private Quadrant(int x,int y) {
 this.x = x;
 this.y = y;
}

public boolean xPositive() {
 if (x > 0) {
  return true;
 }
 return false;
}

public boolean yPositive() {
 if (y > 0) {
  return true;
 }
 return false;
}

public String signPair() {
  String ansx;
  String ansy;
  if (xPositive() == true) {
    ansx = "+";
  }
  else {
    ansx = "-";
  }
  if (yPositive() == true) {
    ansy = "+";
  }
  else {
    ansy = "-";
  }
 return String.format("(%s,%s)",ansx,ansy);
}

public Quadrant flipX() {
   switch(this){
     case Q1:
       return Q2;
     case Q2:
       return Q1;
     case Q3:
       return Q4;
     case Q4:
       return Q3;
   }
   throw new RuntimeException();
}

public static Quadrant fromInts(int x, int y) {
 if (x > 0) {
  if (y > 0) {
   return Q1;
  }
  else {
   return Q4;
  }
 }
 else if (x < 0) {
  if (y > 0) {
   return Q2;
  }
  else {
   return Q3;
  }
 }
 throw new RuntimeException();
}

public static void main(String[] args) {
  Quadrant q;
  int xparse = 0;
  int yparse = 0;
  if (args.length > 1) {
    for(int i=0;i < args.length-1;i+=2) {
      xparse = Integer.parseInt(args[i]);
      yparse = Integer.parseInt(args[i+1]);
      q = Quadrant.fromInts(xparse,yparse);
      System.out.println("("+xparse+","+yparse+") has signs "+q.signPair()+" and is in "+q);
    }
  }
}
}
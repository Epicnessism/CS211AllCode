import java.util.Scanner; // Required to get input
import java.io.File;  // Required to get input from files
import java.io.*;
// A 2D treasure map which stores locations of treasures in an array
// of coordinates
public class TreasureMap{
  // Prompt the user for info on the treasure map and then create it
  
  // COMPLETE THIS METHOD
  public TreasureMap(){
    Scanner input = new Scanner(System.in); //creates new scanner
    System.out.println("Enter map size (2 ints)"); 
    this.rows = input.nextInt(); //gets first int from scanner
    this.cols = input.nextInt(); //gets second int
    System.out.println("How many treasure marks? ");
    int numX = input.nextInt(); //gets third int
    this.treasureLocations = new Coord[numX]; //resizes treasureLocations to fit numbeer of treasures
    
    for (int i=0; i < numX; i++) { //loops through for each treasure
      System.out.println(String.format("Enter treasure %d location (2 ints)",i)); //asks for coordinates of each treasure
      int treasurex = input.nextInt(); //get first int
      int treasurey = input.nextInt(); //get second int
      Coord treasure = new Coord(treasurex, treasurey); //makes a new coord object to store ints
      treasureLocations[i] = treasure; //sets the coresponding location in coord array treasuremaps to object b
    }
    
    System.out.println("Treasure map created");
  }
  // Read the string representation of a map from a file
  // COMPLETE THIS METHOD
  public TreasureMap(String fileName) throws Exception{
    //first scan to find rows and cols and numX values
    Scanner sc = new Scanner(new File(fileName)); //new scanner
    int numX = 0;
    while (sc.hasNextLine()) { //as long as there is a nextline to be read
      String thisline = sc.nextLine(); //grab the nextline
      rows++; //add one to rows
      cols = thisline.length(); //sets cols to length of string
      for (int i=0;i<cols;i++) { //loops trhough each string for X's, if there is, add one to numX
        if (thisline.charAt(i) == 'X') {
          numX++;
        }
      }
    }
    treasureLocations = new Coord[numX]; //resizes treasureLocations to fit
    //second scan to get the coordinates of each treasure
    int temprow = 0; //new temp variables for row, column and numX
    int tempcol = 0;
    int numxcount = 0;
    Scanner sc2 = new Scanner(new File(fileName)); //new scanner
    while (sc2.hasNextLine()) { //as long as there is a nextline
      String thisline = sc2.nextLine(); //grab it
      tempcol = thisline.length(); //make the tempcol variable the length
      for (int i=0;i<tempcol;i++) { //loops through each row looking for X's
        if (thisline.charAt(i) == 'X') {
          Coord treasure = new Coord(temprow,i); //makes a new Coord object to store temprow and i (column)
          treasureLocations[numxcount] = treasure; //adds it to the corresponding place in treasureLocations
          numxcount++; //add one to numxcount afterwards
        }
      }
      temprow++; //afterwards add one to the row num
    }
  }

  int rows, cols;  // How big is the treasure map
  Coord [] treasureLocations; // The locations of treasures

  // true if there is treasure at the given (r,c) coordinates, false
  // otherwise
  // This method does not require modification
  public boolean treasureAt(int r, int c){
    for(int i=0; i<treasureLocations.length; i++){
      Coord coord = treasureLocations[i];
      if(coord.row == r && coord.col == c){
 return true;
      }
    }
    return false;
  }

  // Create a string representation of the treasure map
  // This method does not require modification
  public String toString(){
    String [][] map = new String[this.rows][this.cols];
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 map[i][j] = ".";
      }
    }
    for(int i=0; i<treasureLocations.length; i++){
      Coord c = treasureLocations[i];
      map[c.row][c.col] = "X";
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 sb.append(map[i][j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
  public static void main(String[] args) throws Exception{
    //setInput("4 4 3   0 0 1 1 0 2");
    
    String filename = "testmap1.txt";
    String testMap = 
     //012345678
      ".........\n"+// 0
      "........X\n"+// 1
      "..X......\n"+// 2
      ".....XX..\n"+// 3
      ".........\n"+// 4
      ".........\n";// 5
    PrintWriter fout = new PrintWriter(new File(filename));
    fout.print(testMap);
    fout.close();
    TreasureMap tm = new TreasureMap(filename);
    System.out.println(tm);
    //TreasureMap tm = new TreasureMap();
    //System.out.println(tm.treasureAt(0,0));
  }
}

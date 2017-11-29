import java.util.*;
import java.io.*;

// Represent a 2D maze with a single mouse and multipel cheese
// locations. Positions on the maze can be flagged or unflagged to
// help keep track of search paths.
public class Maze {
  private int rows; //rows param of maze
  private int cols; //cols param of maze
  private String[][] maze; //2D array of the maze
  private boolean[][] flags; //implement 2D array of flags
  
  // Construct a Maze from the contents of a file.  On construction,
  // all flags are cleared.  The file format should be similar to the
  // following.
  // 
  // ||||||||
  // |     M|
  // | | ||||
  // | |    |
  // | ||||||
  // |      |
  // |||||| |
  // | ||   |
  // |C|  |C|
  // ||||||||
  public Maze(File f) throws Exception {
    ArrayList<String> temp = new ArrayList<String>(); //make a new arraylist string
    Scanner sc = new Scanner(new FileReader(f)); //make a scanner to read incoming file
    
    while(sc.hasNextLine()) { // loop through each newline in file, adding it to temp list
      temp.add(sc.nextLine()); //get the line
    }
    this.rows = temp.size(); //get the row of temp by using size()
    this.cols = temp.get(0).length(); //find col of temp by using first element of temp length()
    maze = new String[rows][cols]; //make a new maze using row and col
    flags = new boolean[rows][cols]; //make new flags using row and col
    for (int i=0; i < rows;i++) { //loop through rows
      for (int j=0; j < cols;j++) { //loop through cols
        flags[i][j] = false; //make each element of flags false to start
      }
    }
    
    for(int i=0; i < rows;i++) { //loops through rows
      String[] line = temp.get(i).split(""); //splits each string
      for (int j=0; j < cols;j++) { //loops through cols
        maze[i][j] = line[j]; //adds it to maze
      }
    }
  }

  // Construct a maze form the given string. The format of the string
  // identical to the format of the file above.
  public Maze(String s) throws Exception {
    String[] temp = s.split("\n"); //temp array, split by newline
    this.rows = temp.length; //get rows from length
    this.cols = temp[0].length(); //get cols from temp[0] lenght
    maze = new String[rows][cols]; //make new maze
    flags = new boolean[rows][cols]; //make new flags
    for (int i=0; i < rows;i++) { //loop through rows
      for (int j=0; j < cols;j++) { //loop through cols
        flags[i][j] = false; //make false
      }
    }
    
    for (int i=0; i < rows;i++) { //loop through rows
      String[] line = temp[i].split(""); //split each string to a single string length
      for (int j=0; j < cols;j++) { //loop through cols
        maze[i][j] = line[j]; //add to maze
      }
    }
  }

  // Return how many rows are in the maze.
  public int getRows() {
    return this.rows; //return rows
  }

  // Return how many columns are in the maze.
  public int getCols() {
    return this.cols; //return rows
  }

  // Return the initial location of the mouse in the maze as a
  // coordinate (row,col).  Assume there is exactly one mouse. Return
  // null if no mouse is present.
  public Coord getMouseLocation() {
    for(int i=0; i < rows;i++) { //loop through rows
      for(int j=0; j < cols;j++) { //loop through cols
        if (maze[i][j].equals("M")) { //see if it is "M"
          return new Coord(i,j); //return the Coord if true
        }
      }
    }
    return null; //no mouse
  }

  // Return a list of coordinates of all the chesse locations in the
  // maze. If no cheese is present, return an empty list.
  public List<Coord> getCheeseLocations() {
    ArrayList<Coord> cheeses = new ArrayList<Coord>(); //make new arraylist to hold cheeses
    for(int i=0; i < rows;i++) { //loop through rows
      for(int j=0; j < cols;j++) { //loop through cols
        if (maze[i][j].equals("C")) { //if "C"
          cheeses.add(new Coord(i,j)); //add it to cheeses as a new coord
        }
      }
    }
    return cheeses; //return cheeses
  }

  // Return true if the given coordinate is in bounds for the maze and
  // false otherwise.
  public boolean inBounds(Coord c) {
    if (c.row >= 0 && c.row < rows && c.col >= 0 && c.col < cols) { //if greater than 0, less than rows,cols
      return true; //true
    }
    return false; //false
  }

  // Return true if the given coordinate is open space for the mouse
  // and false otherwise. The status of the flag (set or clear) at the
  // given location should not affect whether true/false is returned.
  public boolean isSpace(Coord c) {
    if(maze[c.row][c.col].equals(" ")) { //see if given location is space
      return true; //true
    }
    return false; //false
  }

  // Return true if the given location refers to a wall and false
  // otherwise.
  public boolean isWall(Coord c) { 
    if(maze[c.row][c.col].equals("|")) { //see if is wall "|"
      return true; //return true
    }
    return false; //false
  }

  // Return true if the given location refers to the starting location
  // of the mouse and false otherwise.
  public boolean isMouse(Coord c) {
    if(maze[c.row][c.col].equals("M")) { //see if "M" 
      return true; //true
    }
    return false; //false
  }

  // Return true if the given location refers to cheese and false
  // otherwise.
  public boolean isCheese(Coord c) {
    if(maze[c.row][c.col].equals("C")) { //if "C"
      return true; //true
    }
    return false; //false
  }

  // Return true if the given location has its flag set and false
  // otherwise.
  public boolean isFlagged(Coord c) {
    if (flags[c.row][c.col] == true) { //if flags coord is true
      return true; //true
    }
    return false; //false
  }

  // Set the flag at the given location to true.  
  public void setFlag(Coord c) {
    flags[c.row][c.col] = true; //set flags coord to true
  }

  // Set the flag at the given location to false.  
  public void clearFlag(Coord c) {
    flags[c.row][c.col] = false; //set flags coord to false
  }

  // Clear all flags in the maze
  public void clearFlags() {
    for (int i=0; i < rows;i++) { //loop through rows
      for (int j=0; j < cols;j++) { //loop through cols
        flags[i][j] = false; //set to false
      }
    }
  }

  // Starting from the parameter start, flag all positions along the
  // path given by the directions list.
  public void flagPath(List<Direction> path, Coord start) {
    
    for (Direction dir : path) { //for each direction in path list of Directions
      Coord ans = Coord.add(start,dir.getChange()); //ans becomes the start coord Coord.add to dir.getChange()
      flags[ans.row][ans.col] = true; //flag it as true
      start = ans; //make ans start for next loop through
    }
  }

  // Create a display string for the maze. This is identical to the
  // input format of the maze except that any open spaces which are
  // flagged should display as periods (.) rather than spaces ( ). For
  // example, the following maze has some open spaces in a path
  // flagged which guide the mouse to the cheese.
  // 
  // ||||||||
  // |.....M|
  // |.| ||||
  // |.|    |
  // |.||||||
  // |......|
  // ||||||.|
  // | ||  .|
  // |C|  |C|
  // ||||||||
  public String toString() {
    String ans = ""; //starting string
    for(int i=0; i < rows;i++) { //loop through rows
      for(int j=0; j < cols;j++) { //loop through cols
        if (flags[i][j] == true && maze[i][j].equals(" ")) { //if ij is flagged and is space
          ans += "."; //add a period to indicate path to cheese
        }
        else {
          ans += maze[i][j]; //else just add string element to ans
        }
      }
      if (i != rows) { //if not the last row
        ans += "\n"; //add a newline
      }
    }
    return ans; //return ans
  }

}
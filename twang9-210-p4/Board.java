import java.util.Scanner;
public class Board {
  
  private int rows, cols; //fields rows and cols, size of the board
  private Gem [][] g; //a 2D gem array to hold the board
  
  // Convenience constructor to build from a 2D array of ints. If
  // negative int appears, treat it as an empty gem. Otherwise, the
  // numbers represent the kinds of the gems to be created.
  public Board(int g[][]) {
    this.rows = g.length; //makes rows the length of the 2D array
    this.cols = g[0].length; //makes cols the length of the first element of g 
    this.g = new Gem [rows][cols]; //make 2d array of gems with rows and cols size
    
    for (int i=0; i<g.length; i++) { //loop through rows
      for (int j=0; j<g[i].length; j++) { //loop through cols
        if (g[i][j] < 0) { //if value of int g[][] is negative, make it an empty gem
          this.g[i][j] = Gem.makeEmpty(); //call makeempty method and sets that to Gem g[i][j]
        }
        else {
          this.g[i][j] = new Gem(g[i][j]); //otherwise, just use the given value and make it a gem, add it to Gem g[][]
        }
      }
    }
  }

   // Make a deep copy of this Board. Requires that the 2D array of
  // gems be copied entirely to prevent shallow references to the same
  // arrays.  One of the required constructors is useful for this method.
  public Board clone() {
    String saved = this.saveString(); //get the savestring of this board
    Board board = this.fromSaveString(saved); //make a new board using the fromsavestring method giving saved as a string
    
    //the forloops below are for trying the flag of gems
    for (int i=0; i < board.getRows(); i++) { //loop through rows of board
      for (int j=0; j < board.getCols(); j++) { //loop through cols of board
        if (g[i][j].flagged()) { //if the original gem at i,j is flagged
          board.gemAt(i,j).setFlag(); //flag the board i,j gem
        }
      }
    }
    return board; //return the cloned board
  }

  // Access the number of rows and columns. Assumes all rows have equal length.
  public int getRows() { //returns rows
    return rows;
  }

  public int getCols() { //returns cols
    return cols;
  }

  // True if the given position is in bounds and contains a gem which
  // can be removed. False if the row/col is out of bounds or the
  // board is empty at the specified position.
  public boolean validGemAt(int r, int c) { 
    if (r < 0 || r >= rows || c < 0 || c >= cols) { //if r,c is less than 0 or greater than rows,cols, return false
      return false;
    }
    return g[r][c].isFilled(); //otherwise return the gem isFilled method
  }

  // Return true if at least one gem exists on the board. False if all
  // board positions are empty.
  public boolean hasValidGem() {
    for (int i=0; i < g.length; i++) { //loop through rows
      for (int j = 0; j < g[i].length; j++) { //loops through cols
        if (g[i][j].isFilled()) { //if there is a filled gem
          return true; //return true
        }
      }
    }
    return false; //if no gems are filled, return false
  }

  // Retrieve gem at given location. Do not do bounds checking; out of
  // bounds positions should automatically raise an
  // IndexOutOfBoundsException.
  public Gem gemAt(int i, int j) {
    return g[i][j]; //return the gem at i,j
  }

  // Clear all flags of gems on the board by invoking their
  // clearFlag() method.
  public void clearFlags() {
    for (int i=0; i < g.length;i++) { //loop through rows
      for (int j=0; j < g[i].length;j++) { //loops through cols
        g[i][j].clearFlag(); //invoke clearFlag method of the gem at i,j
      }
    }
  }
  // You may wish to write some private helper methods to help break
  // this task down into manageable chunks.
  public void doRemovals() {
    for (int i=0;i<g.length;i++) { //loop through rows
      for (int j=0; j<g[i].length; j++) { //loops through cols
        if (g[i][j].flagged()) { //if the gem at i,j is flagged
          g[i][j] = Gem.makeEmpty(); //make the gem an empty gem
        }
      }
    }
    //check up the column to see if it is empty
    for (int i=rows-1;i > 0; i--) { //loops through from bottom row to top
      for (int j=0; j < cols; j++) { // loops through from left to right cols
        if (g[i][j].isEmpty()) { //if there is an empty gem
          for (int k=1; k <= i; k++) { //new loop that counts now many places up the loop goes
            if (g[i-k][j].isFilled()) { //checking to see if there is a filled gem
              g[i][j] = g[i-k][j]; //if so, make the gem coordiantes i,j that gem
              g[i-k][j] = Gem.makeEmpty(); //make the first filled gem found as an empty gem
              break; //break out of the loop and start again
            }
          }
        }
      }
    }
    //collapse columns to the left
    for (int j = 0; j < cols; j++) { //loop through the columns up to cols
      if (g[rows-1][j].isEmpty()) { //if the gem at row-1 and j is empty
        for (int k = 1; k < cols-j; k++) { //start new loop that counts how many places over the loop goes
          if (g[rows-1][j+k].isFilled()) { //when it finally finds a gem that is not empty
            for (int i=0; i < rows; i++) { //loop again to move each gem in that col over to the original empty column
              g[i][j] = g[i][j+k]; //make original column the values of the new column
              g[i][j+k] = Gem.makeEmpty(); //make the new column empty
            }
            break; //break out of hte loop and start from the original loop again
          }
        }
      }
    }
  }
  // Each line ends with a newline (\n) character.
  public String saveString() {
    String ans = ""; //answer string that is empty
    for (int i=0; i < rows; i++) { //loop through the rows
      for (int j=0; j < cols; j++) { //loop throug the cols
        if (g[i][j].isEmpty()) { //if the gem at that point is empty
          ans += ". "; //add a period for its place
        }
        else {
          ans += g[i][j].kindString() + " "; //if not empty, then invoke the kindstring method and add that to ans string
        }
      }
      ans += "\n"; //at the end of each cols loop, add a newline
    }
    return ans; //return ans string
  }

  // Create a board from the given string.  The format accepted should
  // be identical to what is produced by saveString(). Parsing this
  // string will require two passes through the string, first to count
  // the size, then to read the gems and spaces.
  public static Board fromSaveString(String s) {
    Scanner sc1 = new Scanner(s); //make a new scanner
    int row = 0; //row to 0
    int col = 0; //col to 0
    String [] sl = s.split("\n"); //split the string into a string array sl at each newline
    row = sl.length; //make row the length of sl
    col = sl[0].split("\\s+").length; //make col the splitted length of the first element of sl
    
    int [][] g = new int [row][col]; //make new 2d int array with size row,col
    String [][] sll = new String [row][col]; //make new 2d string array with size row,col
    for (int i=0; i < row; i++) { //loop through the rows
      String[] line = sl[i].split("\\s+"); //split each column into a temp string array line
      for (int j=0; j < col; j++) { //loop through cols
        sll[i][j] = line[j]; //add the string of each element of line to sll[i][j]
      }
    }
    //from here, parse each string element in sll into an integer
    //set that gem to the corresponding position on 2d int array g
    for (int i=0; i < row; i++) { //loop through rows
      for (int j=0; j < col; j++) { //loop through cols
        if (sll[i][j].equals(".")) { //check if there is a period, then make the int value -1
          g[i][j] = -1;
        }
        else {
          g[i][j] = Integer.parseInt(sll[i][j]); //otherwise parse the string element into an integer and set that to g[i][j]
        }
      }
    }
    Board board = new Board(g); //make a new board with input of 2d int array g
    return board; //return the board
  }

  // Implementation Provided. Fancy display string version of the
  // board; assumes gem kinds fit in 2 chars. Flagged gems have an
  // asterisk put to the right of them.
  public String toString(){
    StringBuilder sb = new StringBuilder();
    // Col header
    sb.append("   ");
    for(int j=0; j<this.getCols(); j++){
      sb.append(String.format("%3d",j));
    }
    sb.append("\n");
    sb.append("   ");
    for(int j=0; j<this.getCols(); j++){
      sb.append("---");
    }
    sb.append("\n");
    // Main body of board
    for(int i=0; i<this.getRows(); i++){
      sb.append(String.format("%2d|",i));
      for(int j=0; j<this.getCols(); j++){
        Gem g = this.gemAt(i,j);
        char flag = g.flagged() ? '*' : ' ';
        if(j==0){
          sb.append(String.format("%3s%c",g.kindString(),flag));
        }
        else{
          sb.append(String.format("%2s%c",g.kindString(),flag));
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}

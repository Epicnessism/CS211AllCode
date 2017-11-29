import java.util.Scanner;

// Exceptions for conditions that arise during game play
class MoveOutOfBoundsException  extends Exception{}
class PositionOccupiedException extends Exception{}

public class GamePrototype{

  // Place player on board at the given row/col position. Parameter
  // player is a string of the tile that should be shown at the
  // row/col position.
  // 
  // Throw a MoveOutOfBoundsException if the row/col is out of bounds
  //
  // Throw a PositionOccupiedException if a string already exists at
  // the given row/col position
  public static void placeToken(int row, int col,
                                String player, String [][] board)
    throws Exception
  {
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
      throw new MoveOutOfBoundsException();
    }
    else if (board[row][col] != null) {
      throw new PositionOccupiedException();
    }
    else {
      board[row][col] = player;
    }
  }
                                  

  public static void main(String [] args) throws Exception{
    Scanner in = new Scanner(System.in);

    // Read in the size of the game board
    System.out.println("Enter # of rows and cols for the board");
    int rows = in.nextInt();
    int cols = in.nextInt();

    String [][] board = new String[rows][cols];
    int movesMade = 0;
    String player = "A"; 
    while(movesMade < rows*cols){
      // Print board
      System.out.printf("Board (%d x %d), %d moves made\n",rows,cols,movesMade);
      for(int r=0; r<rows; r++){
        System.out.printf("|");
        for(int c=0; c<cols; c++){
          String p = board[r][c]==null ? " " : board[r][c];
          System.out.printf(p);
        }
        System.out.printf("|\n");
      }

      // Request input move
      System.out.printf("Player %s Enter row/col: ",player);
      int row = in.nextInt();
      int col = in.nextInt();

      // MAKE CHANGES TO THE BELOW CODE
      try {
      placeToken(row,col,player,board);
      player = player.equals("A") ? "B" : "A";
      movesMade++;
      }
      catch(MoveOutOfBoundsException e) {
        System.out.println(String.format("Move %d %d out of bounds, Try again",row,col));
      }
      catch(PositionOccupiedException e) {
        player = "B";
        System.out.println(String.format("Position %d %d occupied. Turn lost",row,col));
      }
    }

      // END CHANGEABLE CODE

      // Adjust the above code to handle exceptions thrown by
      // placeToken().
      //
      // If move is out of bounds print a message like
      //   Move -1 2 out of bounds. Try again
      // and allow the player to make another move
      //
      // If the position is occupied print a message like
      //   Position 1 1 occupied. Turn lost
      // and 
    
    // Print board
    System.out.printf("FINAL BOARD (%d x %d)\n",rows,cols);
    for(int r=0; r<rows; r++){
      System.out.printf("|");
      for(int c=0; c<cols; c++){
        String p = board[r][c]==null ? " " : board[r][c];
        System.out.printf(p);
      }
      System.out.printf("|\n");
    }
  }

}

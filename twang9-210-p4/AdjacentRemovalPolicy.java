//AdjacentRemovalPolicy checks gems next to indicated gem in the up,down,left,right directions
//and flags them for removal if they are of the same kind. The score of the move is the square
//of the number of gems removed
public class AdjacentRemovalPolicy implements RemovalPolicy {
  private static int badMoveScore = -1;
  public String description(){
    return "Adjacent gems of the same kind will be removed";
  }

  // Mark all gems connected to the gem at row/col on the given board
  public void flagConnectedGems(int row, int col, Board b) {
    if(!b.validGemAt(row,col)){
      String msg = String.format("Position (%d,%d) invalid on board:\n%s",row,col,b.toString());
      throw new RuntimeException(msg);
    }
    b.clearFlags(); //clear any existing flags on board
    
    Gem center = b.gemAt(row,col); //create the center gem
    center.setFlag(); //set the center gem's flag
    
    if (b.validGemAt(row,col-1)) { //checks if gem to the left of center is a valid gem
      Gem cleft = b.gemAt(row,col-1); //makes the gem to the left of center
      if (center.sameKind(cleft)) { //asserts sameKind() to center
        cleft.setFlag(); //if so, then flag
      }
    }
    if (b.validGemAt(row,col+1)) { //checks if gem to the right of center is a valid gem
      Gem cright = b.gemAt(row,col+1); //makes the gem to the right of center
      if (center.sameKind(cright)) { //asserts sameKind() to center
        cright.setFlag(); //if so, the flag
      }
    }
    if (b.validGemAt(row-1,col)) { //checks if gem to above of center is a valid gem
      Gem ctop = b.gemAt(row-1,col); //makes the gem above center
      if (center.sameKind(ctop)) { //asserts sameKind() to center
        ctop.setFlag(); //if so, then flag
      }
    }
    if (b.validGemAt(row+1,col)) { //checks if gem below center is a valid gem
      Gem cbot = b.gemAt(row+1,col); //makes gem below center
      if (center.sameKind(cbot)) { //asserts sameKind() to center
        cbot.setFlag(); //if so, then flag
      }
    }
  }

  // Determine the score for removing the gem at row/col; do not
  // actually perform the removal on the board, at most flag the gems
  // for removal.  The numeric score is the square of the number of
  // gems removed.
  public int scoreMove(int row, int col, Board b) {
    if(!b.validGemAt(row,col)){ //if not a valid gem on board b at row and col, throw a runtime exception with a message
      String msg = String.format("Position (%d,%d) invalid on board:\n%s",row,col,b.toString()); //create message string to give to exception
      throw new RuntimeException(msg); //throw runtimeexception
    }
    this.flagConnectedGems(row,col,b); //calls the flagconnectedgems method of this policy, flags gems accordingly to row,col on board b
    int num = 0; //number of gems flagged tracker
    for (int i=0; i < b.getRows(); i++) { //looping through each row
      for (int j=0; j < b.getCols(); j++) { //looping through each col
        if (b.gemAt(i,j).flagged() == true) { //if the gem at i,j is flagged, add one to num
          num++;
        }
      }
    }
    return num*num; //square num and return it
  }    
    
  public String toString(){ return "AdjacentRemovalPolicy"; } //return the string of the name of this class
  public String saveString(){ return "AdjancentRemovalPolicy"; } //return the string of hte name of this class
}
    

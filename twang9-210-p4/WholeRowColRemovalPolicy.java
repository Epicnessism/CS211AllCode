//WholeRowColRemovalPolicy removes all gems that are connected in the same row and col continuously
//that are of the same kind as the moved gem. Scoring is the square of the number of gems removed.
public class WholeRowColRemovalPolicy implements RemovalPolicy {
  private static int badMoveScore = -1; //if the move is invalid deduct points
  public String description(){ 
    //return the string description of what this policy does
    return "Adjacent gems in whole row/column will be removed"; 
  }

  // Mark all gems connected to the gem at row/col on the given board
  public void flagConnectedGems(int row, int col, Board b) {
    if(!b.validGemAt(row,col)){ //if it is not a valid gem at row and col, throw runtime exception
      String msg = String.format("Position (%d,%d) invalid on board:\n%s",row,col,b.toString()); //create string msg of error
      throw new RuntimeException(msg); //throw runtimeexception
    }
    b.clearFlags(); //clear any existing flags
    Gem center = b.gemAt(row,col); //make center gem
    center.setFlag(); //set center gem's flag
    //look up the column
    for (int i=row; i >= 0; i--) { //loop through from row to 0
      if (b.gemAt(i,col).sameKind(center)) { //if the gem at location is samekind as center
        b.gemAt(i,col).setFlag(); //flag that gem
      }
      else if (! b.gemAt(i,col).sameKind(center)) { //if not, break from the loop
        break;
      }
    }
    //look down column
    for (int i=row; i < b.getRows(); i++) { //loop through from row to getRows
      if (b.gemAt(i,col).sameKind(center)) { //if gem at that location is samekind as center
        b.gemAt(i,col).setFlag(); //flag that gem
      }
      else if (! b.gemAt(i,col).sameKind(center)) { //if not samekind, break from loop
        break;
      }
    }
    //look left of center
    for (int j=col; j >= 0; j--) { //loop through columns col to 0
      if (b.gemAt(row,j).sameKind(center)) { //if gem at location is samekind
        b.gemAt(row,j).setFlag(); //flag that gem
      }
      else if (! b.gemAt(row,j).sameKind(center)) { //if not samekind, break from loop
        break;
      }
    }
    //look right of center
    for (int j=col; j < b.getCols(); j++) { //loop through columns col to getCols
      if (b.gemAt(row,j).sameKind(center)) { //if gem at location is samekind as center
        b.gemAt(row,j).setFlag(); //flag that gem
      }
      else if (! b.gemAt(row,j).sameKind(center)) { //if not samekind, break from loop
        break;
      }
    }
  }

  // Determine the score for removing the gem at row/col; do not
  // actually perform the removal on the board, at most flag the gems
  // for removal.  The numeric score is the square of the number of
  // gems removed.
  public int scoreMove(int row, int col, Board b) {
    if(!b.validGemAt(row,col)){ //if not a valid gem at location, throw runtimeexception
      String msg = String.format("Position (%d,%d) invalid on board:\n%s",row,col,b.toString()); //create error string message
      throw new RuntimeException(msg); //throw runtimeexception
    }
    this.flagConnectedGems(row,col,b); //invoke flagconnectedgems method at row,col
    int num = 0; //number of flagged gems tracker
    for (int i=0; i < b.getRows(); i++) { //loop through rows
      for (int j=0; j < b.getCols(); j++) { //loop through cols
        if (b.gemAt(i,j).flagged() == true) { //if the gem at that location is flagged
          num++; //add one to num
        }
      }
    }
    return num*num; //return the square of num as the score
  }    
    
  public String toString(){ return "WholeRowColRemovalPolicy"; } //return string of class name
  public String saveString(){ return "WholeRowColRemovalPolicy"; } //return string of class name
}
    

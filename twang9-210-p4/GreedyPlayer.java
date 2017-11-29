public class GreedyPlayer implements Player {
  // Perform a single move in the given game. May throw a
  // QuitGameException to indicate that no move is desired.
  public void executeMove(Game game) {
    Board board = game.getBoard(); //make new board
    int best = 0; //track the highest score possible
    int row = 0; //row value of best move
    int col = 0; //col value of best move
    
    for (int i=0; i < board.getRows(); i++) { //loop through rows of board
      for (int j=0; j < board.getCols(); j++) { //loop through cols of board
        if (board.validGemAt(i,j)) { //if there is a valid gem at board i,j
          RemovalPolicy policy = game.getPolicy(); //get the removalpolicy of game
          policy.flagConnectedGems(i,j,board); //invoke the flagconnectedgem method at i,j of the policy
          if (policy.scoreMove(i,j,board) > best) { //if the scoremove value of policy is greater than best
            best = policy.scoreMove(i,j,board); //make that value the new best value
            row = i; //record the row value
            col = j; //record col value
          }
        }
      }
    }
    game.removeGemAdjustScore(row,col); //implement the best move
    }
  // Return the name of class name of the Player
  public String toString() {
    return "GreedyPlayer"; //return string of name of class greedyplayer
  }

  // Return the class name of the Player
  public String saveString() {
    return "GreedyPlayer"; //return string of name of class greedyplayer
  }
}
import java.util.*;

// Search a maze for a path from mouse to cheese WITHOUT recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
public class IterativeMazeSearch extends MazeSearch{

  // Constructor to initialize any state required to search mazes
  public IterativeMazeSearch() {
    
  }
  
  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. CONSTRAINT: You may not use recursive calls or recursive
  // helper methods.
  public List<Direction> searchForCheese(Maze maze) {
    AStack<Direction> stack = new AStack<Direction>(); //make new stack
    Coord curPos = maze.getMouseLocation(); //first start pos
    maze.setFlag(curPos); //flag the starting position so that we don't go over it again

    for (int i=0; i < Direction.directions.length; i++) { //loop through for each enum 
      Direction dir = Direction.directions[i]; //shorten Direction.directions[i] ease of coding
      curPos = Coord.add(curPos,dir.getChange()); //get the direction change, set to curPos
      
      stack.push(dir); //add the direction to the top of the stack
      if (! maze.inBounds(curPos)) { //check if in bounds
        stack.pop(); //remove from latest element of stack
        curPos = Coord.subtract(curPos,dir.getChange()); //get new curPos
        continue; //skip rest and continue loop
      }
      if(maze.isFlagged(curPos)) { //if the pos is flagged already
        stack.pop(); //remove latest element of stack
        curPos = Coord.subtract(curPos,dir.getChange()); //revert to previous pos
        continue; //skip rest of loop and continue
      }
      maze.setFlag(curPos); //flag the pos if not already
      
      if (maze.isCheese(curPos)) { //if cheese is found
//        System.out.println("Cheese!");
        return stack.toList(); //return the stack in list form
      }
      
      if (maze.isSpace(curPos)) {
        i=0; //reset for loop counter i to 0
        continue; //continue looping
      }
      
      stack.pop(); //remove latest direction
      curPos = curPos.subtract(curPos,dir.getChange()); //revert to old curPos
    }
   return null; //return null otherwise
  }
  
//  public static void main(String[] args) throws Exception{
//    String abundance1str =
//   //012345678901234
//    "|||||||||||||||\n"+
//    "|C         C|||\n"+
//    "|||| ||| ||||||\n"+
//    "|||| |||     ||\n"+
//    "|   M  C C|| ||\n"+
//    "|C|| ||||||| ||\n"+
//    "||||  C  |||C||\n"+
//    "|||||||||||||||\n"
//    ;
//    String fork1str =
//    "        ||||||||||||||\n"+
//    "        |           C|\n"+
//    "||||||||| ||||||||||||\n"+
//    "|M                   |\n"+
//    "||||||||||||||||||||||\n"
//    ;
//    IterativeMazeSearch ob = new IterativeMazeSearch();
//    List<Direction> x = ob.searchForCheese(new Maze(abundance1str));
//    List<Direction> x = ob.searchForCheese(new Maze(fork1str));
  }
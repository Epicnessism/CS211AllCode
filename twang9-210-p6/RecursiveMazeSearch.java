import java.util.*;

// Search a maze for a path from mouse to cheese using recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
public class RecursiveMazeSearch extends MazeSearch{

  // Constructor to initialize any state required to search mazes
  public RecursiveMazeSearch() {
    //empty because reasons...
  }

  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. Employ a recursive helper function to assist with the
  // search.
  public List<Direction> searchForCheese(Maze maze) {
    AStack<Direction> path = new AStack<Direction>(); //initialize stack
    Coord start = maze.getMouseLocation(); //first start
    maze.setFlag(start); //flag the starting position so that we don't go over it again
    if (recursiveSearch(maze, path, start)) { //call initial instance of recursiveSearch
      return path.toList(); //if the call is true, return a stack.toList
    }
    return null; //otherwise null
  }
  
  // Suggested recursive helper function. Takes the maze, a current
  // path, and a current position and continues the search. Returns
  // true if a path to cheese is located and false if the present path
  // is a dead end.
  protected boolean recursiveSearch(Maze maze, AStack<Direction> path, Coord curPos) {
    for(Direction dir : Direction.directions){ //loop through the possible directions
      path.push(dir); //add dir to stack
      curPos = Coord.add(curPos,dir.getChange()); //get the new curPos with the dir change
      if (! maze.inBounds(curPos)) { //check if not out of bounds
        Direction d = path.pop(); //if so, remove from stack
        curPos = Coord.subtract(curPos,dir.getChange()); //backtrack curPos to previous position
        continue; //continue the loop
      }
      if (maze.isFlagged(curPos)) { //if its flagged, undo
        path.pop(); //remove from stack
        curPos = Coord.subtract(curPos,dir.getChange()); //backtrack curPos to previous position
        continue; //continue loop
      }
      maze.setFlag(curPos); //if not, flag it as doing
      
      if (maze.isCheese(curPos)) { //check if cheese found
        return stack.toList(); //true
      }
      
      if (maze.isSpace(curPos)) { //if it is a space
        if(recursiveSearch(maze, path, curPos)) { //start recursive call on recursiveSearch with current values
          return true; //true if true
        }
      }
      path.pop(); //if by here still not caught, remove from stack
      curPos = Coord.subtract(curPos,dir.getChange()); //backtrack curPos to previous position
    }
    return false; //false if nothing above found
  }
}
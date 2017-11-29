import java.util.*;

// Search a maze for a path from mouse to cheese. The exact search
// method is left to child classes to implement.
public abstract class MazeSearch {
  
  // Constructor to initialize any state required to search mazes
  public MazeSearch() {
    //this is empty because reasons...
  }

  // Return true if the path given leads the mouse in maze to cheese.
  // Return false otherwise (walking through walls, not ending on
  // cheese). Throw an exception if the path would lead the mouse out
  // of bounds in the maze.
  public boolean isPathToCheese(Maze maze, List<Direction> path) {
    Coord mouse = maze.getMouseLocation(); //make coord for initial maze mouse position
    
    int c =0; //counter
    for(Direction dir : path) { //for each direction in path list
      c++; //add 1 to c
      Coord ans = Coord.add(mouse,dir.getChange()); //make ans new coord direction with dir change
      if (! maze.inBounds(ans)) { //if not in bounds
        throw new IndexOutOfBoundsException("error"); //throw exception
      }
      
      if (maze.isCheese(ans)) { //if is cheese
        if (c == path.size()) { //and is the end of the path to cheese
          return true; //return true
        }
      }
      if (maze.isWall(ans)) { //if wall
        return false; //false
      }
      
      mouse = ans; //make ans mouse for next loop thorugh
    }
    return false; //false otherwise
  }

  // Overriden by children: Search for cheese in the given maze. If no
  // path to any cheese exists, return null.
  public abstract List<Direction> searchForCheese(Maze maze);

}
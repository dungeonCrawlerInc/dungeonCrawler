// name
// dimensions
// contents

// Imports
import java.util.*;

// 
public class room{
  
  // size of the map
  private int _rows = 10;
  private int _columns = 10;
  private char _numAdjRooms = 0; // Accesible rooms from current room
  
  // Grid for room
  private char[][] _roomGrid;

  // array of adjecent room addresses
  
}

void createRoom(int rows, int columns){
  for(int i = 0; i < rows; i++){
    for(int j = 0; j < columns; j++)
      _roomGrid[rows][columns] = 'F';
  }
}

// Keep room tree somewhere...

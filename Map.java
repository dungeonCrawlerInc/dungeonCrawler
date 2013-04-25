/* Map class is what map objects will get put into to populate the map
 * Simulates the terrain, walkable space, etc.
 */

// Imports
import java.util.*;
import javax.swing.*;
import java.awt.*;


/* Notes for future:
 *  Keep map tree
 *  Keep array of adjecent map addresses
 */


// 
public class Map extends JPanel{
  
   public static final int _GridSize = 10; // Size of an individual square
   
   private int _rows = 100; // Size of the available map
   private int _columns = 100;
   private char _numAdjMaps = 0; // Accesible rooms from current room
  
   
   private MapChar[][] _mapGrid; // Grid for room
   

   // Room constructor
   public Map(){
     
      this._mapGrid = new MapChar[_rows][_columns]; // Initialize the grid
      
      // Populate the map with all floor objects, can be changed later
      for(int i = 0; i < rows; ++i){
         for(int j = 0; j < columns; ++j)
            _mapGrid[i][j] = Globals.FLOOR;
      }
      
      // Set the dimensions of the overall room
      int mapWidth = columns * _GridSize;
      int mapHeight = rows * _GridSize;
      setPreferredSize(new Dimension(mapWidth, mapHeight));
   }

  // Temporary method, will go into the play area gui for it to call.
  public void setTavern(){
      for(int i = 0; i < _rows; i++)
         _mapGrid[i][0] = _mapGrid[i][_columns - 1] = Globals.WALL;
      for(int i = 0; i < _columns; i++)
         _mapGrid[0][i] = _mapGrid[_rows - 1][i] = Globals.WALL;
   }
   
   @Override
   public void paintComponent(Graphics g){
      
      super.paintComponent(g); // Important to call super class method
      
      g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board
    
      int recW = getWidth() / _rows; // Draw the grid
      int recH = getHeight() / _columns;
      
      for (int i = 0; i < _rows; i++){ // Determine what to draw for each grid square on map
         for (int j = 0; j < _columns; j++){
            
            int xCord = i * recW; // Upper left corner of this terrain rect
            int yCord = j * recH;
            
            
            if(_roomGrid[i][j] == Globals.VOID){ // Paint black grid for void space
              g.setColor(Color.BLACK); // All void space is black
              g.drawRect(xCord, yCord, recW, recH); // Fill in the square with black
            }
            
             // Don't paint anything for floor space. Void space already painted
            if(_roomGrid[i][j] != Globals.FLOOR && _roomGrid[i][j] != Globals.VOID){
               g.setColor(_roomGrid[i][j].getColor()); // Get color of map object
               String s = "" + _roomGrid[i][j].getChar(); // Grab the character from the map object
               g.drawString(s, xCord, yCord); // Paint the character in the appropriate color on the grid square
            }
            
         }
      }
   }

   public static void main(String[] args){
      SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            Globals globals = new Globals();
            JFrame frame = new JFrame("Room");
            Room room = new Room();
            room.setTavern();
            frame.add(room);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}

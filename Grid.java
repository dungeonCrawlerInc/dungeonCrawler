/* Grid class is what grid objects will get put into to populate the grid
 * Simulates the map, building, terrain, walkable space, items, people, etc.
 */

// Imports
import java.util.*;
import javax.swing.*;
import java.awt.*;


/* Notes for future:
 *  Keep grid tree
 *  Keep array of adjecent grid addresses
 */


// 
public class Grid extends JPanel{
  
   public static final int _GridSize = 10; // Size of an individual square
   
   private int _rows = 100; // Size of the available grid
   private int _columns = 100;
   private char _numAdjGrids = 0; // Accesible rooms from current room
  
   
   private GridChar[][] _grid; // Grid for room
   

   // Room constructor
   public Grid(){
     
      this._grid = new GridChar[_rows][_columns]; // Initialize the grid
      
      // Populate the grid with all floor objects, can be changed later
      for(int i = 0; i < rows; ++i){
         for(int j = 0; j < columns; ++j)
            _grid[i][j] = Globals.FLOOR;
      }
      
      // Set the dimensions of the overall grid
      int gridWidth = columns * _GridSize;
      int gridHeight = rows * _GridSize;
      setPreferredSize(new Dimension(gridWidth, gridHeight));
   }

  // Temporary method, will go into the play area gui for it to call.
  public void setTavern(){
      for(int i = 0; i < _rows; i++)
         _grid[i][0] = _grid[i][_columns - 1] = Globals.WALL;
      for(int i = 0; i < _columns; i++)
         _grid[0][i] = _grid[_rows - 1][i] = Globals.WALL;
   }
   
   @Override
   public void paintComponent(Graphics g){
      
      super.paintComponent(g); // Important to call super class method
      
      g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board
    
      int recW = getWidth() / _rows; // Draw the grid
      int recH = getHeight() / _columns;
      
      for (int i = 0; i < _rows; i++){ // Determine what to draw for each grid square on grid
         for (int j = 0; j < _columns; j++){
            
            int xCord = i * recW; // Upper left corner of this terrain rect
            int yCord = j * recH;
            
            
            if(_grid[i][j] == Globals.VOID){ // Paint black grid for void space
              g.setColor(Color.BLACK); // All void space is black
              g.drawRect(xCord, yCord, recW, recH); // Fill in the square with black
            }
            
             // Don't paint anything for floor space. Void space already painted
            if(_grid[i][j] != Globals.FLOOR && _grid[i][j] != Globals.VOID){
               g.setColor(_grid[i][j].getColor()); // Get color of map object
               String s = "" + _grid[i][j].getChar(); // Grab the character from the map object
               g.drawString(s, xCord, yCord); // Paint the character in the appropriate color on the grid square
            }
            
         }
      }
   }

   public static void main(String[] args){
      SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            Globals globals = new Globals();
            JFrame frame = new JFrame("Grid");
            Grid grid = new Grid();
            grid.setTavern();
            frame.add(grid);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}

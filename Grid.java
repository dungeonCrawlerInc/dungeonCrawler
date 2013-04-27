/* Grid class is what grid objects will get put into to populate the grid
 * Simulates the map, building, terrain, walkable space, items, people, etc.
 */

// Imports
import javax.swing.*;
import java.awt.*;

/* Notes for future:
 *  Keep grid tree
 *  Keep array of adjecent grid addresses
 */

// 
public class Grid extends JPanel{
  
   public static final int _GridSize = 20; // Size of an individual square
   
   private int _rows = 35; // Size of the available grid
   private int _columns = 35;
//   private char _numAdjGrids = 0; // Accesible rooms from current room
  
   private GridChar[][] _grid; // Grid for room
   
   // Room constructor
   public Grid(){
     
      this._grid = new GridChar[_rows][_columns]; // Initialize the grid
      
      // Populate the grid with all floor objects, can be changed later
      for(int i = 0; i < _rows; ++i){
         for(int j = 0; j < _columns; ++j)
            _grid[i][j] = Globals.FLOOR;
      }
      
      // Set the dimensions of the overall grid
      int gridWidth = _columns * _GridSize;
      int gridHeight = _rows * _GridSize;
      setPreferredSize(new Dimension(gridWidth, gridHeight));
   }

  // Temporary method, will go into the play area gui for it to call.
  public void setTavern(){
     
     for(int i = 0; i < _rows; i++){ // Populate outer VOID space
        for(int j = 0; j < 10; ++j)
         _grid[i][j] = _grid[i][_columns - 1 - j] = Globals.VOID;
     }
     for(int i = 0; i < _columns; i++){
        for(int j = 0; j < 10; ++j)
         _grid[j][i] = _grid[_rows - 1 - j][i] = Globals.VOID;
     }
   
     for(int i = 10; i < _rows - 10; ++i) // Populate walls
        _grid[i][10] = _grid[i][_rows - 11] = Globals.WALL;
     for(int i = 11; i < _columns - 10; ++i)
        _grid[10][i] = _grid[_columns - 11][i] = Globals.WALL;
  
     /*
     for(int i = 0; i < 3; ++i){
        int x = 20;
        for(int j = 0; j < 2; ++j)
           _grid
     }
     */
     
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
              g.fillRect(xCord, yCord, recW, recH); // Fill in the square with black
            }
            
             // Don't paint anything for floor space. Void space already painted
            if(_grid[i][j] != Globals.FLOOR && _grid[i][j] != Globals.VOID){
               g.setColor(Color.BLACK);
               g.drawRect(xCord, yCord, recW, recH);
               g.setColor(_grid[i][j].getColor()); // Get color of map object
               Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
               g.setFont(font);
               String s = "" + _grid[i][j].getChar(); // Grab the character from the map object
               g.drawString(s, xCord + 4, yCord + 16); // Paint the character in the appropriate color on the grid square
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

/*
 * 
 * 
 * 
 * 
 */

// Imports
import java.util.*;
import javax.swing.*;
import java.awt.*;

// 
public class Room extends JPanel{
  
   // Size of square
   public static final int _GridSize = 10;
   
   // Size of the map
   private int _rows = 100;
   private int _columns = 100;
   private char _numAdjRooms = 0; // Accesible rooms from current room
  
   // Grid for room
   private MapChar[][] _roomGrid;

   // array of adjecent room addresses

   // 
   public Room(int rows, int columns){
      _columns = columns;
      _rows = rows;
      this._roomGrid = new MapChar[rows][columns];
      for(int i = 0; i < rows; i++){
         for(int j = 0; j < columns; j++)
            _roomGrid[i][j] = Globals.FLOOR;
      }
      
      int roomWidth = columns * _GridSize;
      int roomHeight = rows * _GridSize;
      setPreferredSize(new Dimension(roomWidth, roomHeight));
   }

  // Keep room tree somewhere...

   public void setTavern(){
      for(int i = 0; i < _rows; i++)
         _roomGrid[i][0] = _roomGrid[i][_columns - 1] = Globals.WALL;
      for(int i = 0; i < _columns; i++)
         _roomGrid[0][i] = _roomGrid[_rows - 1][i] = Globals.WALL;
   }
   
   @Override
   public void paintComponent(Graphics g){
      // Important to call super class method
      super.paintComponent(g);
      // Clear the board
      g.clearRect(0, 0, getWidth(), getHeight());
    
      // Draw the grid
      int rectWidth = getWidth() / _rows;
      int rectHeight = getHeight() / _columns;
    
      //g.setColor(Color.RED);
      
      for (int i = 0; i < _rows; i++){
         for (int j = 0; j < _columns; j++){
            
            // Upper left corner of this terrain rect
            int x = i * rectWidth;
            int y = j * rectHeight;
            if(_roomGrid[i][j] != Globals.FLOOR){
               g.setColor(_roomGrid[i][j].getColor());
               String s = "" + _roomGrid[i][j].getChar();
               g.drawString(s, x, y);
            }
         }
      }
   }

   public static void main(String[] args){ // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
      SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            Globals globals = new Globals();
            JFrame frame = new JFrame("Room");
            Room room = new Room(50, 50);
            room.setTavern();
            frame.add(room);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}

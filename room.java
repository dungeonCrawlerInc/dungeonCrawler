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
public class room extends JPanel{
  
   // Size of square
   public static final int _GridSize = 10;
   
   // Size of the map
   private int _rows = 100;
   private int _columns = 100;
   private char _numAdjRooms = 0; // Accesible rooms from current room
  
   // Grid for room
   private char[][] _roomGrid;

   // array of adjecent room addresses

   // 
   public room(int rows, int columns){
      _columns = columns;
      _rows = rows;
      this._roomGrid = new char[rows][columns];
      for(int i = 0; i < rows; i++){
         for(int j = 0; j < columns; j++)
            _roomGrid[i][j] = 'F';
      }
      
      int roomWidth = columns * _GridSize;
      int roomHeight = rows * _GridSize;
      setPreferredSize(new Dimension(roomWidth, roomHeight));
   }

  // Keep room tree somewhere...

   @Override
   public void paintComponent(Graphics g){
      // Important to call super class method
      super.paintComponent(g);
      // Clear the board
      g.clearRect(0, 0, getWidth(), getHeight());
    
      // Draw the grid
      int rectWidth = getWidth() / _rows;
      int rectHeight = getHeight() / _columns;
    
      for (int i = 0; i < _rows; i++){
         for (int j = 0; j < _columns; j++){
            // Upper left corner of this terrain rect
            int x = i * rectWidth;
            int y = j * rectHeight;
            String s = "" + _roomGrid[i][j];
            g.drawString(s, x, y);
                         //Color terrainColor = new Color(102,153,0); // Change this....
            //g.setColor(terrainColor);
            //g.fillRect(x, y, rectWidth, rectHeight);
         }
      }
   }

   public static void main(String[] args){ // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
      SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            JFrame frame = new JFrame("Tavern");
            room room = new room(50, 50);
            frame.add(room);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}

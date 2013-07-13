package dungeonGame;

import java.awt.*;

public class GridSquareTypes{
 
   private Color brown = new Color(50, 50 , 50); // Not actually brown, will have to test
   public static GridSquare WALL, DOOR, FLOOR, VOID, TREE, CHARACTER; // Accesible by other classes/files


   public GridSquareTypes(){ 
      CHARACTER = new GridSquare('C', Color.BLACK, false); // Constructor takes an ASCI char and a color.
      WALL = new GridSquare('W', Color.GRAY, false);
      DOOR = new GridSquare('D', brown, true);
      FLOOR = new GridSquare('F', Color.WHITE, true); // Blank walkable space on grid
      VOID = new GridSquare('V', Color.BLACK, false); // Unused surrounding grid space
      TREE = new GridSquare('T', brown, false);
      
      // To add a new GridChar, give it a name and call constructor with desired ASCI char and color
      // Note: Update README file with new GridChar.  Also have to add GridChar name to public static list above
   }


}
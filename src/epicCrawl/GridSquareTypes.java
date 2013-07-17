package epicCrawl;

import java.awt.*;

public class GridSquareTypes{
   public static GridSquare WALL, DOOR, FLOOR, VOID, CHARACTER, TABLE, GIRL, CHEST, LCHAIR, RCHAIR; // Accesible by other classes/files

   public GridSquareTypes(){ 
      CHARACTER = new GridSquare('C', false); // Constructor takes an ASCI char and a color.
      WALL = new GridSquare('W', false);
      DOOR = new GridSquare('D', true);
      FLOOR = new GridSquare('F', true); // Blank walkable space on grid
      VOID = new GridSquare('V',false); // Unused surrounding grid space
      TABLE = new GridSquare('T', false);
      GIRL = new GridSquare('G', false);
      CHEST = new GridSquare('Z', false);
      LCHAIR = new GridSquare('X', false);
      RCHAIR = new GridSquare('Y', false);
      
      // To add a new GridChar, give it a name and call constructor with desired ASCI char and color
      // Note: Update README file with new GridChar.  Also have to add GridChar name to public static list above
   }


}
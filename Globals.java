import java.util.*; // Might be able to get rid of some of these?
import java.awt.*;
import javax.swing.*;

public class Globals{ // Should probably later change to MapChars.... unless other classes get added in here
 
   private Color brown = new Color(50, 50 , 50); // Not actually brown, will have to test
   public static GridChar USER, BARTENDER, STOOL, TABLE, WALL, DOOR, ENEMY, FLOOR, VOID; // Accesible by other classes/files

   public Globals(){ 
      USER = new MapChar('U', Color.BLACK); // Constructor takes an ASCI char and a color.
      BARTENDER = new MapChar('B', Color.BLACK);
      STOOL = new MapChar('S', brown);
      TABLE = new MapChar('T', brown); 
      WALL = new MapChar('W', Color.GRAY);
      DOOR = new MapChar('D', brown);
      ENEMY = new MapChar('E', Color.RED);
      FLOOR = new MapChar('F', Color.WHITE); // Blank walkable space on grid
      VOID = new MapChar('V', Color.BLACK); // Unused surrounding grid space
      
      // To add a new GridChar, give it a name and call constructor with desired ASCI char and color
      // Note: Update README file with new GridChar.  Also have to add GridChar name to public static list above
   }

}

// Might add field for GridChar to be passable?  Meaning can be walked through/on.

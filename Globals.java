import java.util.*; // Might be able to get rid of some of these?
import java.awt.*;
import javax.swing.*;


// Should probably rename file and class to MapCharacters
public class Globals{
 
   private Color brown = new Color(50, 50 , 50); // Not actually brown, will have to test
   public static MapChar USER, BARTENDER, STOOL, TABLE, WALL, DOOR, ENEMY, FLOOR; // Accesible by other classes/files

   public Globals(){ // When adding in a new item/thing to go on map just give it a name and call constructor
      USER = new MapChar('U', Color.BLACK); // Constructor takes an ASCI char and a color.
      BARTENDER = new MapChar('B', Color.BLACK);
      STOOL = new MapChar('S', brown);
      TABLE = new MapChar('T', brown); 
      WALL = new MapChar('W', Color.GRAY);
      DOOR = new MapChar('D', brown);
      ENEMY = new MapChar('E', Color.RED);
      FLOOR = new MapChar('F', Color.WHITE);
   }

}


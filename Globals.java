// Map key struct

import java.util.*;
import java.awt.*;
import javax.swing.*;



public class Globals{
 
   private Color brown = new Color(50, 50 , 50);
   public static MapChar USER, BARTENDER, STOOL, TABLE, WALL, DOOR, ENEMY, FLOOR;

   public Globals(){
      USER = new MapChar('U', Color.BLACK);
      BARTENDER = new MapChar('B', Color.BLACK);
      STOOL = new MapChar('S', brown);
      TABLE = new MapChar('T', brown); 
      WALL = new MapChar('W', Color.GRAY);
      DOOR = new MapChar('D', brown);
      ENEMY = new MapChar('E', Color.RED);
      FLOOR = new MapChar('F', Color.WHITE);
   }

}


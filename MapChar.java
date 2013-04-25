//Imports
import java.util.*;

// MapChar is an object that get placed on the map
public class MapChar{
   
   private java.awt.Color _color; // Color to paint object
   private char _character; // ASCI char to display for object
   
   public MapChar(char character, java.awt.Color color){
      _character = character; // Set the variables based on parameters for constructor
      _color = color;
   }

   // Can change color incase object gets changed to another object or current object's state changes
   public void setColor(java.awt.Color c){
      _color = c;
   }

   // Accesor to color of object
   public java.awt.Color getColor(){
      return _color;
   }
   
   // In game possibility to change what is currently at spot on map
   public void setChar(char character){
      _character = character;
   }
   
   // Can be used to determine what object is at a given location
   public char getChar(){
      return _character;
   }

} // End of file

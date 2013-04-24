
import java.util.*;




public class MapChar{
   
   private java.awt.Color _color;
   private char _character;
   
   public MapChar(char character, java.awt.Color color){
      _character = character;
      _color = color;
   }

   public void setColor(java.awt.Color c){
      _color = c;
   }

   public java.awt.Color getColor(){
      return _color;
   }
   
   public void setChar(char character){
      _character = character;
   }
   
   public char getChar(){
      return _character;
   }

}

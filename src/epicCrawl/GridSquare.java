package epicCrawl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GridSquare{
   private char _object; // ASCI char to display for object
   private boolean _passable, _visible;
   private BufferedImage _gridImageLabel;
   //private DefaultListModel listModel;
   
   public GridSquare(char c, boolean passable){
      _object = c; // Set the variables based on parameters for constructor
      _passable = passable;
      _visible = false;
      _gridImageLabel = createImageLabel(c);
   }

   public BufferedImage getImage(){
	   return _gridImageLabel;
   }
   
   public BufferedImage createImageLabel(char c){
	   BufferedImage bufImage = null;
	   String imageName = "";

	   switch(c){
	   case 'F': imageName = "Images/32x32WoodFloor.png"; // "32x32WoodFloor.png";
	   break;
	   case 'C': imageName = "Images/CHARACTER-Armor.png";
	   break;
	   case 'W': imageName = "Images/32x32StoneWall.png";
	   break;
	   case 'T': imageName = "Images/table.png";
	   break;
	   case 'D': imageName = "Images/Door.png";
	   break;
	   case 'Z': imageName = "Images/Chest.png";
	   break;
	   case 'G': imageName = "Images/GIRL.png";
	   break;
	   case 'X': imageName = "Images/chair right.png";
	   break;
	   case 'Y': imageName = "Images/chair left.png";
	   }
	   
	   InputStream input = this.getClass().getClassLoader().getResourceAsStream(imageName);
	   try{
		   bufImage = ImageIO.read(input);
	   }catch (IOException e){System.err.println("Failed to load image for grid square.");}
		
	   return bufImage;
   }
   
   // Can be used to determine what object is at a given location
   public char getChar(){
      return _object;
   }

   public void setPassable(boolean passable){
	   _passable = passable;
   }

   public boolean isPassable(){
	   return _passable;
   }
   
   public boolean isVisible(){
	   return _visible;
   }
   
   public void setVisible(boolean v){
	   _visible = v;
   }
} // End of file
package epicCrawl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GridSquare{
   private String _name; // ASCI char to display for object
   private boolean _passable, _visible;
   private BufferedImage _gridImageLabel;
   
   public GridSquare(String s, boolean passable){
	   _name = s; // Set the variables based on parameters for constructor
      _passable = passable;
      _visible = false;
      
      if(!s.equals("void"))
    	  _gridImageLabel = createImageLabel(s);
   }

   public BufferedImage getImage(){
	   return _gridImageLabel;
   }
   
   public BufferedImage createImageLabel(String s){
	   BufferedImage bufImage = null;
	   String imageName = "";
	   
	   if(s.equals("woodfloor"))
		   imageName = "Images/32x32WoodFloor.png";
	   else if(s.equals("character"))
		   imageName = "Images/CHARACTER-Armor.png";
	   else if(s.equals("wall"))
		   imageName = "Images/32x32StoneWall.png";
	   else if(s.equals("table"))
		   imageName = "Images/table.png";
	   else if(s.equals("door"))
		   imageName = "Images/Door.png";
	   else if(s.equals("chest"))
		   imageName = "Images/Chest.png";
	   else if(s.equals("girl"))
		   imageName = "Images/GIRL.png";
	   else if(s.equals("rightchair"))
		   imageName = "Images/chair left.png";
	   else if(s.equals("leftchair"))
		   imageName = "Images/chair right.png";
	   else if(s.equals("enemy"))
		   imageName = "Images/Enemy.png";

	   InputStream input = this.getClass().getClassLoader().getResourceAsStream(imageName);
	   try{
		   bufImage = ImageIO.read(input);
	   }catch (IOException e){System.err.println("Failed to load image for grid square.");}
		
	   return bufImage;
   }
   
   // Can be used to determine what object is at a given location
   public String getName(){
      return _name;
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
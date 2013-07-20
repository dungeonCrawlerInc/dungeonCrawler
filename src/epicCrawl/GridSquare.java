package epicCrawl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GridSquare{
   private String _name, _path; // ASCI char to display for object
   private boolean _passable, _visible;
   private BufferedImage _gridImageLabel;
   
   public GridSquare(String name, String path, boolean passable){
	   _name = name; // Set the variables based on parameters for constructor
	   _path = path;
      _passable = passable;
      _visible = false;
      
      _gridImageLabel = createImageLabel();
   }

   public BufferedImage getImage(){
	   return _gridImageLabel;
   }
   
   public BufferedImage createImageLabel(){
	   BufferedImage bufImage = null;

	   InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + _path);
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
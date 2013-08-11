package epicCrawl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

// Base class for anything added into the game.
public class GridObject{
   private String _name, _path;
   private boolean _passable, _visible;
   private BufferedImage _gridImageLabel;
   private int _xLoc, _yLoc;
   
   public GridObject(String name, boolean passable){
	   _name = name; // Set the variables based on parameters for constructor
	   _path = "Images/" + name + ".png";
      _passable = passable;
      _visible = false;
      _gridImageLabel = createImageLabel();
   }

   public BufferedImage getImage(){
	   return _gridImageLabel;
   }
   
   public BufferedImage createImageLabel(){
	   BufferedImage bufImage = null;

	   InputStream input = this.getClass().getClassLoader().getResourceAsStream(_path);
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
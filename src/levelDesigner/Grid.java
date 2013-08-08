package levelDesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Grid extends JPanel implements ActionListener{
   int myI, myJ;
   private String[] imageNames;
   ArrayList<BufferedImage> gridImages;
   ArrayList<String> gridImageNames;
   
   public Grid(int i, int j, int size, String[] strs){
	  imageNames = strs;
	  
	  gridImages = new ArrayList<BufferedImage>();
	  gridImageNames = new ArrayList<String>();
	  
      myI = i;
      myJ = j;
      setBackground(Color.RED);
      
      this.setBorder(BorderFactory.createLineBorder(Color.black));
      this.setVisible(true);
   }
      
   @Override
   public void actionPerformed(ActionEvent ae){
	   if(LevelDesigner.val >= 0 && LevelDesigner.val <= 7){ // Floor space or wall
		   gridImages.clear();
		   gridImageNames.clear();
	   }
	   else if(gridImages.size() == 0){ // Not a floor space and there isn't a floor space
		   return;
	   }
	   else if(gridImages.size() > 1){ // Floor space and something else
		   gridImages.remove(1);
		   gridImageNames.remove(1);
	   }
	   
	   if(gridImageNames.size() > 0 && gridImageNames.get(0) == "Void.png") // Don't put things on top of void spaces
		   return;
	   
	   String imageName = imageNames[LevelDesigner.val];

	   InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + imageName);
	   try{
		   gridImages.add(ImageIO.read(input));
		   gridImageNames.add(imageName);
	   }
	   catch(Exception e){System.err.println("Failed to load image");}
	   
	   repaint();
   }
   
   public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
		g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board
		
		for(BufferedImage curImage: gridImages)
			g.drawImage(curImage, 0, 0, getWidth(), getHeight(), null);
	}
}
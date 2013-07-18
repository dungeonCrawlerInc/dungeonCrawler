package levelDesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import epicCrawl.GridSquareTypes;

@SuppressWarnings("serial")
public class Grid extends JPanel implements ActionListener{
   LevelDesigner grid;
   int myI, myJ;
   private Color[] colors = {Color.blue, Color.black, Color.red, Color.yellow,Color.green};
   private String[] imageNames = {"dirt.png", "grass.png", "Door.png", "wood floor.png", "32x32WoodFloor.png"};
   BufferedImage gridImage;
   private String imagePath;
   
   public Grid(int i, int j, int size, LevelDesigner m){
	  imagePath = "";
      setSize(size, size);
      grid = m;
      myI = i;
      myJ = j;
      setBackground(Color.RED);
      
      this.setBorder(BorderFactory.createLineBorder(Color.black));
      this.setVisible(true);
   }
      
   @Override
   public void actionPerformed(ActionEvent ae){
	   System.out.println("grid square clicked");
	   
	   grid.notify(myI, myJ);
	   this.setBackground(colors[LevelDesigner.val]);
	   
	   imagePath = "Images/" + imageNames[LevelDesigner.val];
	   
	   gridImage = null;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(imagePath);
		try{
			gridImage = ImageIO.read(input);
		}catch(Exception e){
			System.err.println("Failed to load image");
		}
   }
   
   public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
		g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board

		g.drawImage(gridImage, 0, 0, getWidth(), getHeight(), null);
	}
}
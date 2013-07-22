package levelDesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Grid extends JPanel implements ActionListener{
   int myI, myJ;
   private String[] imageNames;
   BufferedImage gridImage;
   String imageName;
   
   public Grid(int i, int j, int size, String[] strs){
	  imageNames = strs;
	  imageName = "null";
      myI = i;
      myJ = j;
      setBackground(Color.RED);
      
      this.setBorder(BorderFactory.createLineBorder(Color.black));
      this.setVisible(true);
   }
      
   @Override
   public void actionPerformed(ActionEvent ae){
	   imageName = imageNames[LevelDesigner.val];
	   
	   gridImage = null;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + imageName);
		try{
			gridImage = ImageIO.read(input);
		}catch(Exception e){System.err.println("Failed to load image");}
		
		repaint();
   }
   
   public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
		g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board
		g.drawImage(gridImage, 0, 0, getWidth(), getHeight(), null);
	}
}
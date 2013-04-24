import java.awt.*;
import javax.swing.*;

//First thing called at every execution
public class startGame {
  public static void main(String[] args) {
	dungeonCrawler mainMenu = new dungeonCrawler();
	
    //Create the game window
    WindowUtilities.setNativeLookAndFeel();
    JFrame f = new JFrame("Dungeon Crawler");
    f.setSize(1000, 800);
    Container content = f.getContentPane();
    content.setBackground(Color.white);
    content.setLayout(new FlowLayout()); 
    content.add(new JButton("New Game"));
    content.add(new JButton("Load Character"));
    content.add(new JButton("About"));
    f.addWindowListener(new ExitListener());
    f.setVisible(true);
  }
}

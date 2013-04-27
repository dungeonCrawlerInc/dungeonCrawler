// Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class GUI{
   
   public GUI(){
      JFrame guiFrame = new JFrame(); // Create frame for game
      guiFrame.setLayout(new BorderLayout());
      guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      guiFrame.setTitle("+++ Dungeon Crawler +++"); // Game title
      guiFrame.setSize(700,700); // Size of play window on start, later support changing screen size
      guiFrame.setLocationRelativeTo(null); // Center on screen

    JPanel buttonPanel = new JPanel(); // Will hold the three buttons
    buttonPanel.setLayout(new FlowLayout()); // Get rid of layout manager
      
      // Button to start the game when clicked, later add in conditions where either not visible/grayed out/pop up until new or load is selected
      JButton newCharacter = new JButton("New Character");
      JButton loadCharacter = new JButton("Load Character");
      JButton aboutInfo = new JButton("About Info");
      
      //Handle creating new character, later need to add in name input, file generation, etc.
      newCharacter.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent event){
            // Wipe the board...
            // Create the grid, txt area, items area, populate tavern, etc.
            
         }
      });

      //Handle loading existing character, need to add in finding and loading the file and then starting the game
      loadCharacter.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent event){
            // Wipe the board..., load old character
            // Create the grid, txt area, items area, populate tavern, etc.
            
         }
      });
      
      //Handle showing blurb about how,why,when,where about the project
      aboutInfo.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent event){
            // Display pop up info on game...
         }
      });
      
      // Add to border layout manager and add to frame
      buttonPanel.add(newCharacter);
      buttonPanel.add(loadCharacter);
      buttonPanel.add(aboutInfo);

    JLabel dungeonCrawlerLabel = new JLabel("+++ Dungeon Crawler +++");
    dungeonCrawlerLabel.setFont(new Font("Serif", Font.BOLD, 48));
    dungeonCrawlerLabel.setHorizontalAlignment(JLabel.CENTER);

    guiFrame.add(dungeonCrawlerLabel, BorderLayout.NORTH);
    
      /* // Needs some work
      ImagePanel topPanel = new ImagePanel("C:/Users/Localadmin/Desktop/Dungeon Crawler Code/Dr. Java Stuff/dragon.png"); // Panel for top of screen
      guiFrame.add(topPanel, BorderLayout.NORTH); // Add image to top panel
      */
      
      guiFrame.add(buttonPanel, BorderLayout.CENTER);
      guiFrame.setVisible(true); // Make it appear
   }
   
   public static void main(String[] args){ new GUI();} // Call creation of game
}

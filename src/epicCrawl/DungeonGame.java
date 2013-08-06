package epicCrawl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DungeonGame{
	private JFrame gameFrame;
	private JPanel mainScreenPanel;
	private JButton startGame, gameInfo, mainMenu;
	private GamePanel gamePanel;
	private ImageIcon newAndLoadGameIcon;

	public DungeonGame(){
		setUpGameFrame();
		setUpMainScreen();
		setUpGamePanel();
		setUpButtonActionListeners();

		gameFrame.add(mainScreenPanel);
		mainScreenPanel.setVisible(true);
		gameFrame.setVisible(true);

		gameFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		gameFrame.setLocationRelativeTo(null);
		
		gameFrame.pack();
		gameFrame.repaint();
	}

	private void setUpGameFrame(){
		gameFrame = new JFrame(); // Create frame for game
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		gameFrame.setTitle("Epic Crawl - Main Menu"); // Game title
	}

	private void setUpMainScreen(){
		BufferedImage tmpImage = null;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/64x64EpicCrawler.png");
		try{
			tmpImage = ImageIO.read(input);
		}catch(IOException e){
			System.err.println("Failed to load main screen image");
		}
		newAndLoadGameIcon = new ImageIcon(tmpImage);
		
		mainScreenPanel = new JPanel();
		mainScreenPanel.setLayout(new BorderLayout());
		Dimension dim = new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
								(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 60);
		mainScreenPanel.setPreferredSize(dim);

        JPanel mainFrameOptions = new JPanel(); // Will hold the three buttons
		mainFrameOptions.setLayout(new FlowLayout()); // Get rid of layout manager
		mainFrameOptions.setBackground(Color.BLACK);

		// Button to start the game when clicked, later add in conditions where either not visible/grayed out/pop up until new or load is selected
		startGame = new JButton("New Game");
	//	loadGame = new JButton("Load Game");
		gameInfo = new JButton("Game Info");

		// Add to border layout manager and add to frame
		mainFrameOptions.add(startGame);
	//	mainFrameOptions.add(loadGame);
		mainFrameOptions.add(gameInfo);

        JLabel gameLabel = new JLabel("Epic Crawl");
		gameLabel.setFont(new Font("Serif", Font.BOLD, 48));
		gameLabel.setHorizontalAlignment(JLabel.CENTER);
		gameLabel.setBackground(Color.BLACK);
		gameLabel.setForeground(Color.RED);
		mainScreenPanel.add(gameLabel, BorderLayout.NORTH);
		mainScreenPanel.setBackground(Color.BLACK);

        BufferedImage mainScreenImage = null;
		
		input = this.getClass().getClassLoader().getResourceAsStream("Images/EpicCrawlTitle.png");
		try {
			mainScreenImage = ImageIO.read(input);
		} catch (IOException e) {
			System.err.println("Failed to load main screen image");
		}

        JPanel mainScreenImageAndOptionsPanel = new JPanel();
		mainScreenImageAndOptionsPanel.setBackground(Color.BLACK);
		mainScreenImageAndOptionsPanel.setLayout(new BorderLayout());
		mainScreenImageAndOptionsPanel.add(mainFrameOptions, BorderLayout.NORTH);
		
		JLabel mainScreenImageLabel = new JLabel(new ImageIcon(mainScreenImage));
		mainScreenImageAndOptionsPanel.add(mainScreenImageLabel, BorderLayout.CENTER);
		mainScreenPanel.add(mainScreenImageAndOptionsPanel);
		mainScreenPanel.setVisible(true); // Make it appear
	}
	
	private void setUpGamePanel(){
		mainMenu = new JButton("Main Menu"); // Button to return to main menu when in games
		mainMenu.setBackground(Color.RED);
		gamePanel = new GamePanel(mainMenu);
		gamePanel.setBackground(Color.GREEN);
	}

	private void setUpButtonActionListeners(){
		//Handle creating new character, later need to add in name input, file generation, etc.
		startGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				String newCharacterName = (String)JOptionPane.showInputDialog(gameFrame, "Enter character name.", "New Character", JOptionPane.INFORMATION_MESSAGE, newAndLoadGameIcon, null, null); // ("Enter character name.");
				if(newCharacterName == null){
				    backToMainScreen();
				}
				else{
					// check if name already exists...
					// ask if want to overwrite...
					/*
					try{
					    BufferedWriter bufWriter = new BufferedWriter(new FileWriter("CharacterNames.txt", true));
					    bufWriter.write("\n" + newCharacterName); // System.out.println(newCharacterName);
					    bufWriter.newLine();
					    bufWriter.flush();
					    bufWriter.close();
					}catch(IOException e){
					    System.out.println("Error saving new character name to CharacterNames.txt");
					}
					*/

					gameFrame.setTitle("Epic Crawl - " + newCharacterName);
					startGame();
				}
			}
		});

        /* // For now saving/loading games isn't supported
		loadGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				// Wipe the board..., load old character
				// Create the grid, txt area, items area, populate tavern, etc.
				
				// Read in character names from file and save into string array
				ArrayList<String> savedCharactersList = new ArrayList<String>();
				
				try{
					BufferedReader bufReader = new BufferedReader(new FileReader("CharacterNames.txt"));
					String curLine;
				
					while((curLine = bufReader.readLine()) != null){
						if(!curLine.equals(""))
							savedCharactersList.add(curLine);
					}
				
					bufReader.close();
				}
				catch(Exception e){
					System.err.println("Can't open file to load character names, " + e);
				}
				
				String[]savedCharacters = new String[savedCharactersList.size()];
				for(int i = 0; i < savedCharactersList.size(); ++i){
					savedCharacters[i] = savedCharactersList.get(i);
					System.out.println(savedCharacters[i]);
				}
				
				String input = (String)JOptionPane.showInputDialog(gameFrame,
						"Select your character", "Load Character", JOptionPane.INFORMATION_MESSAGE, newAndLoadGameIcon, savedCharacters, null);
				
				// do something with input
				if(input == null)
					gameFrame.setTitle("Epic Crawl - Main Menu");
				else
					gameFrame.setTitle("Epic Crawl - " + input);
				
				gameFrame.repaint();
			}
		});
		*/

		gameInfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				InputStream inStream = getClass().getClassLoader().getResourceAsStream("Files/GameInfo.txt");
				Scanner inScanner = new Scanner(inStream);
				StringBuilder strBuf = new StringBuilder("");
				
				while(inScanner.hasNextLine()){
					strBuf.append(inScanner.nextLine());
					if(inScanner.hasNextLine())
						strBuf.append("\n");
				}
				
				inScanner.close();
				
				JOptionPane.showMessageDialog(gameFrame, strBuf.toString(), "About Info for Epic Crawl",
				    JOptionPane.INFORMATION_MESSAGE, newAndLoadGameIcon);

				gameFrame.repaint();
			}
		});

		mainMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				backToMainScreen();
				
				gameFrame.setTitle("Epic Crawl - Main Menu");
				gameFrame.repaint();
			}
		});
	}

	private void backToMainScreen(){
		gamePanel.setVisible(false);
		gameFrame.remove(gamePanel);

		mainScreenPanel.setVisible(true);
		gameFrame.add(mainScreenPanel);
	}
	
	private void startGame(){
		mainScreenPanel.setVisible(false);
		gameFrame.remove(mainScreenPanel);
		gameFrame.add(gamePanel);
		gamePanel.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(java.lang.String[] args){
		DungeonGame newGame = new DungeonGame();
	}
} // End of file
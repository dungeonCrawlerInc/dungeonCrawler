package levelDesigner;

/*
 * Add in different combo boxes based on object
 * 		terrain
 * 		object
 * 		living thing
 * 
 * Add in option to change rows/columns
 * 
 * Add in ability to place objects on top of others
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class LevelDesigner extends JPanel implements ButtonListener{
	private final int SIZE = 12;
	private Grid [][] theView;
	private ButtonPanel bp;
	public static int val;
	private int rows, cols;
	private JPanel gridPanel;
	private JFrame frame;
	private ImageIcon optionPaneIcon;
	String[] test = {"dirt.png", "grass.png", "Door.png", "woodfloor.png", "32x32WoodFloor.png", 
			"32x32StoneWall.png", "chairleft.png", "chairright.png", "CHARACTER-NoArmor.png", 
			"CHARACTER-Weapon.png", "CHARACTER-Armor.png", "Chest.png", "Enemy.png", "GIRL.png", 
			"TallTablewithfood.png", "table.png", "Portal.png", "Void.png", "++Save Level++"};

	public LevelDesigner(int r, int c){
		frame = new JFrame();
		setVisible(true);
		setLayout(new BorderLayout());

		BufferedImage tmpImage = null;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/64x64EpicCrawler.png");
		try{
			tmpImage = ImageIO.read(input);
		}catch(IOException e){
			System.err.println("Failed to load main screen image");
		}
		optionPaneIcon = new ImageIcon(tmpImage);

		rows = r;
		cols = c;
		theView = new Grid[r][c];   
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(cols);
		gridLayout.setRows(rows);

		gridPanel = new JPanel();
		gridPanel.setVisible(true);
		gridPanel.setBackground(Color.BLACK);
		gridPanel.setPreferredSize(getMaximumSize());
		gridPanel.setLayout(gridLayout);

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				theView[i][j] = new Grid(i, j, SIZE, test);
				gridPanel.add(theView[i][j]);
			}
		}

		bp =  new ButtonPanel(test, this);

		this.add(bp, BorderLayout.SOUTH);
		this.add(gridPanel, BorderLayout.CENTER);

		frame.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me) {

				for(int i = 0; i < rows; ++i){
					for(int j = 0; j < cols; ++j){
						Point p = SwingUtilities.convertPoint(frame, me.getPoint(), theView[i][j]);

						if (theView[i][j].contains(p)){
							theView[i][j].actionPerformed(null);
							return;
						}
					}
				}
			} 
		});

		frame.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent me) {

				for(int i = 0; i < rows; ++i){
					for(int j = 0; j < cols; ++j){
						Point p = SwingUtilities.convertPoint(frame, me.getPoint(), theView[i][j]);

						if (theView[i][j].contains(p)){
							theView[i][j].actionPerformed(null);
							return;
						}
					}
				}
			} 
		});

		Dimension dim = new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
				(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 30);
		frame.setPreferredSize(dim);
		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setTitle("Epic Crawl - Main Menu");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		frame.repaint();
		frame.add(this);
	}

	public String toString(){
		String s= "";
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				if(theView[r][c].imageName.equals("null"))
					s = s + "Void.png ";
				else
					s = s + theView[r][c].imageName + " ";
			}

			s = s + "\n";
		}

		return s;
	}

	public void saveGame(){
		String levelName = (String)JOptionPane.showInputDialog(this, 
				"Enter level name.", "Save level", JOptionPane.INFORMATION_MESSAGE, optionPaneIcon, null, null);

		levelName += ".txt";

		PrintWriter p = null;
		try{
			p = new PrintWriter(new File(levelName));
		} catch (FileNotFoundException e1){}
		p.println( this );
		p.close();   

		try{
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter("LevelsList.txt", true));
			bufWriter.write("\n" + levelName); // System.out.println(newCharacterName);
			bufWriter.newLine();
			bufWriter.flush();
			bufWriter.close();
		}catch(IOException e){
			System.out.println("Error saving level to LevelsList.txt");
		}
	}

	public void loadGame(){
		ArrayList<String> savedLevelsList = new ArrayList<String>();
		try{
			BufferedReader bufReader = new BufferedReader(new FileReader("LevelsList.txt"));
			String curLine = "";

			while((curLine = bufReader.readLine()) != null){
				if(!curLine.equals(""))
					savedLevelsList.add(curLine);
			}

			bufReader.close();
		}
		catch(Exception e){
			System.err.println("Can't open file to load levels, " + e);
		}

		String[]savedLevels = new String[savedLevelsList.size()];
		for(int i = 0; i < savedLevelsList.size(); ++i){
			savedLevels[i] = savedLevelsList.get(i);
		}

		String levelFileName = (String)JOptionPane.showInputDialog(this,
				"Select level to load", "Load Level", JOptionPane.INFORMATION_MESSAGE, optionPaneIcon, savedLevels, null);

		System.out.println(levelFileName);

		FileReader fileReader = null;
		try{
			fileReader = new FileReader(levelFileName);
		} catch (FileNotFoundException e1){}

		Scanner lineScanner = new Scanner(fileReader);
		Scanner wordScanner = null;
		int curRow = 0, curCol = 0;
		String cur = "";

		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		while(lineScanner.hasNextLine()){
			wordScanner = new Scanner(lineScanner.nextLine());

			while(wordScanner.hasNext()){
				cur = wordScanner.next();

				InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + cur);
				try{
					theView[curRow][curCol].gridImage = ImageIO.read(input);
					theView[curRow][curCol].imageName = cur;
				}catch(Exception e){System.err.println("Failed to load image");}

				++curCol;
			}
			
			curCol = 0;
			++curRow;
		}

		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		lineScanner.close();
		wordScanner.close();
		repaint();
	}

	public void buttonPressed(int id){
		System.out.println("buttonPressed");

		if(id == -1) // Save
			saveGame();
		else if(id == -2) // Load
			loadGame();
		else
			val = id;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]){ 
		LevelDesigner levelDesigner = new LevelDesigner(50, 50);
	}
}
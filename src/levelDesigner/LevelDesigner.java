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
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LevelDesigner extends JPanel implements ButtonListener{
	private final int SIZE = 12;
	private int [][] thePit;    
	private Grid [][] theView;
	private ButtonPanel bp;
	public static int val;
	private int rows, cols;
	private JPanel gridPanel;
	private JFrame frame;
	String[] test = {"dirt.png", "grass.png", "Door.png", "wood floor.png", "32x32WoodFloor.png", 
			"32x32StoneWall.png", "chair left.png", "chair right.png", "CHARACTER - No Armor.png", 
			"CHARACTER - Weapon.png", "CHARACTER-Armor.png", "Chest.png", "Enemy.png", "GIRL.png", 
			"TallTable with food.png", "table.png", "Void.png", "++Save Level++"};

	public LevelDesigner(int r, int c){
		frame = new JFrame();
		setVisible(true);
		setLayout(new BorderLayout());

		rows = r;
		cols = c;

		thePit = new int[r][c];
		theView = new Grid[r][c];   

		gridPanel = new JPanel();
		gridPanel.setVisible(true);
		gridPanel.setBackground(Color.BLACK);
		gridPanel.setPreferredSize(getMaximumSize());

		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(cols);
		gridLayout.setRows(rows);

		gridPanel.setLayout(gridLayout);
		
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				theView[i][j] = new Grid(i, j, SIZE, this, test);
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

		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		frame.setTitle("Epic Crawl - Main Menu");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.repaint();
		frame.add(this);
	}

	public String toString(){
		int noRows = thePit.length;
		int noColumns = thePit[0].length;

		String s="";
		for (int r=0;r<noRows;r++){
			for (int c=0;c<noColumns;c++){
				s=s + thePit[r][c] + " ";
			}
			s=s+"\n";
		}
		return(s);
	}

	public void notify( int i, int j){
		thePit[i][j] = val;
	}

	public void saveGame(){
		final JFileChooser fc = new JFileChooser();

		fc.setCurrentDirectory(new java.io.File("."));
		int returnVal = fc.showSaveDialog( null);

		if( returnVal == JFileChooser.APPROVE_OPTION ){
			try{
				PrintWriter p = new PrintWriter( 
						new File( fc.getSelectedFile().getName() ) );
				System.out.println(" printing");

				p.println( this );
				p.close();   
			}
			catch( Exception e){System.out.println("ERROR: file not saved");}
		}
	}

	public void buttonPressed(int id){
		if(id == test.length - 1) // Save
			saveGame();
		else
			val = id;
	}
	
	public static void main(String arg[]){ 
		LevelDesigner levelDesigner = new LevelDesigner(50, 50);
	}
}
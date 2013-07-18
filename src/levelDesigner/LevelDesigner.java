package levelDesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	public LevelDesigner(int r, int c){
		frame = new JFrame();
		int h = 10, w = 10;
		setVisible(true);
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);

		rows = r;
		cols = c;

		thePit = new int[ r][ c];
		theView = new Grid[r][c];   

		gridPanel = new JPanel();
		gridPanel.setVisible(true);
		gridPanel.setBackground(Color.BLACK);

		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(cols);
		gridLayout.setRows(rows);

		gridPanel.setLayout(gridLayout);

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				theView[i][j] = new Grid(i, j, SIZE, this);
				gridPanel.add(theView[i][j]);
			}
		}

		String test [] = {"0 (blue)", "1 (black)","2 (red)","3 (yellow)","4 (green)","save"};
		bp =  new ButtonPanel(test,  this);

		this.add(bp, BorderLayout.NORTH);
		this.add(gridPanel, BorderLayout.CENTER);

		frame.addMouseListener(new MouseAdapter() { // Work on indexing into 2d array -----------------
			public void mousePressed(MouseEvent me) {
				int colIndex = (me.getX() - gridPanel.getX()) / (gridPanel.getWidth() / rows);
				int rowIndex = (me.getY() - gridPanel.getY()) / (gridPanel.getHeight() / cols);
				
				theView[rowIndex][colIndex].actionPerformed(null);
			} 
		});

		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Epic Crawl - Main Menu");
		frame.pack();
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

	public void print(){
		final JFileChooser fc = new JFileChooser();

		fc.setCurrentDirectory(new java.io.File("."));
		int returnVal = fc.showSaveDialog( null);

		if( returnVal == JFileChooser.APPROVE_OPTION ) 
		{
			try{
				PrintWriter p = new PrintWriter( 
						new File( fc.getSelectedFile().getName() ) );
				System.out.println(" printing");

				p.println( this );
				p.close();   
			}
			catch( Exception e)
			{
				System.out.println("ERROR: file not saved");
			}
		}
	}

	public void buttonPressed(String buttonLabel, int id){
		System.out.println("in here");
		
		if(id == 5)
			print();
		else
			val = id;
	}

	public void buttonReleased( String buttonLabel, int buttonId ){}
	public void buttonClicked( String buttonLabel, int buttonId ){}

	public static void main(String arg[]){ 
		LevelDesigner levelDesigner = new LevelDesigner(50, 50);
	}
}
package levelDesigner;

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

	public LevelDesigner(int r, int c){
		frame = new JFrame();
		setVisible(true);
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);

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
				theView[i][j] = new Grid(i, j, SIZE, this);
				gridPanel.add(theView[i][j]);
			}
		}

		String test [] = {"0", "1","2","3","4","save"};
		bp =  new ButtonPanel(test,  this);

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
		if(id == 5) // Save
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
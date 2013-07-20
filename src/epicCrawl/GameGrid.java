package epicCrawl;

//Imports
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

//maybe instead of setting town or house could create them at start up and store them into list of levels..

//change later so when clicked the focus is on this panel

//
public class GameGrid extends JPanel{
	private static final long serialVersionUID = 6942355989519710021L;
	private boolean _viewMode = false;
	
	private ArrayList<GridSquare> gridSquares;

	private int _maxRows = 50, _maxColumns = 50; // Size of the available grid
	private int _rows = 15, _columns = 15;
	
	private Action leftAction, rightAction, upAction, downAction;
	
	//   private char _numAdjGrids = 0; // Accesible rooms from current room
	private GridSquare[][] _grid; // Grid for current map
	
	private int playerX, playerY, newX, newY; // --------------------------------------------------
	private GridSquare gridSquareUnderPlayer;

	// Room constructor
	public GameGrid(){
		setFocusable(true); // Not needed?
		
		setUpKeyBindings();

		_grid = new GridSquare[_maxRows][_maxColumns];
		
		loadLevel("Levels/newLevel.txt");
	}
	
	public void loadLevel(String levelFileName){
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(levelFileName);
		Scanner lineScanner = new Scanner(inStream);
		Scanner wordScanner = null;
		
		int curRow = 0, curCol = 0;
		String cur = "";
		boolean headerInfo = true;

		while(lineScanner.hasNextLine()){
			wordScanner = new Scanner(lineScanner.nextLine());

			while(wordScanner.hasNext()){
				cur = wordScanner.next();
				
				if(cur.equals("dirt.png"))
					_grid[curCol][curRow] = GridSquareTypes.DIRTFLOOR;
				else if(cur.equals("grass.png"))
					_grid[curCol][curRow] = GridSquareTypes.GRASSFLOOR;
				else if(cur.equals("Door.png"))
					_grid[curCol][curRow] = GridSquareTypes.DOOR;
				else if(cur.equals("woodfloor.png"))
					_grid[curCol][curRow] = GridSquareTypes.WOODFLOORLIGHT;
				else if(cur.equals("32x32WoodFloor.png"))
					_grid[curCol][curRow] = GridSquareTypes.WOODFLOORDARK;
				else if(cur.equals("32x32StoneWall.png"))
					_grid[curCol][curRow] = GridSquareTypes.STONEWALL;
				else if(cur.equals("chairleft.png"))
					_grid[curCol][curRow] = GridSquareTypes.LCHAIR;
				else if(cur.equals("chairright.png"))
					_grid[curCol][curRow] = GridSquareTypes.RCHAIR;
				else if(cur.equals("Chest.png"))
					_grid[curCol][curRow] = GridSquareTypes.CHEST;
				else if(cur.equals("Enemy.png"))
					_grid[curCol][curRow] = GridSquareTypes.ENEMY;
				else if(cur.equals("GIRL.png"))
					_grid[curCol][curRow] = GridSquareTypes.GIRL;
				else if(cur.equals("TallTablewithfood.png"))
					_grid[curCol][curRow] = GridSquareTypes.TABLEFOOD;
				else if(cur.equals("Void.png"))
					_grid[curCol][curRow] = GridSquareTypes.VOID;
				else if(cur.equals("table.png"))
					_grid[curCol][curRow] = GridSquareTypes.TABLE;

				++curCol;

			}
			
			curCol = 0;
			++curRow;
		}
		
		
		playerX = 3;
		playerY = 24;
		gridSquareUnderPlayer = _grid[playerX][playerY];
		_grid[playerX][playerY] = GridSquareTypes.CHARACTER;
		
		lineScanner.close();
		wordScanner.close();
	}
	
	private void movePlayer(String direction){
		
		if(direction.equals("left")){
			newX = playerX - 1;
			newY = playerY;
		}
		else if(direction.equals("up")){
			newX = playerX;
			newY = playerY - 1;
		}
		else if(direction.equals("right")){
			newX = playerX + 1;
			newY = playerY;
		}
		else if(direction.equals("down")){
			newX = playerX;
			newY = playerY + 1;
		}
		else
			System.out.println("wtf");
		
		// check if move is out of bounds
		if(newX < 0 || newX > _maxRows -1 || newY < 0 || newY > _maxColumns - 1){
			System.out.println("Attempting to move out of bounds");
			return;
		}
		
		GridSquare nextSquare = _grid[newX][newY]; // grab square type 
		
		if(!nextSquare.isPassable()){ // check if move is passable
			System.out.println("Attempting to move through solid object");
			return;
		}
		
		_grid[playerX][playerY] = gridSquareUnderPlayer; // restore last grid square
		gridSquareUnderPlayer = _grid[newX][newY]; // save grid square type
		_grid[newX][newY] = GridSquareTypes.CHARACTER; 
		
		playerX = newX;
		playerY = newY;
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
		g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board

		if(!_viewMode){
			int recW = getWidth() / _rows; // Draw the grid
			int recH = getHeight() / _columns;

			for (int i = 0; i <  _rows; i++){ // Determine what to draw for each grid square on grid
				int viewX = (playerX - (7) + i);
				int xCord = i * recW; // Upper left corner of this terrain rect

				for (int j = 0; j < _columns; j++){
					int yCord = j * recH;
					int viewY = (playerY - (7) + j);

					if(playerX < 8)
						viewX = i;
					if(playerY < 8)
						viewY = j;
					if(playerX > _maxColumns - 8)
						viewX = _maxColumns - _columns + i;
					if(playerY > _maxRows - 8)
						viewY = _maxRows - _rows + j;

					if(_grid[viewX][viewY] == GridSquareTypes.VOID){ // Paint black grid for void space
						g.setColor(Color.BLACK); // All void space is black
						g.fillRect(xCord, yCord, recW, recH); // Fill in the square with black
					}

					if(_grid[viewX][viewY] != GridSquareTypes.VOID){
							g.drawImage(GridSquareTypes.WOODFLOORDARK.getImage(), xCord, yCord, recW, recH, null);
							
							if(_grid[viewX][viewY] == GridSquareTypes.CHARACTER)
								g.drawImage(gridSquareUnderPlayer.getImage(), xCord, yCord, recW, recH, null);
							
							if(_grid[i][j] != GridSquareTypes.WOODFLOORDARK)
								g.drawImage(_grid[viewX][viewY].getImage(), xCord, yCord, recW, recH, null);
					}
				}
			}
		}
		else{
			int recW = getWidth() / _maxRows; // Draw the grid
			int recH = getHeight() / _maxColumns;
			
			for (int i = 0; i <  _maxRows; i++){ // Determine what to draw for each grid square on grid
				int xCord = i * recW; // Upper left corner of this terrain rect
				
				for (int j = 0; j < _maxColumns; j++){
					int yCord = j * recH;
					
					if(_grid[i][j] == GridSquareTypes.VOID){ // Paint black grid for void space
						g.setColor(Color.BLACK); // All void space is black
						g.fillRect(xCord, yCord, recW, recH); // Fill in the square with black
					}
					
					if(_grid[i][j] != GridSquareTypes.VOID){
						g.drawImage(GridSquareTypes.WOODFLOORDARK.getImage(), xCord, yCord, recW, recH, null);
						
						if(_grid[i][j] == GridSquareTypes.CHARACTER)
							g.drawImage(gridSquareUnderPlayer.getImage(), xCord, yCord, recW, recH, null);
						
						if(_grid[i][j] != GridSquareTypes.WOODFLOORDARK)
							g.drawImage(_grid[i][j].getImage(), xCord, yCord, recW, recH, null);
					}
				}
			}
		}
	}

	private void setUpKeyBindings(){
		leftAction = new LeftAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "doLeftAction");  
		getActionMap().put( "doLeftAction", leftAction );
		
		upAction = new UpAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "doUpAction");  
		getActionMap().put( "doUpAction", upAction );
		
		rightAction = new RightAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "doRightAction");  
		getActionMap().put( "doRightAction", rightAction );
		
		downAction = new DownAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "doDownAction");  
		getActionMap().put( "doDownAction", downAction );
	}
	
	class LeftAction extends AbstractAction{
		public void actionPerformed( ActionEvent ae ){movePlayer("left");}
	}
	
	class UpAction extends AbstractAction{
		public void actionPerformed( ActionEvent ae ){movePlayer("up");}
	}
	
	class RightAction extends AbstractAction{
		public void actionPerformed( ActionEvent ae ){movePlayer("right");}
	}
	
	class DownAction extends AbstractAction{
		public void actionPerformed( ActionEvent ae ){movePlayer("down");}
	}

	public void setViewMode(boolean x){_viewMode = x;}
	
	public boolean getViewMode(){return _viewMode;}
	
	public static void main(java.lang.String[] args){
		JFrame frame = new JFrame();
		GridSquareTypes GridSquareTypes = new GridSquareTypes();
		
		final GameGrid grid = new GameGrid();
		grid.setVisible(true);
		grid.repaint();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setVisible(true);
		mainPanel.setLayout(new BorderLayout());
		
		JPanel devPanel = new JPanel();
		devPanel.setVisible(true);
		devPanel.setLayout(new FlowLayout());
		
		JButton viewModeButton = new JButton("Toggle View Mode");
		
		viewModeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(grid.getViewMode())
					grid.setViewMode(false);
				else
					grid.setViewMode(true);
				grid.repaint();
			}
		});
		
		devPanel.add(viewModeButton);
		
		mainPanel.add(grid, BorderLayout.CENTER);
		mainPanel.add(devPanel, BorderLayout.NORTH);
		
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		frame.setTitle("Epic Crawl"); // Game title
		frame.setSize(700,700); // Size of play window on start, later support changing screen size
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.repaint();
	}
}
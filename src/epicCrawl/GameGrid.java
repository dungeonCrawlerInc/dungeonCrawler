package epicCrawl;

//Imports
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

//
@SuppressWarnings("serial")
public class GameGrid extends JPanel{
	private boolean _viewMode = false;

	private int _maxRows = 50, _maxColumns = 50; // Size of the available grid
	private int _rows = 15, _columns = 15;
	
	private Action leftAction, rightAction, upAction, downAction;
	
	private  ArrayList<GridSquare>[][] _grid; // Grid for current map
	
	private int playerX, playerY, newX, newY; // --------------------------------------------------

	GridSquare grassSquare, dirtSquare, darkWoodFloorSquare, lightWoodFloorSquare, stoneWallSquare,
		voidSquare;
	
	BufferedImage playerImage;
	
	// Room constructor
	@SuppressWarnings("unchecked")
	public GameGrid(){
		grassSquare = new GridSquare("grassFloor", "grass.png", true);
		dirtSquare = new GridSquare("dirtFloor", "dirt.png", true);
		darkWoodFloorSquare = new GridSquare("woodFloorDark", "32x32WoodFloor.png", true);
		lightWoodFloorSquare = new GridSquare("woodFloorLight", "woodfloor.png", true);
		stoneWallSquare = new GridSquare("stoneWall", "32x32StoneWall.png", false);
		voidSquare = new GridSquare("void", "Void.png", false);
		
		setFocusable(true); // Not needed?
		setUpKeyBindings();

		_grid = (ArrayList<GridSquare>[][])new ArrayList[_maxRows][_maxColumns]; // new ArrayList<GridSquare>()[_maxRows][_maxColumns];
		
		for(int i = 0; i < _maxRows; ++i){
			for(int j = 0; j < _maxColumns; ++j){
				_grid[i][j] = new ArrayList<GridSquare>(); 
			}
		}
		loadLevel("Levels/newLevel.txt");
	}
	
	public void loadLevel(String levelFileName){
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(levelFileName);
		Scanner lineScanner = new Scanner(inStream);
		Scanner wordScanner = null;
		int curRow = 0, curCol = 0;
		String cur = "";

		while(lineScanner.hasNextLine()){
			wordScanner = new Scanner(lineScanner.nextLine());
			
			while(wordScanner.hasNext()){
				cur = wordScanner.next();
				
				if(cur.equals("dirt.png"))
					_grid[curCol][curRow].add(dirtSquare);
				else if(cur.equals("grass.png"))
					_grid[curCol][curRow].add(grassSquare);
				else if(cur.equals("Door.png"))
					_grid[curCol][curRow].add(new GridSquare("door", "Door.png", true));
				else if(cur.equals("woodfloor.png"))
					_grid[curCol][curRow].add(lightWoodFloorSquare);
				else if(cur.equals("32x32WoodFloor.png"))
					_grid[curCol][curRow].add(darkWoodFloorSquare);
				else if(cur.equals("32x32StoneWall.png"))
					_grid[curCol][curRow].add(stoneWallSquare);
				else if(cur.equals("chairleft.png"))
					_grid[curCol][curRow].add(new GridSquare("leftchair", "chairleft.png", false));
				else if(cur.equals("chairright.png"))
					_grid[curCol][curRow].add(new GridSquare("rightchair", "chairright.png", false));
				else if(cur.equals("Chest.png"))
					_grid[curCol][curRow].add(new GridSquare("chest", "Chest.png", false));
				else if(cur.equals("Enemy.png"))
					_grid[curCol][curRow].add(new GridSquare("enemy", "Enemy.png", false));
				else if(cur.equals("GIRL.png"))
					_grid[curCol][curRow].add(new GridSquare("girl", "GIRL.png", false));
				else if(cur.equals("TallTablewithfood.png"))
					_grid[curCol][curRow].add(new GridSquare("tableFood", "TallTablewithfood.png", false));
				else if(cur.equals("Void.png"))
					_grid[curCol][curRow].add(voidSquare);
				else if(cur.equals("table.png"))
					_grid[curCol][curRow].add(new GridSquare("table", "table.png", false));
				else if(cur.equals("Portal.png"))
					_grid[curCol][curRow].add(new GridSquare("portal", "Portal.png", true));

				++curCol;
			}
			
			curCol = 0;
			++curRow;
		}
		
		playerX = 3;
		playerY = 24;
		
		BufferedImage bufImage = null;

		   InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + "CHARACTER-Armor.png");
		   try{
			   bufImage = ImageIO.read(input);
		   }catch (IOException e){System.err.println("Failed to load image for grid square.");}
			
		   playerImage = bufImage;
		
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
		
		// Check if move is out of bounds
		if(newX < 0 || newX > _maxRows -1 || newY < 0 || newY > _maxColumns - 1){
			return;
		}
		
		GridSquare nextSquare = _grid[newX][newY].get(_grid[newX][newY].size() - 1); // grab square type 
		
		if(!nextSquare.isPassable()){ // Check if trying to move through solid object
			return;
		}
		
		playerX = newX;
		playerY = newY;
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
		g.clearRect(0, 0, getWidth(), getHeight()); // Clear the board

		if(!_viewMode){ // 15x15 view mode
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

					if(_grid[viewX][viewY].get(0) == voidSquare){ // Paint black grid for void space
						g.setColor(Color.BLACK); // All void space is black
						g.fillRect(xCord, yCord, recW, recH); // Fill in the square with black
					}

					if(_grid[viewX][viewY].get(0) != voidSquare){
						for(int z = 0; z < _grid[viewX][viewY].size(); ++z)
								g.drawImage(_grid[viewX][viewY].get(z).getImage(), xCord, yCord, recW, recH, null);
					}
				}
			}
			
			int drawPlayerX = 7, drawPlayerY = 7;
			
			if(playerX < 8)
				drawPlayerX = playerX;
			if(playerY < 8)
				drawPlayerY = playerY;
			if(playerX > _maxColumns - 8)
				drawPlayerX = playerX - (_maxRows- _rows);
			if(playerY > _maxRows - 8)
				drawPlayerY = playerY - (_maxColumns - _columns);
	
			g.drawImage(playerImage, drawPlayerX * recW, drawPlayerY * recH, recW, recH, null);
		}
		else{ // Full view mode
			int recW = getWidth() / _maxRows; // Draw the grid
			int recH = getHeight() / _maxColumns;
			
			for (int i = 0; i <  _maxRows; i++){ // Determine what to draw for each grid square on grid
				int xCord = i * recW; // Upper left corner of this terrain rect
				
				for (int j = 0; j < _maxColumns; j++){
					int yCord = j * recH;
					
					if(_grid[i][j].get(0) == voidSquare){ // Paint black grid for void space
						g.setColor(Color.BLACK); // All void space is black
						g.fillRect(xCord, yCord, recW, recH); // Fill in the square with black
					}
					
					if(_grid[i][j].get(0) != voidSquare){
						for(int z = 0; z < _grid[i][j].size(); ++z)
							g.drawImage(_grid[i][j].get(z).getImage(), xCord, yCord, recW, recH, null);
					}
				}
			}
			
			g.drawImage(playerImage, playerX * recW, playerY * recH, recW, recH, null);
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
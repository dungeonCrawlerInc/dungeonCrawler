package epicCrawl;

//Imports
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//
@SuppressWarnings("serial")
public class GameGrid extends JPanel{
	private boolean _viewMode = false;

	private int _maxRows = 50, _maxColumns = 50; // Size of the available grid
	private int _rows = 15, _columns = 15;
	private int playerX, playerY, newX, newY;
    public int _characterLevel;
    public String _characterName;

	private ArrayList<GridObject>[][] _grid; // Grid for current map
	GridObject grassSquare, dirtSquare, darkWoodFloorSquare, lightWoodFloorSquare, stoneWallSquare,
		voidSquare, mediumWoodFloorSqurae, redWoodFloorSquare, treeAndShrubNorSBorderSquare, treeSquare;
	BufferedImage playerImage;
	private String curLevel;
	
	// Room constructor
	@SuppressWarnings("unchecked")
	public GameGrid(){
        _characterLevel = 1;

        grassSquare = new GridObject("grass", true);
		dirtSquare = new GridObject("dirt", true);
		darkWoodFloorSquare = new GridObject("woodFloorDark", true);
        mediumWoodFloorSqurae = new GridObject("woodFloorMedium", true);
        redWoodFloorSquare = new GridObject("woodFloorRed", true);
		lightWoodFloorSquare = new GridObject("woodFloorLight", true);
		stoneWallSquare = new GridObject("stoneWall", false);
		voidSquare = new GridObject("void", false);
        treeAndShrubNorSBorderSquare = new GridObject("treeAndShrubNorSBorder", false);
        treeSquare = new GridObject("tree", false);
		
		setFocusable(true); // Not needed?
		setUpKeyBindings();

		_grid = (ArrayList<GridObject>[][])new ArrayList[_maxRows][_maxColumns]; // new ArrayList<GridObject>()[_maxRows][_maxColumns];
		
		for(int i = 0; i < _maxRows; ++i){
			for(int j = 0; j < _maxColumns; ++j){
				_grid[i][j] = new ArrayList<GridObject>();
			}
		}

        curLevel = "Town.txt";
		loadLevel(curLevel);

		playerX = 45;
		playerY = 1;

		BufferedImage bufImage = null;

		InputStream input = this.getClass().getClassLoader().getResourceAsStream("Images/" + "characterArmor.png");
		try{
			bufImage = ImageIO.read(input);
		}catch (IOException e){System.err.println("Failed to load image for grid square.");}

		playerImage = bufImage;
	}
	
	public void loadLevel(String levelFileName){
		levelFileName = "Levels/" + levelFileName;
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(levelFileName);
		Scanner lineScanner = new Scanner(inStream);
		Scanner stringScanner = null;
		int curRow = 0, curCol = 0;
		String curString;

		while(lineScanner.hasNextLine()){
			stringScanner = new Scanner(lineScanner.nextLine());
			
			while(stringScanner.hasNext()){
				curString = stringScanner.next();
				
				// Inner while loop if there are multiple images in string ex: Dirt.png,chest.png
				ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(curString.split(",")));

				for(String cur: wordList){
					if(cur.equals("dirt.png"))
						_grid[curCol][curRow].add(dirtSquare);

                    else if(cur.equals("bed.png"))
                        _grid[curCol][curRow].add(new GridObject("bed", false));
                    else if(cur.equals("rock.png"))
                        _grid[curCol][curRow].add(new GridObject("rock", false));
                    else if(cur.equals("bedWithCat.png"))
                        _grid[curCol][curRow].add(new GridObject("bedWithCat", false));
					else if(cur.equals("grass.png"))
						_grid[curCol][curRow].add(grassSquare);
                    else if(cur.equals("woodFloorMedium.png"))
                        _grid[curCol][curRow].add(mediumWoodFloorSqurae);
                    else if(cur.equals("woodFloorRed.png"))
                        _grid[curCol][curRow].add(redWoodFloorSquare);
					else if(cur.equals("doorInsideToInside.png"))
						_grid[curCol][curRow].add(new GridObject("doorInsideToInside", true));
                    else if(cur.equals("doorInsideToOutside.png"))
                        _grid[curCol][curRow].add(new GridObject("doorInsideToOutside", true));
                    else if(cur.equals("doorOutsideToInside.png"))
                        _grid[curCol][curRow].add(new GridObject("doorOutsideToInside", true));
					else if(cur.equals("woodFloorLight.png"))
						_grid[curCol][curRow].add(lightWoodFloorSquare);
					else if(cur.equals("woodFloorDark.png"))
						_grid[curCol][curRow].add(darkWoodFloorSquare);
					else if(cur.equals("stoneWall.png"))
						_grid[curCol][curRow].add(stoneWallSquare);
					else if(cur.equals("chairLeftFacing.png"))
						_grid[curCol][curRow].add(new GridObject("chairLeftFacing", false));
					else if(cur.equals("chairRightFacing.png"))
						_grid[curCol][curRow].add(new GridObject("chairRightFacing", false));
					else if(cur.equals("chest.png"))
						_grid[curCol][curRow].add(new GridObject("chest", false));
					else if(cur.equals("enemyBull.png"))
						_grid[curCol][curRow].add(new GridObject("enemyBull", false));
                    else if(cur.equals("enemySkeleton.png"))
                        _grid[curCol][curRow].add(new GridObject("enemySkeleton", false));
					else if(cur.equals("girl.png"))
						_grid[curCol][curRow].add(new GridObject("girl", false));
					else if(cur.equals("tablewithfood.png"))
						_grid[curCol][curRow].add(new GridObject("tablewithfood", false));
					else if(cur.equals("void.png"))
						_grid[curCol][curRow].add(voidSquare);
					else if(cur.equals("table.png"))
						_grid[curCol][curRow].add(new GridObject("table", false));
					else if(cur.equals("portal.png"))
						_grid[curCol][curRow].add(new GridObject("portal", true));
                    else if(cur.equals("tableLong.png"))
                        _grid[curCol][curRow].add(new GridObject("tableLong", false));
                    else if(cur.equals("tree.png"))
                        _grid[curCol][curRow].add(treeSquare);
                    else if(cur.equals("treeAndShrubNorSBorder.png"))
                        _grid[curCol][curRow].add(treeAndShrubNorSBorderSquare);
                    else if(cur.equals("houser0c0.png"))
                        _grid[curCol][curRow].add(new GridObject("houser0c0", false));
                    else if(cur.equals("houser0c1.png"))
                        _grid[curCol][curRow].add(new GridObject("houser0c1", false));
                    else if(cur.equals("houser0c2.png"))
                        _grid[curCol][curRow].add(new GridObject("houser0c2", false));
                    else if(cur.equals("houser0c3.png"))
                        _grid[curCol][curRow].add(new GridObject("houser0c3", false));
                    else if(cur.equals("houser0c4.png"))
                        _grid[curCol][curRow].add(new GridObject("houser0c4", false));
                    else if(cur.equals("houser1c0.png"))
                        _grid[curCol][curRow].add(new GridObject("houser1c0", false));
                    else if(cur.equals("houser1c1.png"))
                        _grid[curCol][curRow].add(new GridObject("houser1c1", false));
                    else if(cur.equals("houser1c2.png"))
                        _grid[curCol][curRow].add(new GridObject("houser1c2", false));
                    else if(cur.equals("houser1c3.png"))
                        _grid[curCol][curRow].add(new GridObject("houser1c3", false));
                    else if(cur.equals("houser1c4.png"))
                        _grid[curCol][curRow].add(new GridObject("houser1c4", false));
                    else if(cur.equals("houser2c0.png"))
                        _grid[curCol][curRow].add(new GridObject("houser2c0", false));
                    else if(cur.equals("houser2c1.png"))
                        _grid[curCol][curRow].add(new GridObject("houser2c1", false));
                    else if(cur.equals("houser2c3.png"))
                        _grid[curCol][curRow].add(new GridObject("houser2c3", false));
                    else if(cur.equals("houser2c4.png"))
                        _grid[curCol][curRow].add(new GridObject("houser2c4", false));
                    else if(cur.equals("villager1.png"))
                        _grid[curCol][curRow].add(new GridObject("villager1", false));
				}
				
				++curCol;
			}
			
			curCol = 0;
			++curRow;
		}
		
		lineScanner.close();
		stringScanner.close();
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
		
		GridObject nextSquare = _grid[newX][newY].get(_grid[newX][newY].size() - 1); // grab square type
		
		if(!nextSquare.isPassable()){ // Check if trying to move through solid object
			return;
		}
		
		playerX = newX;
		playerY = newY;
		
		if(curLevel.equals("Town.txt") && playerX == 7 && playerY == 7){ // Temporary portal jump
            curLevel = "House1.txt";
            loadLevel(curLevel);
			playerX = 19;
			playerY = 27;
		}
		else if(curLevel.equals("House1.txt") && playerX == 19 && playerY == 28){
            curLevel = "Town.txt";
            loadLevel(curLevel);
			playerX = 7;
			playerY = 8;
		}
        else if(curLevel.equals("Town.txt") && playerX == 25 && playerY == 7){
            curLevel = "House2.txt";
            loadLevel(curLevel);
            playerX = 19;
            playerY = 27;
        }
        else if(curLevel.equals("House2.txt") && playerX == 19 && playerY == 28){
            curLevel = "Town.txt";
            loadLevel(curLevel);
            playerX = 25;
            playerY = 8;
        }
        else if(curLevel.equals("Town.txt") && playerX == 9 && playerY == 42){
            curLevel = "Dungeon.txt";
            loadLevel(curLevel);
            playerX = 4;
            playerY = 4;
        }
        else if(curLevel.equals("Dungeon.txt") && playerX == 43 && playerY == 42){
            curLevel = "Town.txt";
            loadLevel(curLevel);
            playerX = 24;
            playerY = 42;
        }
		
		
		
		
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

					for(int z = 0; z < _grid[viewX][viewY].size(); ++z)
						g.drawImage(_grid[viewX][viewY].get(z).getImage(), xCord, yCord, recW, recH, null);
				}
			}
			
			int drawPlayerX = 7, drawPlayerY = 7;
			
			if(playerX < 8)
				drawPlayerX = playerX;
			if(playerY < 8)
				drawPlayerY = playerY;
			if(playerX > _maxColumns - 8)
				drawPlayerX = playerX - (_maxRows - _rows);
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
					
					for(int z = 0; z < _grid[i][j].size(); ++z)
						g.drawImage(_grid[i][j].get(z).getImage(), xCord, yCord, recW, recH, null);
				}
			}
			
			g.drawImage(playerImage, playerX * recW, playerY * recH, recW, recH, null);
		}
	}

	private void setUpKeyBindings(){
        Action leftAction = new LeftAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "doLeftAction");  
		getActionMap().put( "doLeftAction", leftAction );

        Action upAction = new UpAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "doUpAction");  
		getActionMap().put( "doUpAction", upAction );

        Action rightAction = new RightAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "doRightAction");  
		getActionMap().put( "doRightAction", rightAction );

        Action downAction = new DownAction();
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "doDownAction");  
		getActionMap().put( "doDownAction", downAction );
	}
	
	class LeftAction extends AbstractAction{
		public void actionPerformed(ActionEvent ae){movePlayer("left");}
	}
	
	class UpAction extends AbstractAction{
		public void actionPerformed(ActionEvent ae){movePlayer("up");}
	}
	
	class RightAction extends AbstractAction{
		public void actionPerformed(ActionEvent ae){movePlayer("right");}
	}
	
	class DownAction extends AbstractAction{
		public void actionPerformed(ActionEvent ae){movePlayer("down");}
	}

	public void setViewMode(boolean x){_viewMode = x;}
	
	public boolean getViewMode(){return _viewMode;}

    public void setCharacterName(String characterName){
        _characterName = characterName;
    }
	
	public static void main(java.lang.String[] args){
		JFrame frame = new JFrame();
		
		final GameGrid grid = new GameGrid();
        grid.setCharacterName("Hero");
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
			public void actionPerformed(ActionEvent ae){
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
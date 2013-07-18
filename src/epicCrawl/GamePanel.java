package epicCrawl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = -2089981632801905548L;
	private JButton mainMenu;
	private GridSquareTypes gridSquareTypes;
	private GameGrid gameGrid;
	private JPanel gameInfoPanel, gameTextPanel, gridAndTextPanel;
	private JList textList;

	public GamePanel(JButton _mainMenu){
		setFocusable(true);
		mainMenu = _mainMenu;
		setLayout(new BorderLayout());
		setBackground(Color.GREEN);
		setUpGameInfoPanel();
		setUpGridAreaAndTextArea();
	}

	private void setUpGameInfoPanel(){
		gameInfoPanel = new JPanel();
		gameInfoPanel.setLayout(new FlowLayout());
		gameInfoPanel.setBackground(Color.BLUE);
		gameInfoPanel.add(mainMenu);
		gameInfoPanel.setVisible(true);
		Dimension x = new Dimension(300, 200);
		gameInfoPanel.setPreferredSize(x);

		Border gameInfoPanelBorder = BorderFactory.createTitledBorder("Character Stats/Items/Inventory");
		// createTitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor) 
		
		gameInfoPanel.setBorder(gameInfoPanelBorder);
		add(gameInfoPanel, BorderLayout.EAST);
	}

	private void setUpGridAreaAndTextArea(){ // Fix the panel and list...............
		gridAndTextPanel = new JPanel();
		gridAndTextPanel.setLayout(new BorderLayout());
		
		GridSquareTypes gridSquareTypes = new GridSquareTypes();

		gameGrid = new GameGrid();
		gameGrid.setHome(); // -------------------

		gameGrid.setBackground(Color.BLACK);
		gameGrid.setVisible(true);
		
		gameTextPanel = new JPanel();
		gameTextPanel.setBackground(Color.GRAY);
		gameTextPanel.setVisible(true);
		gameTextPanel.setLayout( new BorderLayout() );
		
		DefaultListModel model = new DefaultListModel();
		
		textList = new JList(model);
		textList.setBackground(Color.GRAY);
		textList.setVisibleRowCount(4);
		
		Border textListBorder = BorderFactory.createTitledBorder("Game Messages/Combat Dialogue");
		// createTitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor) 
		
		textList.setBorder(textListBorder);
		
		 for (int i = 0; i < 4; i++)
			model.addElement(" ");
		
		JScrollPane pane = new JScrollPane(textList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMinimum());

		gameTextPanel.add(pane, BorderLayout.CENTER);

		gridAndTextPanel.add(gameGrid, BorderLayout.CENTER);
		gridAndTextPanel.add(gameTextPanel, BorderLayout.SOUTH);
		add(gridAndTextPanel, BorderLayout.CENTER);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
	}

	public static void main(java.lang.String[] args){
		JFrame frame = new JFrame();

		JButton mainMenu = new JButton("Main Menu");
		mainMenu.setBackground(Color.RED);

		GamePanel gamePanel = new GamePanel(mainMenu);
		gamePanel.setVisible(true);
		gamePanel.repaint();

		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		frame.setTitle("Epic Crawl"); // Game title
		frame.setSize(700,700); // Size of play window on start, later support changing screen size
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.repaint();
	}
}
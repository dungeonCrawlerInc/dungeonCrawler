package epicCrawl;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel{
    private GameGrid _gameGrid;
    private JFrame _gameFrame;
	private JButton _mainMenu;
    private String _characterName;
    private JPanel _infoPanel, _inventoryPanel, _itemPanel, _gridAndTextPanel;
    private JTabbedPane _dialogueTabbedPane;
    private JScrollPane _allMessagesScrollPane, _combatScrollPane, _dialogueScrollPane;
    private JLabel _infoLabel;

	public GamePanel(JFrame gameFrame, JButton mainMenu){
		setFocusable(true);

        _gameFrame = gameFrame;
		_mainMenu = mainMenu;

		setLayout(new BorderLayout());
		setBackground(Color.GREEN);

		setUpGridAreaAndTextArea();
        add(_gridAndTextPanel, BorderLayout.CENTER);

        setUpInfoPanel();
        add(_infoPanel, BorderLayout.EAST);
	}

    private void setUpItemPanel(){
       _itemPanel = new JPanel();
       _itemPanel.setLayout(new FlowLayout());
       _itemPanel.setVisible(true);
       _itemPanel.setBackground(Color.DARK_GRAY);

        LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black, 3);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "Items");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.BLACK);
        _itemPanel.setBorder(titledBorder);
    }

    private void setUpInventoryPanel(){
        _inventoryPanel = new JPanel();

        int numInvRows = 4;
        int numInvCols = 4;

        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(numInvCols);
        gridLayout.setRows(numInvRows);

        _inventoryPanel.setLayout(gridLayout);
        _inventoryPanel.setVisible(true);
        _inventoryPanel.setBackground(Color.DARK_GRAY);

        for(int row = 0; row < numInvRows; ++row){
            for(int col = 0; col < numInvCols; ++col){
                JPanel tmpPanel = new JPanel();
                tmpPanel.setBackground(Color.BLUE);
                tmpPanel.setBorder((LineBorder)BorderFactory.createLineBorder(Color.BLACK, 2));
                tmpPanel.setVisible(true);

                _inventoryPanel.add(tmpPanel);
            }
        }

        LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black, 3);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "Inventory");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.BLACK);
        _inventoryPanel.setBorder(titledBorder);
    }

    private void setUpInfoLabel(){
        _infoLabel = new JLabel();
        _infoLabel.setVisible(true);
        _infoLabel.setBackground(Color.GREEN);

        updateInfoLabel();
    }

	private void setUpInfoPanel(){
        _infoPanel = new JPanel();
        _infoPanel.setSize(_gameFrame.getWidth() / 4, _gameFrame.getHeight());
        _infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.PAGE_AXIS));

        setUpItemPanel();
        setUpInventoryPanel();
        setUpInfoLabel();

        _infoPanel.add(_mainMenu);
        _infoPanel.add(_infoLabel);
        _infoPanel.add(_itemPanel);
        _infoPanel.add(_inventoryPanel);
	}

    @SuppressWarnings({"unchecked" })
    private void setUpScrollPanes(){
        String[] data1 = {"1:", "2:", "3:", "4:", "5:", "6:", "7:", "8:", "9:", "10:"};
        JList dataList1 = new JList(data1);
        dataList1.setBackground(Color.DARK_GRAY);
        //dataList1.setVisibleRowCount(4);
        _allMessagesScrollPane = new JScrollPane(dataList1);

        String[] data2 = {"1:", "2:", "3:", "4:", "5:", "6:", "7:", "8:", "9:", "10:"};
        JList dataList2 = new JList(data2);
        dataList2.setBackground(Color.DARK_GRAY);
        //dataList2.setVisibleRowCount(4);
        _combatScrollPane = new JScrollPane(dataList2);

        String[] data3 = {"1:", "2:", "3:", "4:", "5:", "6:", "7:", "8:", "9:", "10:"};
        JList dataList3 = new JList(data3);
        dataList3.setBackground(Color.DARK_GRAY);
        //dataList3.setVisibleRowCount(4);
        _dialogueScrollPane = new JScrollPane(dataList3);
    }

    private void setUpDialogueArea(){
        setUpScrollPanes();

        _dialogueTabbedPane = new JTabbedPane();

        _dialogueTabbedPane.addTab("All Messages", null, _allMessagesScrollPane,
                "Does nothing");

        _dialogueTabbedPane.addTab("Combat Only", null, _combatScrollPane,
                "Does nothing");

        _dialogueTabbedPane.addTab("Dialogue Only", null, _dialogueScrollPane,
                "Does nothing");

        _dialogueTabbedPane.setBackground(Color.BLACK);
        _dialogueTabbedPane.setVisible(true);
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setUpGridAreaAndTextArea(){
        _gridAndTextPanel = new JPanel();
		_gridAndTextPanel.setLayout(new BorderLayout());

        _gameGrid = new GameGrid();
        _gameGrid.setCharacterName(_characterName);
		_gameGrid.setBackground(Color.BLACK);
		_gameGrid.setVisible(true);

		setUpDialogueArea();

		_gridAndTextPanel.add(_gameGrid, BorderLayout.CENTER);
		_gridAndTextPanel.add(_dialogueTabbedPane, BorderLayout.SOUTH);
	}

    public void setCharacterName(String characterName){
        _characterName = characterName;
    }

    public void updateInfoLabel(){
        _infoLabel.setText("<html>Hero : " + _characterName + "<br>" +
                "Level: " + _gameGrid._characterLevel + "</html>");
    }

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); // Important to call super class method
	}

	public static void main(java.lang.String[] args){
		JFrame frame = new JFrame();

		JButton mainMenu = new JButton("Main Menu");
		mainMenu.setBackground(Color.RED);

		GamePanel gamePanel = new GamePanel(frame, mainMenu);
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
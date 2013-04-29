import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * @author Greg Hilston
 */
public class dungeonCrawler extends JFrame {
	public dungeonCrawler() {
		initComponents();
	}

	private void newCharButtonActionPerformed(ActionEvent e) {
		createCharacter newCharMenu = new createCharacter();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Greg Hilston
		label1 = new JLabel();
		newCharButton = new JButton();
		loadCharButton = new JButton();
		aboutButton = new JButton();

		//======== this ========
		setTitle("Dungeon Crawler");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("Dungeon Crawler");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 36));

		//---- newCharButton ----
		newCharButton.setText("New Character");
		newCharButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newCharButtonActionPerformed(e);
			}
		});

		//---- loadCharButton ----
		loadCharButton.setText("Load Character");

		//---- aboutButton ----
		aboutButton.setText("About");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(53, 53, 53)
					.addComponent(label1)
					.addContainerGap(56, Short.MAX_VALUE))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(22, 22, 22)
					.addComponent(newCharButton)
					.addGap(18, 18, 18)
					.addComponent(loadCharButton)
					.addGap(18, 18, 18)
					.addComponent(aboutButton, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(22, 22, 22))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(newCharButton)
						.addComponent(loadCharButton)
						.addComponent(aboutButton))
					.addGap(30, 30, 30))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		
		setVisible(true); // Make it appear
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Greg Hilston
	private JLabel label1;
	private JButton newCharButton;
	private JButton loadCharButton;
	private JButton aboutButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	
	
   public static void main(String[] args){ new dungeonCrawler();} // Call creation of game
}

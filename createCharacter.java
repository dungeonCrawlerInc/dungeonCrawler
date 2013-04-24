import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
/*
 * Created by JFormDesigner on Wed Apr 24 15:52:21 EDT 2013
 */



/**
 * @author Greg Hilston
 */
public class createCharacter extends JFrame {
	public createCharacter() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Greg Hilston
		characterNameTextField = new JTextField();
		label1 = new JLabel();
		startButton = new JButton();

		//======== this ========
		setTitle("Create Your Character");
		Container contentPane = getContentPane();

		//---- characterNameTextField ----
		characterNameTextField.setText("Joe Shmoo");

		//---- label1 ----
		label1.setText("Character Name:");

		//---- startButton ----
		startButton.setText("Start Game");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGap(23, 23, 23)
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(characterNameTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addGap(120, 120, 120))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(144, 144, 144)
					.addComponent(startButton)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(characterNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label1))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
					.addComponent(startButton)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Greg Hilston
	private JTextField characterNameTextField;
	private JLabel label1;
	private JButton startButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

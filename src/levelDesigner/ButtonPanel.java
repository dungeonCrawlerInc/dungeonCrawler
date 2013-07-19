package levelDesigner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{
	private  ButtonListener client;
	private String[] labels;
	JComboBox comboBox;
	JList imageList;

	public ButtonPanel(String[] args, ButtonListener bl){
		client = bl;
		labels = args;

		makeButtons();
		
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				String cmbType = (String) jcmbType.getSelectedItem();
				
				if(client != null)
					client.buttonPressed(jcmbType.getSelectedIndex());
				else
					System.out.println("No Client: mouseReleased");
			}
		});
	}

	private void makeButtons(){
		comboBox = new JComboBox(labels);
		comboBox.setVisible(true);
		this.add(comboBox);
	}
}
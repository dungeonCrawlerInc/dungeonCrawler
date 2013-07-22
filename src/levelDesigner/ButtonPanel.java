package levelDesigner;

import java.awt.FlowLayout;
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
	private Button saveLevel, loadLevel, iconPaintSize;

	public ButtonPanel(String[] args, ButtonListener bl){
		client = bl;
		labels = args;

		this.setLayout(new FlowLayout());
		
		makeComboBox();
		makeButtons();
	}
	
	public void makeButtons(){
		saveLevel = new Button("Save Level", -1, client);
		saveLevel.setVisible(true);
		
		loadLevel = new Button("Load Level", -2, client);
		loadLevel.setVisible(true);

        iconPaintSize = new Button("Paint Brush Size", -3, client); //Square of size n by n TODO: Include other brush shapes
        iconPaintSize.setVisible(true);
		
		this.add(saveLevel);
		this.add(loadLevel);
        this.add(iconPaintSize);
	}
	
	public void makeComboBox(){
		comboBox = new JComboBox(labels);
		comboBox.setVisible(true);
		this.add(comboBox);
		
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
}

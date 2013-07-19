package levelDesigner;

import java.awt.event.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton{
	private ButtonListener listener;
	private String label;
	private int identifier;

	public Button(String labl, int id, ButtonListener bl){
		this.setText(labl);
		this.setSize(20,20);
		listener = bl;
		label = labl;
		identifier = id;

		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				if(listener != null)
					listener.buttonReleased( label, identifier );
				else
					System.out.println("No Listener: mouseReleased");
			}
		});
	}
} 
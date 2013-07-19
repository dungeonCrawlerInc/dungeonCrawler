package levelDesigner;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements ButtonListener{
	private  ButtonListener client;
	private String[] labels;
	private int numberOfButtons;
	private int longestLabel = -1;

	public ButtonPanel(String[] args, ButtonListener bl){
		client = bl;
		labels = args;
		numberOfButtons = labels.length;

		for(int i = 0; i < numberOfButtons; i++){
			if( labels[i].length() > longestLabel)
				longestLabel = labels[i].length();
		}

		makeButtons();
	}

	private void makeButtons(){
		Button[] buttons = new Button[ labels.length ];

		for (int b = 0; b < buttons.length; b++){
			buttons[ b ] = new Button(labels[b], b, this); 
			this.add( buttons[ b ]);
		}
	}

	public void buttonPressed( String buttonLabel, int buttonId ){}

	public void buttonReleased( String buttonLabel, int buttonId ){
		if(client != null)
			client.buttonPressed( buttonLabel, buttonId );
		else
			System.out.println("No Client: mouseReleased");
	}

	public void buttonClicked(String buttonLabel, int buttonId){}
}
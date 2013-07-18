package levelDesigner;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Button extends JButton{
	private ButtonListener listener;
    private String         label;
    private int            identifier;

    public Button( int x, int y, String labl, int id, ButtonListener bl ){
        this.setText(labl);
        this.setSize(20,20);
        listener = bl;
        label = labl;
        identifier = id;
        
        this.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent ae){
    			System.out.println("Button " + identifier + " pressed");
    			
    			if(listener != null)
    	            listener.buttonReleased( label, identifier );
    	         else
    	            System.out.println("No Listener: mouseReleased");
    		}
    	});
    }
   
   public static void main(String[] args){     
      JFrame frame = new JFrame();
      
      Button bp =  new Button(10, 10, "Button", 1,  null);
      
      frame.add(bp);
      frame.setVisible(true);
      frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
      frame.setLocationRelativeTo(null);
      frame.pack();
      frame.repaint();
   }
} 
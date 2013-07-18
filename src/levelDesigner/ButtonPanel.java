package levelDesigner;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements ButtonListener{
   private  ButtonListener client;
   private String[] labels;
   private int numberOfButtons;
   private int longestLabel = -1;
   private int x, y;
 
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
      int bh = 20;  // button box height
      
      Button[] buttons = new Button[ labels.length ];
      int bx = x + 8;  // x coord of all buttons
      int by = y + 8;  
      
      for (int b = 0; b < buttons.length; b++, by += (bh+7)){
         buttons[ b ] = new Button( bx, by, labels[ b ], b, this); 
         this.add( buttons[ b ]);
      }
   }
   
   public void buttonPressed( String buttonLabel, int buttonId ){
       if(client != null)
           client.buttonPressed( buttonLabel, buttonId );
       else
           System.out.println("No Client: mousePressed");
   }
   
   public void buttonReleased( String buttonLabel, int buttonId ){
      if(client != null)
           client.buttonPressed( buttonLabel, buttonId );
       else
           System.out.println("No Client: mouseReleased");
   }
   
   public void buttonClicked(String buttonLabel, int buttonId){
      if(client != null)
           client.buttonPressed( buttonLabel, buttonId );
       else
           System.out.println("No Client: mouseClicked");
   }
   
   public static void main(String[] args){     
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      panel.setSize(300,300);
      panel.setVisible(true);
      panel.setLayout(new FlowLayout());
      
      String test [] = {"One", "two", "Three","veryverylong"};
      ButtonPanel bp =  new ButtonPanel(test, null);
      
       String test2 [] = {"One"};
      ButtonPanel bp2 =  new ButtonPanel(test2, null);
      
      panel.add(bp);
      panel.add(bp2);
      
      frame.add(panel);
      
      frame.setVisible(true);
      frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
      frame.setLocationRelativeTo(null);
      frame.setTitle("Epic Crawl - Main Menu");
      frame.pack();
      frame.repaint();
   }   
}
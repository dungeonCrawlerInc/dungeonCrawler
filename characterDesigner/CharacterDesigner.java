//Commented out two lines in Enemy so I could compile
//I believe there is an issue with the EpicCrawl package
// ------ I think it's because I got rid of the setMoving method in LivingObject but didn't get rid of the call to it
// ------ in enemy, that line should be deleted, I think setLiving should be fine uncommented.


package characterDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterDesigner extends JPanel implements ButtonListener, ActionListener{
    protected JFrame frame;
    protected JTextField textField;

    public CharacterDesigner(){
        super(new GridBagLayout());

        //Setup for frame
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Character Designer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit

        textField = new JTextField(12);
        TextPrompt namePrompt = new TextPrompt("Character Name", textField);
        textField.addActionListener(this);
        frame.add(textField);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void buttonPressed(int id){
        return;
    }

    public static void main(String[] args) {
        CharacterDesigner characterDesigner = new CharacterDesigner();
    } //main
} //class EmptyFrame1

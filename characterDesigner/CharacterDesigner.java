//Commented out two lines in Enemy so I could compile
//I believe there is an issue with the EpicCrawl package
// ------ I think it's because I got rid of the setMoving method in LivingObject but didn't get rid of the call to it
// ------ in enemy, that line should be deleted, I think setLiving should be fine uncommented.

package characterDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterDesigner extends JPanel implements ButtonListener, ActionListener{
    protected JFrame frame;
    protected JPanel panel;
    protected TextPrompt namePrompt;
    protected JTextField textField, strength, dexterity, intelligence, remainingAttributePoints;
    protected JComboBox raceList;
    protected JButton createCharacterButton, increaseStrengthButton, decreaseStrengthButton, increaseDexterityButton, decreaseDexterityButton, increaseIntelligenceButton, decreaseIntelligenceButton;
    String[] raceStrings = {"Human", "Elf", "Dwarf", "Half-Orc", "Gnome"};
    protected String characterName, race, characterClass;
    protected int strengthVal = 10, dexterityVal = 10, intelligenceVal = 10, remainingAttributePointsVal = 12;

    public CharacterDesigner(){
        super(new GridBagLayout());

        //Setup for JFrame
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Character Designer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit

        //Setup for JPanel
        panel = new JPanel();
        frame.add(panel);

        //Character Name
        textField = new JTextField(12);
        namePrompt = new TextPrompt("Character Name", textField);
        textField.addActionListener(this);
        panel.add(textField);

        //Race Combo Box
        raceList = new JComboBox(raceStrings);
        raceList.setSelectedIndex(0);
        raceList.addActionListener(this);
        panel.add(raceList);

        //Attributes

        //Strength
        increaseStrengthButton = new JButton("+");
        panel.add(increaseStrengthButton);
        increaseStrengthButton.addActionListener(this);

        strength = new JTextField();
        strength.setText("Strength: " + strengthVal);
        strength.setEditable(false);
        panel.add(strength);

        decreaseStrengthButton = new JButton("-");
        panel.add(decreaseStrengthButton);
        decreaseStrengthButton.addActionListener(this);


        //Dexterity
        increaseDexterityButton = new JButton("+");
        panel.add(increaseDexterityButton);
        increaseDexterityButton.addActionListener(this);

        dexterity = new JTextField();
        dexterity.setText("Dexterity: " + dexterityVal);
        dexterity.setEditable(false);
        panel.add(dexterity);

        decreaseDexterityButton = new JButton("-");
        panel.add(decreaseDexterityButton);
        decreaseDexterityButton.addActionListener(this);

        //Intelligence
        increaseIntelligenceButton = new JButton("+");
        panel.add(increaseIntelligenceButton);
        increaseIntelligenceButton.addActionListener(this);

        intelligence = new JTextField();
        intelligence.setText("Intelligence: " + intelligenceVal);
        intelligence.setEditable(false);
        panel.add(intelligence);

        decreaseIntelligenceButton = new JButton("-");
        panel.add(decreaseIntelligenceButton);
        decreaseIntelligenceButton.addActionListener(this);


        remainingAttributePoints = new JTextField();
        remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
        remainingAttributePoints.setEditable(false);
        panel.add(remainingAttributePoints);


        //Create Character
        createCharacterButton = new JButton("Create Character");
        panel.add(createCharacterButton);
        createCharacterButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    //This function is called whenever any character creation fields are altered
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == textField)
        {
            System.out.println("Name Changed!");
        }
        else if(e.getSource() == raceList)
        {
            System.out.println("Race Changed!");
        }
        else if(changedAttributePoints(e.getSource()) == true)
        {
            determineAndUpdateAttribute(e.getSource());
        }
        else if(e.getSource() == createCharacterButton)
        {
            createCharacter(textField.getText());
        }
    }

    public void determineAndUpdateAttribute(Object obj)
    {
       if(obj == increaseStrengthButton)
       {
           if(remainingAttributePointsVal > 0)
           {
               remainingAttributePointsVal--;
               strengthVal++;

               remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
               strength.setText("Strength: " + strengthVal);
           }
       }
       else if(obj == decreaseStrengthButton)
       {
            if(strengthVal > 1)
            {
                strengthVal--;
                remainingAttributePointsVal++;

                remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
                strength.setText("Strength: " + strengthVal);
            }
       }

       else if(obj == increaseDexterityButton)
       {
           if(remainingAttributePointsVal > 0)
           {
               remainingAttributePointsVal--;
               dexterityVal++;

               remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
               dexterity.setText("Dexterity: " + dexterityVal);
           }
       }
       else if(obj == decreaseDexterityButton)
       {
           if(dexterityVal > 1)
           {
               dexterityVal--;
               remainingAttributePointsVal++;

               remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
               dexterity.setText("Dexterity: " + dexterityVal);
           }
       }

       else if(obj == increaseIntelligenceButton)
       {
           if(remainingAttributePointsVal > 0)
           {
               remainingAttributePointsVal--;
               intelligenceVal++;

               remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
               intelligence.setText("Intelligence: " + intelligenceVal);
           }
       }
       else if(obj == decreaseIntelligenceButton)
       {
           if(intelligenceVal > 1)
           {
               intelligenceVal--;
               remainingAttributePointsVal++;

               remainingAttributePoints.setText("Remaining Points: " + remainingAttributePointsVal);
               intelligence.setText("Intelligence: " + intelligenceVal);
           }
       }
    }

    public boolean changedAttributePoints(Object obj)
    {
        if(obj == increaseStrengthButton || obj == decreaseStrengthButton || obj == increaseDexterityButton || obj == decreaseDexterityButton || obj == increaseIntelligenceButton || obj == decreaseIntelligenceButton)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean fileAlreadyExists(String fileName)
    {
        File characterFile = new File(fileName);

        if (characterFile.exists()){
            return true;
        }
        else
        {
            return false;
        }
    }

    //Attempts to store the newly created character as an XML file
    //Returns 1 for success and -1 for a file already existing with the given name
    public int createCharacter(String characterName)
    {
        if(remainingAttributePointsVal != 0)
        {
            System.out.println("Remaining Points != 0");
            return -1;
        }
        else if(fileAlreadyExists("savedCharacters/" + characterName + ".char"))
        {
            //TODO: Filename already exists, ask to overwrite or cancel
            return -1;
        }

        File newCharacter = new File("savedCharacters/" + characterName + ".char");
        try{
            FileWriter fw = new FileWriter(newCharacter.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<character>\n");
            bw.write("\t<name>" + characterName + "</name>\n");
            bw.write("\t<race>" + String.valueOf(raceList.getSelectedItem()) + "</race>\n");
            bw.write("\t<strength>" + strengthVal + "</strength>\n");
            bw.write("\t<dexterity>" + dexterityVal + "</dexterity>\n");
            bw.write("\t<intelligence>" + intelligenceVal + "</intelligence>\n");
            bw.write("</character>");
            bw.close();

            System.out.println("Character Successfully Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void buttonPressed(int id){
        return;
    }

    public static void main(String[] args) {
        CharacterDesigner characterDesigner = new CharacterDesigner();
    } //main
} //class EmptyFrame1

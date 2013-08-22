//TODO: Add "Clear Level" button, with confirmation message "Are you sure you want to clear the level?"

package levelDesigner;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{
	private  ButtonListener client;
	private String[] labels;
    private String[] brushShapes = {"Square Brush"};
    private Integer[] paintSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	JComboBox selectedTileCombo, brushShapeCombo;
    public JComboBox iconPaintSizeCombo;    //TODO: Has to be public for now, maybe should make a getter method
	private Button saveLevel, loadLevel, clearLevel;
    public JCheckBox fillCheckBox;   //TODO: Has to be public for now, maybe should make a getter method

	public ButtonPanel(String[] args, ButtonListener bl){
		client = bl;
		labels = args;

		this.setLayout(new FlowLayout());
		
		makeComboBox(); //TODO: Should we rename this function?
		makeButtons();  //TODO: Should we rename this function?
        makePaintBrushOptions();

        fillCheckBox = new JCheckBox("Fill");   //TODO: Maybe we should consolidate all these make buttons into one function...?
        fillCheckBox.setVisible(true);
        this.add(fillCheckBox);

        clearLevel = new Button("Clear Level", -3, client);
        clearLevel.setVisible(true);
        this.add(clearLevel);
	}

    public void makeComboBox(){
        selectedTileCombo = new JComboBox(labels);
        selectedTileCombo.setVisible(true);
        this.add(selectedTileCombo);

        selectedTileCombo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox jcmbType = (JComboBox) e.getSource();
                String cmbType = (String) jcmbType.getSelectedItem();

                if (client != null)
                    client.buttonPressed(jcmbType.getSelectedIndex());
                else
                    System.out.println("No Client: mouseReleased");
            }
        });
    }

	public void makeButtons(){
		saveLevel = new Button("Save Level", -1, client);
		saveLevel.setVisible(true);
        this.add(saveLevel);

		loadLevel = new Button("Load Level", -2, client);
		loadLevel.setVisible(true);
        this.add(loadLevel);
	}

    public void makePaintBrushOptions(){
        brushShapeCombo = new JComboBox(brushShapes);
        brushShapeCombo.setVisible(true);
        this.add(brushShapeCombo);

        iconPaintSizeCombo = new JComboBox(paintSize);
        iconPaintSizeCombo.setVisible(true);
        this.add(iconPaintSizeCombo);
    }
}

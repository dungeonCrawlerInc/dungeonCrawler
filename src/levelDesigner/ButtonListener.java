package levelDesigner;

public interface ButtonListener{
   public void buttonPressed(String label, int id);
   public void buttonReleased(String label, int id);
   public void buttonClicked(String label, int id);
}
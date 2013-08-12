package epicCrawl;

public class NPC extends LivingObject implements ClickableObject{
    private String _dialogue;

    public NPC(String name, int xLoc, int yLoc){
        super(name, xLoc, yLoc);

        _dialogue = "Hello, I'm " + getName();
    }

    public void sayMessage(){
        System.out.println(_dialogue);
    }

    public void click(int playerX, int playerY) {
        System.out.println("Clicked: " + getName());
        int deltaX, deltaY;
        deltaX = Math.abs(playerX - getXLoc());
        deltaY = Math.abs(playerY - getYLoc());

        if(deltaX < 2 && deltaY < 2 && (deltaX == 0 || deltaY == 0)){
            System.out.println("Next to player!");
            sayMessage();
        }
    }
}

package epicCrawl;

public class Enemy extends LivingObject implements ClickableObject{
    private int _health, _level;

    public Enemy(String name, int xLoc, int yLoc, int level, int health){
        super(name, xLoc, yLoc);

        _health = health;
        _level = level;
    }

    public void attacked(){
        System.out.println("Attacked " + getName());
        System.out.println("Level: " + _level + " Health: " + _health);

        _health -= 10;

        if(_health <= 0){
            setAlive(false);
            setMoving(false);
        }
    }

    //public void attack(){
    //
    //}

    public void click(int playerX, int playerY) {
        System.out.println("Clicked: " + getName());
        int deltaX, deltaY;
        deltaX = Math.abs(playerX - getXLoc());
        deltaY = Math.abs(playerY - getYLoc());

        if(deltaX < 2 && deltaY < 2 && (deltaX == 0 || deltaY == 0)){
            System.out.println("Next to player!");
            attacked();
        }
    }
}

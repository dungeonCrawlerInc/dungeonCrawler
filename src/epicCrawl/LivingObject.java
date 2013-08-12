package epicCrawl;

// Living objects might need to have a reference to the grid
// This would allow enemies to know the players current location, valid move directions, etc.

// Any object in the game that is alive is derived from this class.
public class LivingObject extends GridObject implements ClickableObject{
    private boolean _isAlive, _isMoving;
    private int _xLoc, _yLoc;

    public LivingObject(String name, int xLoc, int yLoc){
        super(name, false);

        _xLoc = xLoc;
        _yLoc = yLoc;
        _isAlive = true;
        _isMoving = true;
    }

    public boolean isAlive(){
        return _isAlive;
    }

    public void setAlive(boolean bool){
        _isAlive = bool;
    }

    public boolean isMoving(){
        return _isMoving;
    }

    public void setMoving(boolean bool){
        _isMoving = bool;
    }

    public void setLoc(int xLoc, int yLoc){
        _xLoc = xLoc;
        _yLoc = yLoc;
    }

    public int getXLoc(){
        return _xLoc;
    }

    public int getYLoc(){
        return _yLoc;
    }
}

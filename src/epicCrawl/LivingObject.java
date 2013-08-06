package epicCrawl;

// Any object in the game that is alive is derived from this class.
public class LivingObject extends GridObject{
    private boolean _isAlive;
    private boolean _isMoving;

    public LivingObject(String name, boolean passable, boolean isAlive, boolean isMoving){
        super(name, passable);

        _isAlive = isAlive;
        _isMoving = isMoving;
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
}

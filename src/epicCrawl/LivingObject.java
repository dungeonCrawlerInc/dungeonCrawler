package epicCrawl;

// Living objects might need to have a reference to the grid
// This would allow enemies to know the players current location, valid move directions, etc.

// Any object in the game that is alive is derived from this class.
public class LivingObject extends GridObject implements ClickableObject{
    private boolean _isAlive;
    private int _xLoc, _yLoc, _health, _strength, _dexterity, _intelligence;

    public LivingObject(String name, int xLoc, int yLoc){
        super(name, false);

        _xLoc = xLoc;
        _yLoc = yLoc;
        _isAlive = true;

        //Default parameters (Any class that implements this class should over write these values)
        _health = 10;
        _strength = 10;
        _dexterity = 10;
        _intelligence = 10;
    }

    public boolean isAlive(){
        return _isAlive;
    }

    public void setAlive(boolean bool){
        _isAlive = bool;
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

    public int getHealth() {
        return _health;
    }

    public void setHealth(int _health) {
        this._health = _health;
    }

    public int getStrength() {
        return _strength;
    }

    public void setStrength(int _strength) {
        this._strength = _strength;
    }

    public int getDexterity() {
        return _dexterity;
    }

    public void setDexterity(int _dexterity) {
        this._dexterity = _dexterity;
    }

    public int getIntelligence() {
        return _intelligence;
    }

    public void setIntelligence(int _intelligence) {
        this._intelligence = _intelligence;
    }

    // Needed temporarily
    public void click(int playerX, int playerY){}
}

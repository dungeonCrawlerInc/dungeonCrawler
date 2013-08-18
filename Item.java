/**
 * Created with IntelliJ IDEA.
 * User: Grehg
 * Date: 8/17/13
 * Time: 10:32 PM
 *
 * Item - anything that could be represented as an item in the hero's inventory
 */
public class Item {
    protected String _name;
    protected int _goldValue, _weight;
    protected slotLocation _slotLocation;

    public Item()
    {
        _name = "";
        _slotLocation.setSlotValue(-1);
        _weight = -1;
        _goldValue = -1;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_goldValue() {
        return _goldValue;
    }

    public void set_goldValue(int _goldValue) {
        this._goldValue = _goldValue;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }

    public slotLocation get_slotLocation() {
        return _slotLocation;
    }

    public void set_slotLocation(slotLocation _slotLocation) {
        this._slotLocation = _slotLocation;
    }
}

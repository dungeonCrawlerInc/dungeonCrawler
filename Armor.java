public class Armor extends Item {
    private int _ac;

    public Armor(String name, int goldValue, int weight, int ac) {
        this._name = name;
        this._goldValue = goldValue;
        this._weight = weight;
        this._ac = ac;
    }

    public int get_ac() {
		return _ac;
	}

	public void set_ac(int _ac) {
		this._ac = _ac;
	}
}

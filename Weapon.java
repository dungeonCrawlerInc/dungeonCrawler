public class Weapon extends Item {
    private int _damage;

    public Weapon(String name, int goldValue, int weight, int damage) {
        this._name = name;
        this._goldValue = goldValue;
        this._weight = weight;
        this._damage = damage;
    }

    public int getDamage() {
		return _damage;
	}

	public void setDamage(int damage) {
		this._damage = damage;
	}
}

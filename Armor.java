public class Armor {
        
    // armor class fields
  private String name;
    private int ac, goldValue;
    
    
	// the armor class has
    // one constructor
    public Armor(String name, int ac, int goldValue) {
        this.name = name;
        this.ac = ac;
        this.goldValue = goldValue;
    }


    //Methods
    public String getName() {
		return this.name;
	}
    
    
    public int getAc() {
		return ac;
	}


	public void setAc(int ac) {
		this.ac = ac;
	}


	public int getValue() {
		return goldValue;
	}


	public void setValue(int goldValue) {
		this.goldValue = goldValue;
	}
}

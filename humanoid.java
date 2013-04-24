public class humanoid {
        
    // humanoid class fields
  private String name;
    private int str, dex, con, intel, wis, cha, gold, locX, locY;
    private weapon rightHand, leftHand; 
    private armor head, chest, legs, feet, hands, ring1, ring2, necklace;
    
        
    // the humanoid class has
    // one constructor
    public humanoid(String name) {
        this.name = name;
        
        //10 being average
        this.str = 10;
        this.dex = 10;
        this.con = 10;
        this.intel = 10;
        this.wis = 10;
        this.cha = 10;
        
        this.gold = 0;
        
        
    }


    //Methods
    public String getName() {
		return this.name;
	}
    
    
	public int getStr() {
		return str;
	}


	public void setStr(int str) {
		this.str = str;
	}


	public int getDex() {
		return dex;
	}


	public void setDex(int dex) {
		this.dex = dex;
	}


	public int getCon() {
		return con;
	}


	public void setCon(int con) {
		this.con = con;
	}


	public int getIntel() {
		return intel;
	}


	public void setIntel(int intel) {
		this.intel = intel;
	}


	public int getWis() {
		return wis;
	}


	public void setWis(int wis) {
		this.wis = wis;
	}


	public int getCha() {
		return cha;
	}


	public void setCha(int cha) {
		this.cha = cha;
	}


	public int getGold() {
		return gold;
	}

	
	//Hard sets this humanoid's gold
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	
	//Soft sets (aka increases or decreases)
	public void changeGold(int offset){
		this.gold += gold;
	}
}

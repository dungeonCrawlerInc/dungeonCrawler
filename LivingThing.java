/*
    * TODO List
        * Add inventory
 */

public class LivingThing {
    
  protected String name; //Default no name
  protected int str, dex, con, intel, wis, cha, gold, locX, locY;
    
        
    // one constructor
    public LivingThing() {   //I removed the parameter "String name", so things that extend this can utilize it
        this.name = "";

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

	public void setGold(int gold) {
		this.gold = gold;
    }
}

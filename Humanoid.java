//A Humanoid is a living thing that walks on two lags and has two arms.

public class Humanoid extends LivingThing{
        
    // humanoid class fields
    private Weapon rightHand, leftHand; 
    private Armor head, chest, legs, feet, hands, ring1, ring2, necklace;

    public Humanoid() {
        this.name = name = "";

        //10 being average
        this.str = 10;
        this.dex = 10;
        this.con = 10;
        this.intel = 10;
        this.wis = 10;
        this.cha = 10;

        this.gold = 0;
    }

    public Humanoid(String name) {
        this.name = name = name;
        
        //10 being average
        this.str = 10;
        this.dex = 10;
        this.con = 10;
        this.intel = 10;
        this.wis = 10;
        this.cha = 10;
        
        this.gold = 0;
    }


}

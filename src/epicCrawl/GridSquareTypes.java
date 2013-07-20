package epicCrawl;

public class GridSquareTypes{
   public static GridSquare STONEWALL, DOOR, VOID, CHARACTER, TABLE, GIRL, CHEST, LCHAIR, 
   		RCHAIR, ENEMY, WOODFLOORLIGHT, DIRTFLOOR, GRASSFLOOR, WOODFLOORDARK, TABLEFOOD; // Accesible by other classes/files

   public GridSquareTypes(){ 
      CHARACTER = new GridSquare("character", false); // Constructor takes an ASCI char and a color.
      STONEWALL = new GridSquare("stoneWall", false);
      DOOR = new GridSquare("door", true);
      WOODFLOORLIGHT = new GridSquare("woodFloorLight", true); // Blank walkable space on grid
      VOID = new GridSquare("void",false); // Unused surrounding grid space
      TABLE = new GridSquare("table", false);
      GIRL = new GridSquare("girl", false);
      CHEST = new GridSquare("chest", false);
      LCHAIR = new GridSquare("leftchair", false);
      RCHAIR = new GridSquare("rightchair", false);
      ENEMY = new GridSquare("enemy", false);
      DIRTFLOOR = new GridSquare("dirtFloor", true);
      GRASSFLOOR = new GridSquare("grassFloor", true);
      WOODFLOORDARK = new GridSquare("woodFloorDark", true);
      TABLEFOOD = new GridSquare("tableFood", false);
      
      // To add a new GridChar, give it a name and call constructor with desired ASCI char and color
      // Note: Update README file with new GridChar.  Also have to add GridChar name to public static list above
   }
}
package epicCrawl;

public class GridSquareTypes{
   public static GridSquare WALL, DOOR, WOODFLOOR, VOID, CHARACTER, TABLE, GIRL, CHEST, LCHAIR, RCHAIR, ENEMY; // Accesible by other classes/files

   public GridSquareTypes(){ 
      CHARACTER = new GridSquare("character", false); // Constructor takes an ASCI char and a color.
      WALL = new GridSquare("wall", false);
      DOOR = new GridSquare("door", true);
      WOODFLOOR = new GridSquare("woodfloor", true); // Blank walkable space on grid
      VOID = new GridSquare("void",false); // Unused surrounding grid space
      TABLE = new GridSquare("table", false);
      GIRL = new GridSquare("girl", false);
      CHEST = new GridSquare("chest", false);
      LCHAIR = new GridSquare("leftchair", false);
      RCHAIR = new GridSquare("rightchair", false);
      ENEMY = new GridSquare("enemy", false);
      
      // To add a new GridChar, give it a name and call constructor with desired ASCI char and color
      // Note: Update README file with new GridChar.  Also have to add GridChar name to public static list above
   }
}
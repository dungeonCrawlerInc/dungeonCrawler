dungeonCrawlerInc.
dungeonCrawler

For fun project by Gregory Hilston and Andrew Boutin. 

TO DO:
    
    Items/Inventory/Character Stats
        Set up inventory slots
        Set up item panels
       
    Combat Dialogue Area
        Write methods to display out messages and cycle through messages correctly
    
    Game
        (IMPORTANT) Need to develope game loop for moving all movable objects on grid
        Create enemy path finding towards character
        Give doors information that will allow them to load new levels
    
    LEVEL 
        Add in line method (Draw a line and any grid square the line touches will get filled in)
    
Minor Fixes Needed:


    Figure out what to do with LevelsList.txt and CharacterNames.txt

Tasks:
    
    Greg
        Character Designer
            New Feature - Add list of skills
            New Feature - Add race bonuses
            New Feature - Add race picture
            * check for already existing .char file and prompt for overwrite or cancel
            * close Character Designer on creation (somehow pass the newly created character name out)
            
        Living Object
            * Consistentantly update, while I create, edit and remove features from a character
            
        Hero class (Does not exist yet) [Will be our playing character, AKA PC)
            * Will extend Living Object
        
        Character Loader (Does Not Exist Yet)
            * Will create an instance of the  Hero class
            * Will load all the values from the .char file and put them into the Hero clas
            
        Non-Playing Character (Does not exist yet) [All the characters that have a proper name)
            *Will extend Living Object
            
        Level Designer
            New Feature - Fill check box
            New Feature - Different shaped brushes
            Edit Feature - Change "Increase Brush Size" button to a combo box


    Andrew
        Start to set up areas to display items being worn.
        Make basic method for enemies to attack player if next to player.
        Display message in dialogue area when attacking or being attacked.
        Fix bugs in enemy path finding.

Development:
  
    Eclipse Java EE IDE for Web Developers.
      Version: Juno Service Release 1
      Build id: 20120920-0800
        http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr1
    jformdesigner (http://www.formdev.com/)
    github (http://windows.github.com/)
    Image Creation (http://pyxeledit.com/)
    Website (http://www.EpicCrawl.com)

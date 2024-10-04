package dtcc.itn262.character;

import static dtcc.itn262.gameutilities.Validation.validateName;

public class Player {
    private final String hero;
    private final PlayerAttributes playerAttributes;
    private int playerRow;
    private int playerCol;


    public Player(String hero, int startRow, int startCol) {
        this.hero = hero;
        playerAttributes = new PlayerAttributes();
        this.playerRow = startRow;
        this.playerCol = startCol;
    }


    // Factory method to create a Player with validation
    public static Player createPlayer(String name, int startRow, int startCol) {
        String validatedName = validateName(name);
        return new Player(validatedName, startRow, startCol);
    }


    public int getPlayerRow() {
        return playerRow;
    }


 /*   public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }*/ // keep I can set up random entry once a player object is created


    // Getter and Setter for playerCol
    public int getPlayerCol() {
        return playerCol;
    }


/*    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }*/ // keep I can set up random entry once a player object is created


    // Method to move the player to a new position
    public void moveTo(int newRow, int newCol) {
        this.playerRow = newRow;
        this.playerCol = newCol;
    }


    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    } // returns the player attributes in PlayerAttributes class


    public String getHero() {
        return hero;
    }


/*    public void setHero(String hero) {
        this.hero = hero;
    }*/  // keep give players option later to change their same


    @Override
    public String toString() {
        return "Hero: " + hero +
                "\nStrength: " + playerAttributes.getStrength() +
                "\nHealth: " + playerAttributes.getHealth() +
                "\nMana: " + playerAttributes.getMana() +
                "\nLevel: " + playerAttributes.getLevel() +
                "\nDefense: " + playerAttributes.getDefense() +
                "\nMagic: " + playerAttributes.getMagic() +
                "\nMagic Defense: " + playerAttributes.getMagicDefense() +
                "\nSpeed: " + playerAttributes.getSpeed() +
                "\nLuck: " + playerAttributes.getLuck() +
                "\nExperience: " + playerAttributes.getExperience();
    }

    public boolean isAlive() {
        return playerAttributes.getHealth() > 0;
    }
}

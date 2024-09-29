package dtcc.itn262.character;

import static dtcc.itn262.gameutilities.Validation.validateName;

public class Player {
    private String hero;
    private PlayerAttributes playerAttributes;

    public Player(String hero) {
        this.hero = hero;
        playerAttributes = new PlayerAttributes();
    }

    // Factory method to create a Player with validation
    public static Player createPlayer(String name) {
        String validatedName = validateName(name);
        return new Player(validatedName);
    }

    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    } // returns the player attributes in PlayerAttributes class

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

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


}

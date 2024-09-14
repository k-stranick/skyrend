package dtcc.itn262.character;

import java.util.Random;


public class Player {
    private Random rand = new Random();
    private String hero;
    private PlayerAttributes playerAttributes;

    public Player(String name) {
        hero = !name.isEmpty() ? name : "Hero";
        playerAttributes = new PlayerAttributes();
    }


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

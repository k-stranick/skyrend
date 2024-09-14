package dtcc.itn262.character;

import java.util.Random;

public class PlayerAttributes {
    Random rand = new Random();

    private int level;
    private int strength;  // governs attack power
    private int defense;  // governs attack resistance
    private int health; // governs life
    private int magic;  // governs magic attack power
    private int magicDefense;  // governs magic attack resistance
    private int mana;  // governs magic pool
    private int speed; // governs how many times a player can attack before the monster attacks
    private int luck; // governs critical hit chance
    private int experience; // governs level


    public PlayerAttributes() {
        magic = randStat();
        magicDefense = randStat();
        strength = randStat();
        defense = randStat();
        magic = randStat();
        speed = randStat();
        luck = randStat();
        experience = 0; // base experience
        health = 100;  // base health
        mana = 100; // base mana
        level = 1; // base level
    }

    private int randStat() {
        //TODO add a range for stats
        //TODO make equations constants to avoid magic numbers and allow for easier balancing
        return rand.nextInt(10) + 8; // generates a random int between 10 and 18
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }
    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLuck() {
        return luck;
    }
    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
package dtcc.itn262.monster;

import java.util.Random;

public class MonsterAttributes {
    Random rand = new Random();
    private int health;
    private int strength;
    private int defense;
    private int magic;
    private int magicDefense;
    private int speed;
    private int luck;
    private int experience;
    private int gold;
    private int mana;
    private String type;
    private String description;

    public MonsterAttributes() {
        health = randStat();
        strength = randStat();
        defense = randStat();
        magic = randStat();
        magicDefense = randStat();
        speed = randStat();
        luck = randStat();
        experience = 0;
        gold = 0;
        mana = 0;
        type = "Monster";
        description = "A monster";
    }

    private int randStat() {
        //TODO add a range for stats
        //TODO make equations constants to avoid magic numbers and allow for easier balancing
        return rand.nextInt(10) + 8; // generates a random int between 10 and 18
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


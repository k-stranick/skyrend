package dtcc.itn262.monster;

import java.util.Random;

public class MonsterAttributes {
    static Random rand = new Random();
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


    // Custom constructor for specific monsters or bosses
    public MonsterAttributes(MonsterAttributesBuilder builder) {
        this.health = builder.health;
        this.strength = builder.strength;
        this.defense = builder.defense;
        this.magic = builder.magic;
        this.magicDefense = builder.magicDefense;
        this.speed = builder.speed;
        this.luck = builder.luck;
        this.mana = builder.mana;
        this.type = builder.type;
        this.description = builder.description;
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

    public static class MonsterAttributesBuilder { // for generic monsters
        private int health = 100;
       private int strength = randStat();
        private int defense = randStat();
        private int magic = randStat();
        private int magicDefense = randStat();
        private int speed = randStat();
        private int luck = randStat();
        private int experience = 0;
        private int gold = 0;
        private int mana = 100;
        private String type = "Monster";
        private String description = "A monster";

        private int randStat() {
            //TODO add a range for stats
            //TODO make equations constants to avoid magic numbers and allow for easier balancing
            return rand.nextInt(10) + 8; // generates a random int between 10 and 18
        }
        public MonsterAttributesBuilder withHealth(int health) {
            this.health = health;
            return this;
        }

        public MonsterAttributesBuilder withStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public MonsterAttributesBuilder withDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public MonsterAttributesBuilder withMagic(int magic) {
            this.magic = magic;
            return this;
        }

        public MonsterAttributesBuilder withMagicDefense(int magicDefense) {
            this.magicDefense = magicDefense;
            return this;
        }

        public MonsterAttributesBuilder withSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public MonsterAttributesBuilder withLuck(int luck) {
            this.luck = luck;
            return this;
        }

        public MonsterAttributesBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public MonsterAttributesBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        // Build method to return a MonsterAttributes instance
        public MonsterAttributes build() {
            return new MonsterAttributes(this);
        }
    }
}


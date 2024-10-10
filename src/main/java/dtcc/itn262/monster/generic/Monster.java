package dtcc.itn262.monster.generic;

import dtcc.itn262.monster.MonsterAttributes;

public class Monster {
    private String monster;
    private final MonsterAttributes monsterAttributes;


    public Monster(String monster, MonsterAttributes attributes) {
        this.monster = monster;
        this.monsterAttributes = attributes;
    }

    public String getMonster() {
        return monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public MonsterAttributes getMonsterAttributes() {
        return monsterAttributes;
    }

    @Override
    public String toString() {
        return "Monster: " + monster + "\nStrength: " + monsterAttributes.getStrength()
                + "\nHealth: " + monsterAttributes.getHealth()
                + "\nMana: " + monsterAttributes.getMana()
                + "\nDefense: " + monsterAttributes.getDefense()
                + "\nSpeed: " + monsterAttributes.getSpeed()
                + "\nLuck: " + monsterAttributes.getLuck()
                + "\nExperience: " + monsterAttributes.getExperience()
                + "\nGold: " + monsterAttributes.getGold()
                + "\nMagic: " + monsterAttributes.getMagic()
                + "\nMagic Defense: " + monsterAttributes.getMagicDefense()
                + "\nType: " + monsterAttributes.getType()
                + "\nDescription: " + monsterAttributes.getDescription();


    }
    public boolean isAlive() {
        return monsterAttributes.getHealth() > 0;
    }

}

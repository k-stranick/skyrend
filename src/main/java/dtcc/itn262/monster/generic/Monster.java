package dtcc.itn262.monster.generic;

import dtcc.itn262.monster.MonsterAttributes;

public class Monster {
    private String enemy;
    private final MonsterAttributes monsterAttributes;


    public Monster(String enemy) {
        this.enemy = enemy;
        monsterAttributes = new MonsterAttributes();
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public MonsterAttributes getMonsterAttributes() {
        return monsterAttributes;
    }

    @Override
    public String toString() {
        return "Monster: " + enemy +
                "\nStrength: " + monsterAttributes.getStrength() +
                "\nHealth: " + monsterAttributes.getHealth() +
                "\nMana: " + monsterAttributes.getMana() +
                "\nDefense: " + monsterAttributes.getDefense()
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

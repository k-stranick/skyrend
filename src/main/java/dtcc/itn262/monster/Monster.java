package dtcc.itn262.monster;

public class Monster {
    private String monster;
    private MonsterAttributes monsterAttributes;


    public Monster(String monster) {
        this.monster = monster;
        monsterAttributes = new MonsterAttributes();
    }

    public String getMonster() {
        return monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        return "Monster: " + monster +
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


}

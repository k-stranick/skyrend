package dtcc.itn262.monster;
// TODO Turn this into an interface and allow generic monsters and bosses implement it

import dtcc.itn262.skills.monsterskills.IMonsterSkill;
import java.util.ArrayList;
import java.util.List;

public class Monster {
    private String monster;
    private final MonsterAttributes monsterAttributes;
    private final List<IMonsterSkill> monsterSkills;

    public Monster(String monster, MonsterAttributes attributes) {
        this.monster = monster;
        this.monsterAttributes = attributes;
        this.monsterSkills = new ArrayList<>();

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

    public boolean isAlive() {
        return monsterAttributes.getActiveHealth() > 0;
    }

    public List<IMonsterSkill> getMonsterSkills() {
        return monsterSkills;
    }

    public void addSkill(IMonsterSkill skill) {   // Method to add skills to the actions

        monsterSkills.add(skill);
    }


    @Override
    public String toString() {
        return "Monster: " + monster
                + "\nExperience: " + monsterAttributes.getExperience()
                + "\nStrength: " + monsterAttributes.getStrength()
                + "\nHealth: " + monsterAttributes.getActiveHealth()
                + "\nMana: " + monsterAttributes.getMana()
                + "\nDefense: " + monsterAttributes.getDefense()
                + "\nSpeed: " + monsterAttributes.getSpeed()
                + "\nLuck: " + monsterAttributes.getLuck()
                + "\nGold: " + monsterAttributes.getGold()
                + "\nMagic: " + monsterAttributes.getMagic()
                + "\nMagic Defense: " + monsterAttributes.getMagicDefense()
                + "\nType: " + monsterAttributes.getType()
                + "\nDescription: " + monsterAttributes.getDescription();


    }
}

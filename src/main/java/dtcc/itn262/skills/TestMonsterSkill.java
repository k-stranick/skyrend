package dtcc.itn262.skills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public class TestMonsterSkill implements MonsterSkill {

    private String skillName = "Test Skill";
    private double damageMultiplier = 1.5;
    private int manaCost = 33;
    private int cooldown = 3;  // cooldown in turns
    private int currentCooldown = 0;

    /**
     * @return skillName the name of the skill
     */
    @Override
    public String getSkillName() {
        return skillName;
    }

    /**
     * @return manaCost the cost of the skill
     */
    @Override
    public int getManaCost() {
        return manaCost;
    }

    /**
     * @return
     */
    @Override
    public boolean isOnCooldown() {
        return currentCooldown > 0;
    }

    /**
     *
     */
    @Override
    public void reduceCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }

    }

    /**
     *
     */
    @Override
    public void setCooldown() {
        currentCooldown = cooldown;

    }

    /**
     * @param monster
     * @param target
     */
    @Override
    public void useSkill(Monster monster, Player target) {
        double damage = (monster.getMonsterAttributes().getMagic() * damageMultiplier) - target.getPlayerAttributes().getMagicDefense();
        if (isOnCooldown()) {
            System.out.println(getSkillName() + "is on cooldown");

        } else if (monster.getMonsterAttributes().getMana() < getManaCost()) {
            System.out.println("Not enough mana to use " + getSkillName());

        } else if (monster.getMonsterAttributes().getMana() >= manaCost && damage > 0) {
            monster.getMonsterAttributes().setMana(monster.getMonsterAttributes().getMana() - manaCost);
            target.getPlayerAttributes().setHealth((int) Math.round(target.getPlayerAttributes().getHealth() - damage));
            setCooldown();
        }
    }
}

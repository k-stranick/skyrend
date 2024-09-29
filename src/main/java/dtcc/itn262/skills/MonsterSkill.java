package dtcc.itn262.skills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public interface MonsterSkill {
    public String getSkillName();

    public int getManaCost();

    public boolean isOnCooldown();

    //public void reduceCooldown();

    public void setCooldown();

    void useSkill(Monster monster, Player target); // monster targeting player with skills
}

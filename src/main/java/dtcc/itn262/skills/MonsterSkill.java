package dtcc.itn262.skills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public interface MonsterSkill {
    String getSkillName();

    int getManaCost();

    boolean isOnCooldown();

    //public void reduceCooldown();

    void setCooldown();

    void useSkill(Monster monster, Player target); // monster targeting player with skills
}

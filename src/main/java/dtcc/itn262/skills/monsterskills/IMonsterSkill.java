package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public interface IMonsterSkill {
	String getSkillName();

	int getManaCost();

	void useSkill(Monster monster, Player target); // monster targeting player with skills


}

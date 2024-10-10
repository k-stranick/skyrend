package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.combat.effects.DefenseBuff;
import dtcc.itn262.monster.generic.Monster;
import dtcc.itn262.skills.playerskills.PlayerSkill;
import dtcc.itn262.skills.playerskills.DivineStrike;
import dtcc.itn262.skills.playerskills.PulseBlade;
import dtcc.itn262.utilities.gamecore.Constants;
import java.util.ArrayList;
import java.util.Random;

public class PlayerActions {
	protected ArrayList<PlayerSkill> skills = new ArrayList<>();
	CombatLogic combatLogic;
	Player player;
	Random rand = new Random();


	public PlayerActions(CombatLogic combatLogic, Player player) { // adding skills to the player
		skills.add(new DivineStrike());
		skills.add(new PulseBlade());
		this.combatLogic = combatLogic;
		this.player = player;
	}


	protected void attack(Player player, Monster target) {
		int damage = (player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense());
		if (damage > 0) {
			target.getMonsterAttributes().setHealth(target.getMonsterAttributes().getHealth() - damage);
			System.out.println(player.getHero() + " attacks " + target.getMonster() + " for " + damage + " damage.");
		} else {
			System.out.println(player.getHero() + " attacks " + target.getMonster() + " but the attack is ineffective.");
		}
	}


	protected void defend(Player player) {
		int buffDuration = 1; // buff lasts for 1 turn
		BuffAndDeBuff<PlayerAttributes> defenseBuff = new DefenseBuff(buffDuration, Constants.DEFENSE_BUFF);
		defenseBuff.apply(player.getPlayerAttributes());
		System.out.println(player.getHero() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration  + " turns.");
		combatLogic.activeBuffs.add(defenseBuff);
	}


	protected void showEnemyStats(Monster target) {
		System.out.println("Enemy: " + target.getMonster() +
				"\nDescription: " + target.getMonsterAttributes().getDescription() +
				"\nHealth: " + target.getMonsterAttributes().getHealth() +
				"\nStrength: " + target.getMonsterAttributes().getStrength() +
				"\nMana: " + target.getMonsterAttributes().getMana() +
				"\nDefense: " + target.getMonsterAttributes().getDefense() +
				"\nMagic: " + target.getMonsterAttributes().getMagic() +
				"\nMagic Defense: " + target.getMonsterAttributes().getMagicDefense() +
				"\nSpeed: " + target.getMonsterAttributes().getSpeed() +
				"\nLuck: " + target.getMonsterAttributes().getLuck());
	}


	protected void useSkill(Player player, Monster target, int skillIndex) {
		try {
			if (skillIndex >= 0 && skillIndex < skills.size()) {
				PlayerSkill skill = skills.get(skillIndex);
				if (!skill.isOnCooldown()) {
					skill.useSkill(player, target);

				} else {
					System.out.println("Cannot use " + skill.getSkillName() + " for " + skill.getCurrentCooldown() + " more turns.");
				}
			} else {
				System.out.println("Invalid skill index.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("An error occurred while using the skill: " + e.getMessage());
		}
	}


	protected void showSkills() {  // need to return a skill list in human-readable format
		for (int i = 0; i < skills.size(); i++) {
			System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
		}
	}


	protected PlayerSkill getSkill(int index) {
		if (index >= 0 && index < skills.size()) {
			return skills.get(index);
		} else {
			throw new IndexOutOfBoundsException("Invalid skill index: " + index);
		}
	}


	protected boolean run(Player player) {
		int luck = player.getPlayerAttributes().getLuck();  // Use player's luck to influence run success
		int chanceToRun = rand.nextInt(100);  // Generate a random number between 0 and 99
		if ((chanceToRun + luck) > 50) {  // Add player's luck to the chance and compare
			return true;
		} else {
			System.out.println("You failed to run away!");
			return false;
		}
	}
}

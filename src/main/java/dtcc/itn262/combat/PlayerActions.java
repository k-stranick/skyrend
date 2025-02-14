package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.combat.effects.DefenseBuff;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.playerskills.DivineStrike;
import dtcc.itn262.skills.playerskills.Heal;
import dtcc.itn262.skills.playerskills.PlayerSkill;
import dtcc.itn262.skills.playerskills.PulseBlade;
import dtcc.itn262.utilities.gamecore.Constants;

import java.util.ArrayList;
import java.util.Random;

public class PlayerActions {
	private final ArrayList<PlayerSkill> skills = new ArrayList<>();
	CombatLogic combatLogic;
	Random rand = new Random();
	private Player player;

	public PlayerActions(CombatLogic combatLogic, Player player) { // adding skills to the player
		this.combatLogic = combatLogic;
		this.player = player;
		initializeSkills();
	}

	// Overloaded constructor for non-combat
	public PlayerActions(Player player) {
		this.player = player;
		initializeSkills();
	}

	private void initializeSkills() {
		skills.add(new DivineStrike());
		skills.add(new PulseBlade());
		skills.add(new Heal());
	}

	public void useItem(HealingItems item) {
		if (player.getPlayerItemsList().contains(item)) {
			System.out.println("Used " + item.getName() + ".");
			item.apply(player); // Apply the item's effect
			player.getPlayerItemsList().remove(item); // Remove the item after use
		} else {
			System.out.println("You do not have " + item.getName() + " in your inventory.");
		}
	}

	public void handleWeaponSwap(Weapon weapon) {
		if (player.getPlayerWeaponList().contains(weapon)) {
			player.equippedWeapon(weapon);
		} else {
			System.out.println("You do not have " + weapon.getName() + " in your inventory.");
		}
	}

	public void handleArmorSwap(Armor armor) {
		if (player.getPlayerArmorList().contains(armor)) {
			player.equippedArmor(armor);
		} else {
			System.out.println("You do not have " + armor.getName() + " in your inventory.");
		}
	}

	protected void attack(Player player, Monster target) {
		int damage = (player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense());
		if (damage > 0) {
			target.getMonsterAttributes().setActiveHealth(target.getMonsterAttributes().getActiveHealth() - damage);
			System.out.println(player.getHeroName() + " attacks " + target.getMonster() + " for " + damage + " damage.");
		} else {
			System.out.println(player.getHeroName() + " attacks " + target.getMonster() + " but the attack is ineffective.");
		}
	}

	protected void defend(Player player) {
		int buffDuration = 1; // buff lasts for 1 turn
		DefenseBuff<PlayerAttributes> playerDefenseBuff = new DefenseBuff<>(buffDuration, Constants.DEFENSE_BUFF);
		playerDefenseBuff.apply(player.getPlayerAttributes());
		System.out.println(player.getHeroName() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration + " turns. Defense increased to " + player.getPlayerAttributes().getDefense());
		combatLogic.activePlayerBuffs.add(playerDefenseBuff);
	}

	protected void scanEnemy(Monster target) {
		System.out.println("Enemy: " + target.getMonster() +
				"\nLevel: " + target.getMonsterAttributes().getLevel() +
				"\nDescription: " + target.getMonsterAttributes().getDescription() +
				"\nHealth: " + target.getMonsterAttributes().getActiveHealth() +
				"\nStrength: " + target.getMonsterAttributes().getStrength() +
				"\nMana: " + target.getMonsterAttributes().getMana() +
				"\nDefense: " + target.getMonsterAttributes().getDefense() +
				"\nMagic: " + target.getMonsterAttributes().getMagic() +
				"\nMagic Defense: " + target.getMonsterAttributes().getMagicDefense() +
				"\nSpeed: " + target.getMonsterAttributes().getSpeed() +
				"\nLuck: " + target.getMonsterAttributes().getLuck());
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

	protected void useSkill(Player player, Monster target, int skillIndex) {
		try {
			if (skillIndex <= 0 && skillIndex >= skills.size()) {
				System.out.println("Invalid skill index.");

				return;
			}
			PlayerSkill skill = skills.get(skillIndex);
			if (!skill.isOnCooldown()) {
				if (skill.isHealingSkill()) {
					skill.useSKill(player);
				} else {
					skill.useSkill(player, target);
				}
			} else {
				System.out.println("Cannot use " + skill.getSkillName() + " for " + skill.getCurrentCooldown() + " more turns.");
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

	public void showPlayerStats() {
		System.out.println(player);
	}
}

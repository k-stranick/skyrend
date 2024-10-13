package dtcc.itn262.monster.genericmonsters;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.monsterskills.Slash;

public class ArcTechSoldier extends Monster {
	public ArcTechSoldier() {
		super("ArcTech Soldier", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(150)
				.withMaxHealth(150)
				.withStrength(50)
				.withMana(50)
				.withMaxMana(50)
				.withDefense(15)
				.withSpeed(12)
				.withMagic(5)
				.withMagicDefense(8)
				.withLuck(10)
				.withType("Cybernetic Enforcer")
				.withDescription("Cybernetic enforcers patrolling the streets of Skyrend, ensuring the Council's laws are upheld. They are heavily armed and dangerous, following strict orders to maintain control over the populace.")
				.build());

		// Skills for the enemy
		addSkill(new Slash());
	}
}

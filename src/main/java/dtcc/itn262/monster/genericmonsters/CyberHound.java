package dtcc.itn262.monster.genericmonsters;

import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.monsterskills.Slash;

public class CyberHound extends Monster {
	public CyberHound() {
		super("Cyber Hound", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(100)
				.withMaxHealth(100)
				.withStrength(45)
				.withMana(45)
				.withMaxMana(45)
				.withDefense(10)
				.withSpeed(20)
				.withMagic(0)
				.withMagicDefense(5)
				.withLuck(15)
				.withType("Cybernetic Beast")
				.withDescription("A mechanical beast that roams the streets of Skyrend.")
				.build());

		// Define unique abilities here like Pounce, Bite, etc.
		addSkill(new Slash());
		//addSkill(new Heal());
	}

	// Define unique abilities here like Pounce, Bite, etc.
}

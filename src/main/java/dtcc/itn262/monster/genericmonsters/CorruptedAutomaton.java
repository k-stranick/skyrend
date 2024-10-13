package dtcc.itn262.monster.genericmonsters;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.monsterskills.DataDrain;
import dtcc.itn262.skills.monsterskills.Slash;
import dtcc.itn262.skills.monsterskills.SelfDestruct;

public class CorruptedAutomaton extends Monster {
	public CorruptedAutomaton() {
		super("Corrupted Automaton", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(200)
				.withMaxHealth(200)
				.withMaxMana(100)
				.withMana(100)
				.withStrength(50)
				.withDefense(20)
				.withSpeed(10)
				.withMagic(5)
				.withMagicDefense(10)
				.withLuck(5)
				.withType("Corrupted Automaton")
				.withDescription("Machines once built to serve Skyrend but now corrupted by the Ghost Code. They act unpredictably, often attacking everything in sight.")
				.build());

		//Skills for the enemy
		addSkill(new SelfDestruct());
		//addSkill(new GlitchSwipe());
		addSkill(new DataDrain());
		addSkill(new Slash());

	}

	// Define unique abilities here like Overclock, Self-Destruct, etc.
}

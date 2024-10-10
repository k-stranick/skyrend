package dtcc.itn262.monster.generic;

import dtcc.itn262.monster.MonsterAttributes;

public class CorruptedAutomaton extends Monster {
	public CorruptedAutomaton() {
		super("Corrupted Automaton", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(200)
				.withMaxHealth(200)
				.withMaxMana(100)
				.withMana(100)
				.withStrength(25)
				.withDefense(20)
				.withSpeed(10)
				.withMagic(5)
				.withMagicDefense(10)
				.withLuck(5)
				.withType("Corrupted Automaton")
				.withDescription("Machines once built to serve Skyrend but now corrupted by the Ghost Code. They act unpredictably, often attacking everything in sight.")
				.build());
	}

	// Define unique abilities here like Overclock, Self-Destruct, etc.
}

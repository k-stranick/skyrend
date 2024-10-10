package dtcc.itn262.monster.generic;

import dtcc.itn262.monster.MonsterAttributes;

public class SteelDevourer extends Monster {
	public SteelDevourer() {
		super("Steel Devourer", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(300)
				.withMaxHealth(300)
				.withMana(50)
				.withMaxMana(50)
				.withStrength(30)
				.withDefense(25)
				.withSpeed(5)
				.withMagic(10)
				.withMagicDefense(15)
				.withLuck(5)
				.withType("Mechanical Beast")
				.withDescription("A massive, corrupted machine created to dismantle buildings but now turned against the people. Its enormous size and power make it a devastating force in battle.")
				.build());
	}

	// Define unique abilities here like Devour, Metal Claw, etc.
}

package dtcc.itn262.monster.boss;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.generic.Monster;

public class Gilgamesh extends Monster {
	public Gilgamesh() {
		super("G1lg@mesh", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(700)
				.withMaxHealth(700)
				.withMaxMana(150)
				.withMana(150)
				.withStrength(60)
				.withDefense(40)
				.withSpeed(30)
				.withMagic(15)
				.withMagicDefense(30)
				.withLuck(25)
				.withType("Gilgamesh")
				.withDescription("G1lg@mesh (often referred to as Gilgamesh) is a powerful, corrupted entity born from the fusion of ancient technology and Ghost Code. Once a revered protector of the city’s AetherGrid, G1lg@mesh has been tainted by the chaotic energies of the Ghost Code, causing it to transform into a being of immense destructive force. Its body is a towering amalgamation of sleek metal and glowing circuitry, crackling with unstable Aether energy. It now seeks to dominate both the physical and digital realms, making it a formidable final boss.")
				.build());
	}

	// Define unique abilities here like Overclock, Self-Destruct, etc.
}


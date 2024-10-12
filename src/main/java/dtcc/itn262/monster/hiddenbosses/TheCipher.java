package dtcc.itn262.monster.hiddenbosses;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.generic.Monster;

public class TheCipher extends Monster {
	public TheCipher() {
		super("The Cipher", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(1000)
				.withMaxHealth(1000)
				.withMaxMana(500)
				.withMana(500)
				.withStrength(80)
				.withDefense(70)
				.withSpeed(40)
				.withMagic(100)
				.withMagicDefense(90)
				.withLuck(20)
				.withType("The Cipher")
				.withDescription("The Cipher is a rogue fragment of the Ghost Code that has evolved into a self-aware entity, existing in both the physical and digital realms of Skyrend. Its form constantly shifts between solid matter and raw code, warping reality and causing chaos wherever it manifests.\\n" +
						"\\n" +
						"Once a mere anomaly in the AetherGrid, The Cipher now controls entire sectors of the Ghost Code, manipulating both magic and technology to destabilize Skyrend. It seeks to unravel the carefully maintained order of the AetherGrid and plunge the city into digital chaos. Encountering The Cipher means facing a being that can rewrite reality, disrupt spells, and erase code, leaving behind nothing but entropy.")
				.build());
	}
	// Define unique abilities here like Rewrite Reality, Aether Torrent, Code Erasure, etc.
}

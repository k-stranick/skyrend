package dtcc.itn262.monster.hiddenbosses;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.genericmonsters.Monster;
import dtcc.itn262.skills.monsterskills.*;

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
		//addSkill("Rewrite Reality", "The Cipher warps reality, causing all attacks to miss for 1 turn.");
		//addSkill("Aether Torrent", "The Cipher unleashes a torrent of Aether energy, dealing massive damage to all enemies.");
		//addSkill("Code Erasure", "The Cipher erases a portion of the target's code, dealing damage and reducing their stats.");
		addSkill(new PhantomCode());
		addSkill(new MemoryLeak());
		addSkill(new DataDrain());
		addSkill(new Slash());
		addSkill(new Zantetsuken());
	}
	// Define unique abilities here like Rewrite Reality, Aether Torrent, Code Erasure, etc.
}

package dtcc.itn262.monster.boss;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.generic.Monster;

public class GhostCodeManifest extends Monster {
	public GhostCodeManifest() {
		super("Ghost Code Manifest", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(500)
				.withStrength(40)
				.withDefense(35)
				.withSpeed(45)
				.withMagic(60)
				.withMagicDefense(50)
				.withLuck(30)
				.withType("Ghost Code Manifest")
				.withDescription("The Ghost Code Manifest is the embodiment of the chaotic, glitching energy that has disrupted Skyrend's AetherGrid. It is a swirling mass of corrupted data, constantly shifting and morphing, with tendrils of fragmented code flickering in and out of existence. Its form is unstable, often appearing as a distortion in reality itself, with areas around it warping and bending unpredictably.")
				.build());
	}

	// Define unique abilities here like Data Drain, Code Corruption, etc.
}

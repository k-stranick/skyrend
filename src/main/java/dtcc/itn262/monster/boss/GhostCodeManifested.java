package dtcc.itn262.monster.boss;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.monsterskills.*;

public class GhostCodeManifested extends Monster {
	public GhostCodeManifested() {
		super("Ghost Code Manifested", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(500)
				.withMaxHealth(500)
				.withMana(300)
				.withMaxMana(300)
				.withStrength(40)
				.withDefense(35)
				.withSpeed(45)
				.withMagic(60)
				.withMagicDefense(50)
				.withLuck(30)
				.withType("Ghost Code")
				.withDescription("The Ghost Code Manifested is the embodiment of the chaotic, glitching energy that has disrupted Skyrend's AetherGrid. It is a swirling mass of corrupted data, constantly shifting and morphing, with tendrils of fragmented code flickering in and out of existence. Its form is unstable, often appearing as a distortion in reality itself, with areas around it warping and bending unpredictably.")
				.build());
		// Define unique abilities here like Corrupt, DataDrain, etc.
		addSkill(new Slash());
		addSkill(new DataDrain());
		addSkill(new MemoryLeak());
		addSkill(new CoreDisruption());
		addSkill(new PhantomCode());
		addSkill(new QuantumDistortion());
//addSkill(new Overclock());
//addSkill(new Corrupt());

	}

}

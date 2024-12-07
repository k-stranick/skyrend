package dtcc.itn262.monster.boss;

import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.monsterskills.*;

public class TheArchitect extends Monster {
	public TheArchitect(int playerLevel) {
		super("The Architect", new MonsterAttributes.MonsterAttributesBuilder()
				.withHealth(600)
				.withMaxHealth(600)
				.withMaxMana(250)
				.withMana(250)
				.withStrength(40)
				.withDefense(55)
				.withSpeed(25)
				.withMagic(70)
				.withMagicDefense(60)
				.withLuck(15)
				.withType("The Architect")
				.withDescription("The Architect is the entity responsible for creating and unleashing the Ghost Code, causing the corruption of Skyrend and manipulating the AetherGrid for his own purposes. He embodies the combination of magic and technology, being more like a god-like figure who crafted the city’s systems to suit his needs. Clad in flowing digital robes with a glitchy aura of magic and data swirling around him, The Architect is cold, calculating, and always five steps ahead of the player.\n" +
						"\n" +
						"He doesn’t just want control; he craves perfection through order and the elimination of those who oppose his vision for Skyrend. While The Ghost Code Manifest represents chaos, The Architect represents cold, methodical control over both the physical and digital realms.")
				.withExperience(0)
				.build(playerLevel));
		// Define unique abilities here like Code Injection, Firewall, etc.
		//addSkill(new CodeInjection());
		//addSkill(new Firewall());
		//addSkill(new DataBlast());
		//addSkill(new MemoryWipe());
		addSkill(new Slash());
		addSkill(new DataDrain());
		addSkill(new MemoryLeak());
		addSkill(new CoreDisruption());
		addSkill(new PhantomCode());
		addSkill(new QuantumDistortion());
	}
}


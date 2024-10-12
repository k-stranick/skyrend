package dtcc.itn262.items.armor;
/*TODO:
* 	implement logic to increase magic-based attacks and magic defense
* */
public class AetherReaverSuit implements Armor {
	private String name = "Aether Reactor Suit";
	private int defenseBoost = 35;
	private String description = "A suit that harnesses Aether energy to power a protective barrier, greatly enhancing defense.";


	@Override
	public String getArmor() {
		return name;
	}

	@Override
	public int getDefenseBoost() {
		return defenseBoost;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setArmor(String armor) {
		this.name = armor;
	}

	@Override
	public void setDefense(int defense) {
		this.defenseBoost = defense;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name + ": " + description + " (Defense: " + defenseBoost + ")";
	}
}


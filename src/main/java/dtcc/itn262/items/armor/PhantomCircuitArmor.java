package dtcc.itn262.items.armor;

public class PhantomCircuitArmor implements Armor {
	private String name = "Phantom Circuit Armor";
	private int defenseBoost = 80;
	private String description = "The Phantom Circuit Armor is the pinnacle of AetherGrid technology, interwoven with the Ghost Code. It phases between realities, making the wearer impervious to all forms of damage as they flicker between dimensions.";

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


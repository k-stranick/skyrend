package dtcc.itn262.items.armor;

public class PhantomCloak implements Armor {
	private String name = "Phantom Cloak";
	private int defenseBoost = 25;
	private String description = "A cloak that allows the wearer to phase in and out of the Ghost Code, evading attacks and becoming nearly invisible.";

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


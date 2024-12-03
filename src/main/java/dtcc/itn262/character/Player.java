package dtcc.itn262.character;

import dtcc.itn262.items.Item;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

import static dtcc.itn262.utilities.input.Validation.validateName;

public class Player {
	private final List<HealingItems> itemsList;// = new ArrayList<>();  // Add an inventory
	public final List<Weapon> weaponList; // = new ArrayList<>();
	public final List<Armor> armorList; // = new ArrayList<>();
	private final String hero;
	private final PlayerAttributes playerAttributes;
	private int playerRow;
	private int playerCol;
	private Armor equippedArmor;
	//private final List<StatusEffect> statusEffects = new ArrayList<>();

	// Constructor for Player class
	private Player(String hero, int startRow, int startCol) {
		this.hero = hero;
		playerAttributes = new PlayerAttributes();
		this.playerRow = startRow;
		this.playerCol = startCol;
		this.armorList = new ArrayList<>();
		this.weaponList = new ArrayList<>();
		this.itemsList = new ArrayList<>();
	}


	// Factory method to create a Player with validation
	public static Player createPlayer(String name, int startRow, int startCol) {
		String validatedName = validateName(name);
		return new Player(validatedName, startRow, startCol);
	}

	public PlayerAttributes getPlayerAttributes() {
		return playerAttributes;
	} // returns the player attributes in PlayerAttributes class

	public int getPlayerRow() {
		return playerRow;
	}

	public int getPlayerCol() { 	// Getter playerCol

		return playerCol;
	}

	public void moveTo(int newRow, int newCol) {	// Method to move the player to a new position

		this.playerRow = newRow;
		this.playerCol = newCol;
	}

	public String getHeroName() {
		return hero;
	}

	public void takeDamage(int damage) {
		int currentHealth = playerAttributes.getHealth();
		playerAttributes.setHealth(currentHealth - damage);  // Update health using the setter
		System.out.println(getHeroName() + " took " + damage + " damage. Current health: " + playerAttributes.getHealth());
	}

	public void displayItems() {
		for (int i = 0; i < itemsList.size(); i++) {
			System.out.println(i + ". " + itemsList.get(i).getName());
		}
	}

	public void addItem(HealingItems item) { 	// Method to add an item to the player's inventory
		itemsList.add(item);
		System.out.println(item.getName() + " added to the inventory.");
	}

	public void restoreHealth(int amount) {
		int newHealth = playerAttributes.getHealth() + amount;
		playerAttributes.setHealth(newHealth);
		System.out.println("Restored " + amount + " health. New health: " + playerAttributes.getHealth());
	}

	public void restoreMana(int amount) {
		int newMana = playerAttributes.getMana() + amount;
		playerAttributes.setMana(newMana);
		System.out.println("Restored " + amount + " mana. New mana: " + playerAttributes.getMana());
	}

	public boolean isAlive() {
		return playerAttributes.getHealth() > 0;
	}

	public void addWeapon(Weapon weapon) {
		weaponList.add(weapon);
		System.out.println(weapon.getName() + " added to the inventory.");
	}

	public void addArmor(Armor armor) {
		armorList.add(armor);
		System.out.println(armor.getName() + " added to the inventory.");

	}

	private void updateDefense() {
		int totalDefense = playerAttributes.getDefense();
		if (equippedArmor != null) {
			totalDefense += equippedArmor.getDefenseBoost();
		}
		playerAttributes.setDefense(totalDefense);
	}

	public void equipArmor(Armor armor) {
		if (armorList.contains(armor)) {
			equippedArmor = armor;
			updateDefense();
			System.out.println(getHeroName() + " equipped " + armor.getName() + ".");
		} else {
			System.out.println("You do not have " + armor.getName() + " in your inventory.");
		}
	}

	public void displayWeapons() {
		for (int i = 0; i < weaponList.size(); i++) {
			System.out.println(i + ". " + weaponList.get(i).getName());
		}
	}


	public void displayArmor() {
		for (int i = 0; i < armorList.size(); i++) {
			System.out.println(i + ". " + armorList.get(i).getName());
		}
	}

	public List<HealingItems> getItemsList() {
		return itemsList;
	}
	public List<Weapon> getWeaponList() {
		return weaponList;
	}

	public List<Armor> getArmorList() {
		return armorList;
	}

	public List<Item> getInventory() {
		List<Item> inventory = new ArrayList<>();

		// Add all weapons to the inventory
		inventory.addAll(weaponList);

		// Add all armors to the inventory
		inventory.addAll(armorList);

		// Add all usable items to the inventory
		inventory.addAll(itemsList);

		return inventory;
	}

	@Override
	public String toString() {  //Should I move this to PlayerAttributes class?
		return "Hero: " + hero +
				"\nStrength: " + playerAttributes.getStrength() +
				"\nHealth: " + playerAttributes.getHealth() +
				"\nMana: " + playerAttributes.getMana() +
				"\nLevel: " + playerAttributes.getLevel() +
				"\nDefense: " + playerAttributes.getDefense() +
				"\nMagic: " + playerAttributes.getMagic() +
				"\nMagic Defense: " + playerAttributes.getMagicDefense() +
				"\nSpeed: " + playerAttributes.getSpeed() +
				"\nLuck: " + playerAttributes.getLuck() +
				"\nExperience: " + playerAttributes.getExperience();
	}



	/*	public void setShielded(boolean b) {// TODO: Implement this method
	}*/
	/*	public void equipWeapon(int weaponIndex) {
		if (weaponIndex >= 0 && weaponIndex < weaponList.size()) {
			IWeapon weapon = weaponList.get(weaponIndex);
			playerAttributes.setStrength(weapon.getDamage());
			System.out.println(getHero() + " equipped " + weapon.getWeapon() + ".");
		} else {
			System.out.println("Invalid weapon choice.");
		}
	}*/
	/*    public void setHero(String hero) {
			this.hero = hero;
		}*/  // keep give players option later to change their same
	/*	public void unequipArmor() {
		if (equippedArmor != null) {
			System.out.println(getHero() + " unequipped " + equippedArmor.getArmor() + ".");
			equippedArmor = null;
			updateDefense();
		} else {
			System.out.println(getHero() + " has no armor equipped.");
		}
	}*/
	/*	public void unequipWeapon() {
		playerAttributes.setStrength(0);
		System.out.println(getHero() + " unequipped weapon.");
	}*/
	/*


	public void addStatusEffect(StatusEffect effect) {
		System.out.println(getHero() + " is now affected by " + effect.getName() + ".");
		statusEffects.add(effect);
	}

	public void updateStatusEffects() {
		// Apply each active status effect
		statusEffects.removeIf(effect -> !effect.isEffectActive());  // Remove expired effects
		for (StatusEffect effect : statusEffects) {
			effect.applyEffect(this);
		}
	}

*/
	/*    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }*/ // keep I can set up random entry once a player object is created
	/*    // Method to remove an item from the player's inventory
    public void removeItem(Items item) {
        inventory.remove(item);
        System.out.println(item.getName() + " removed from the inventory.");
    }*/ // KEEP THIS I CAN TRY AND USE IT FOR OUTSIDE OF COMBAT??
 	/*   public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }*/ // keep I can set up random entry once a player object is created
}

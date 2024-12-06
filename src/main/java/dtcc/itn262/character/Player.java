package dtcc.itn262.character;

import dtcc.itn262.items.Item;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dtcc.itn262.utilities.input.Validation.validateName;

public class Player {
	public final List<Weapon> playerWeaponList; // = new ArrayList<>();
	public final List<Armor> playerArmorList; // = new ArrayList<>();
	private final List<HealingItems> playerItemsList;// = new ArrayList<>();  // Add an inventory
	private final String hero;
	private final PlayerAttributes playerAttributes;
	private transient Map<Integer, Item> inventoryIndexMap; // Tried to use @Expose annotation but it didn't work will need to revisit
	private int playerRow;
	private int playerCol;
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	//private final List<StatusEffect> statusEffects = new ArrayList<>();

	// Constructor for Player class
	private Player(String hero, int startRow, int startCol) {
		this.hero = hero;
		playerAttributes = new PlayerAttributes();
		this.playerRow = startRow;
		this.playerCol = startCol;
		this.playerArmorList = new ArrayList<>();
		this.playerWeaponList = new ArrayList<>();
		this.playerItemsList = new ArrayList<>();
	}

	// Factory method to create a Player with validation
	public static Player createPlayer(String name, int startRow, int startCol) {
		String validatedName = validateName(name);
		return new Player(validatedName, startRow, startCol);
	}

	// returns the player attributes in PlayerAttributes class
	public PlayerAttributes getPlayerAttributes() {
		return playerAttributes;
	}

	// Getter playerRow
	public int getPlayerRow() {
		return playerRow;
	}

	// Getter playerCol
	public int getPlayerCol() {

		return playerCol;
	}

	// Method to move the player to a new position by setting current playerRow and playerCol to new row/col
	public void moveTo(int newRow, int newCol) {

		this.playerRow = newRow;
		this.playerCol = newCol;
	}

	public String getHeroName() {
		return hero;
	}

	public void gainExperience(int xp) {
		playerAttributes.setExperience(playerAttributes.getExperience() + xp);
		checkLevelUp();
	}

	private void checkLevelUp() {
		while (playerAttributes.getExperience() >= LevelingSystem.getXpForNextLevel(playerAttributes.getLevel())) {
			playerAttributes.setExperience(playerAttributes.getExperience() - LevelingSystem.getXpForNextLevel(playerAttributes.getLevel()));
			levelUp();
		}
	}

	private void levelUp() {
		playerAttributes.setLevel(playerAttributes.getLevel() + 1 + playerAttributes.getLevel());
		playerAttributes.setStrength(playerAttributes.getStrength() + 5 + playerAttributes.getLevel());
		playerAttributes.setDefense(playerAttributes.getDefense() + 5 + playerAttributes.getLevel());
		playerAttributes.setMaxHealth(playerAttributes.getMaxHealth() + 20 + playerAttributes.getLevel());
		playerAttributes.setHealth(playerAttributes.getMaxHealth());
		playerAttributes.setMagic(playerAttributes.getMagic() + 5 + playerAttributes.getLevel());
		playerAttributes.setMagicDefense(playerAttributes.getMagicDefense() + 5 + playerAttributes.getLevel());
		playerAttributes.setMaxMana(playerAttributes.getMaxMana() + 10 + playerAttributes.getLevel());
		playerAttributes.setMana(playerAttributes.getMaxMana());
		playerAttributes.setSpeed(playerAttributes.getSpeed() + 2 + playerAttributes.getLevel());
		playerAttributes.setLuck(playerAttributes.getLuck() + 1 + playerAttributes.getLevel());
		System.out.println(hero + " leveled up to level " + playerAttributes.getLevel() + "!");
	}

	public void takeDamage(int damage) {
		int currentHealth = playerAttributes.getHealth();
		playerAttributes.setHealth(currentHealth - damage);  // Update health using the setter
		System.out.println(getHeroName() + " took " + damage + " damage. Current health: " + playerAttributes.getHealth());
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

	private void updateDefense() {
		int totalDefense = playerAttributes.getDefense();
		if (equippedArmor != null) {
			totalDefense += equippedArmor.getDefenseBoost();
		}
		playerAttributes.setDefense(totalDefense);
	}

	private void updateStrength() {
		int totalStrength = playerAttributes.getStrength();
		if (equippedWeapon != null) {
			totalStrength += equippedWeapon.getDamage();
		}
		playerAttributes.setStrength(totalStrength);
	}

	public void addWeaponToPlayerInventory(Weapon weapon) {
		playerWeaponList.add(weapon);
		System.out.println(weapon.getName() + " added to the inventory.");
	}

	public void addArmorToPlayerInventory(Armor armor) {
		playerArmorList.add(armor);
		System.out.println(armor.getName() + " added to the inventory.");

	}

	public void addItemToPlayerInventory(HealingItems item) {    // Method to add an item to the player's inventory
		playerItemsList.add(item);
		System.out.println(item.getName() + " added to the inventory.");
	}

	public void equippedArmor(Armor armor) {
/*		if (!playerArmorList.contains(armor)) {
			System.out.println("You do not have " + armor.getName() + " in your inventory.");
			return;
		}*/
// i feel like i need to pass in the currently selected piece of armor
		if (armor.isEquipped()) {
			System.out.println("You already have armor equipped.");
			return;
		}

		if (equippedArmor != null) {
			equippedArmor.setEquipped(false);
		}
		equippedArmor = armor;
		armor.setEquipped(true);
		updateDefense();
		System.out.println(getHeroName() + " equipped " + armor.getName() + ".");

	}

	public void equippedWeapon(Weapon weapon) {
/*		if (!playerWeaponList.contains(weapon)) {
			System.out.println("You do not have " + weapon.getName() + " in your inventory.");
			return;
		}*/
/*		if (weapon.isEquipped()){
			System.out.println("You already have a weapon equipped.");
			return;
		}*/
		if (equippedWeapon != null) {
			equippedWeapon.setEquipped(false);
			System.out.println("You unequipped " + equippedWeapon.getName() + ".");
			return;
		}
		equippedWeapon = weapon;
		weapon.setEquipped(true);
		updateStrength();
		System.out.println(getHeroName() + " equipped " + weapon.getName() + ".");
	}

	public Armor getEquippedArmor() {
		return equippedArmor;
	}

	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	public List<HealingItems> getPlayerItemsList() {
		return playerItemsList;
	}

	public List<Weapon> getPlayerWeaponList() {
		return playerWeaponList;
	}

	public List<Armor> getPlayerArmorList() {
		return playerArmorList;
	}

	public Map<Integer, Item> getInventoryIndexMap() {
		return inventoryIndexMap;
	}

	public void setInventoryIndexMap(Map<Integer, Item> map) {
		this.inventoryIndexMap = map;
	}

	@Override
	public String toString() {  //Should I move this to PlayerAttributes class?
		int xpForNextLevel = LevelingSystem.getXpForNextLevel(playerAttributes.getLevel());

		return "Hero: " + hero +
				"\nLevel: " + playerAttributes.getLevel() +
				"\nExperience: " + playerAttributes.getExperience() + "/" + xpForNextLevel +
				"\nStrength: " + playerAttributes.getStrength() +
				"\nHealth: " + playerAttributes.getHealth() +
				"\nMana: " + playerAttributes.getMana() +
				"\nDefense: " + playerAttributes.getDefense() +
				"\nMagic: " + playerAttributes.getMagic() +
				"\nMagic Defense: " + playerAttributes.getMagicDefense() +
				"\nSpeed: " + playerAttributes.getSpeed() +
				"\nLuck: " + playerAttributes.getLuck();
	}

	/*	public void setShielded(boolean b) {// TODO: Implement this method
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

}

package dtcc.itn262.character;

import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.combat.effects.StatusEffect;
import dtcc.itn262.items.usableitems.UsableItems;
import dtcc.itn262.items.weapons.IWeapon;
import java.util.ArrayList;
import java.util.List;

import static dtcc.itn262.utilities.input.Validation.validateName;

public class Player {
	private final String hero;
	private final PlayerAttributes playerAttributes;
	private final List<StatusEffect> statusEffects = new ArrayList<>();
	private final List<UsableItems> inventory = new ArrayList<>();  // Add an inventory
	private final List<IWeapon> weaponInventory = new ArrayList<>();
	private final List<Armor> armorInventory = new ArrayList<>();
	private int playerRow;
	private int playerCol;
	private Armor equippedArmor;


	private Player(String hero, int startRow, int startCol) {
		this.hero = hero;
		playerAttributes = new PlayerAttributes();
		this.playerRow = startRow;
		this.playerCol = startCol;
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


 /*   public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }*/ // keep I can set up random entry once a player object is created


	// Getter and Setter for playerCol
	public int getPlayerCol() {
		return playerCol;
	}


/*    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }*/ // keep I can set up random entry once a player object is created


	// Method to move the player to a new position
	public void moveTo(int newRow, int newCol) {
		this.playerRow = newRow;
		this.playerCol = newCol;
	}

	public String getHero() {
		return hero;
	}

	public void takeDamage(int damage) {
		int currentHealth = playerAttributes.getHealth();
		playerAttributes.setHealth(currentHealth - damage);  // Update health using the setter
		System.out.println(getHero() + " took " + damage + " damage. Current health: " + playerAttributes.getHealth());
	}


	public void displayInventory() {
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(i + ". " + inventory.get(i).getName());
		}
	}

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

	private void updateDefense() {
		int totalDefense = playerAttributes.getDefense();
		if (equippedArmor != null) {
			totalDefense += equippedArmor.getDefenseBoost();
		}
		playerAttributes.setDefense(totalDefense);
	}

	/*    public void setHero(String hero) {
			this.hero = hero;
		}*/  // keep give players option later to change their same
// Method to add an item to the player's inventory
	public void addItem(UsableItems item) { // TODO: move this somwhere?
		inventory.add(item);
		System.out.println(item.getName() + " added to the inventory.");
	}

	// Example method for restoring health (you should already have this)
	public void restoreHealth(int amount) {
		int newHealth = playerAttributes.getHealth() + amount;
		playerAttributes.setHealth(newHealth);
		System.out.println("Restored " + amount + " health. New health: " + playerAttributes.getHealth());
	}

	// Example method for restoring mana (if applicable)
	public void restoreMana(int amount) {
		int newMana = playerAttributes.getMana() + amount;
		playerAttributes.setMana(newMana);
		System.out.println("Restored " + amount + " mana. New mana: " + playerAttributes.getMana());
	}

	public boolean isAlive() {
		return playerAttributes.getHealth() > 0;
	}

	public void setShielded(boolean b) {// TODO: Implement this method
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

	public void addWeapon(IWeapon item) {
		weaponInventory.add(item);
		System.out.println(item.getWeapon() + " added to the inventory.");
	}

	public void addArmor(Armor armor) {
		armorInventory.add(armor);
		System.out.println(armor.getArmor() + " added to the inventory.");

	}

/*    // Method to remove an item from the player's inventory
    public void removeItem(Items item) {
        inventory.remove(item);
        System.out.println(item.getName() + " removed from the inventory.");
    }*/ // KEEP THIS I CAN TRY AND USE IT FOR OUTSIDE OF COMBAT??

}

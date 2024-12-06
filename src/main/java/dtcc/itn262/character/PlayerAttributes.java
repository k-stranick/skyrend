package dtcc.itn262.character;

public class PlayerAttributes {
	//Random rand = new Random();

	private int level;
	private int baseStrength;  //TODO
	private int strength;  // governs attack power
	private int baseDefense;  //TODO
	private int defense;  // governs attack resistance
	private int health; // governs current life
	private int maxHealth; // governs max life
	private int magic;  // governs magic attack power
	private int magicDefense;  // governs magic attack resistance
	private int mana;  // governs current magic pool
	private int maxMana; // governs max magic pool
	private int speed; // governs who attacks first
	private int luck; // governs critical hit chance
	private int experience; // governs level

	public PlayerAttributes() {

		this.magicDefense = 18;
		this.baseStrength = 30;
		this.strength = baseStrength; //TODO
		this.baseDefense = 18;
		this.defense = baseDefense;
		this.magic = 20;
		this.speed = 15;  //TODO
		this.luck = 9; // crits?
		this.experience = 0; // base experience
		this.maxHealth = 350;  // base max health, need it for a leveling system
		this.health = maxHealth;  // set current health to max health
		this.maxMana = 125; // base max mana  //TODO
		this.mana = maxMana; // set current mana to max mana, need it for a leveling system
		this.level = 1; // base level
	}


	public int getBaseStrength() {
		return baseStrength;
	}

	public void setBaseStrength(int baseStrength) {
		this.baseStrength = baseStrength;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		// Prevent health from going over maxHealth or below 0
		this.health = Math.max(0, Math.min(health, maxHealth));
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}


	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		// Prevent mana from going over maxMana or below 0
		this.mana = Math.max(0, Math.min(mana, maxMana));
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBaseDefense() {
		return baseDefense;
	}

	public int getDefense() {
		return defense;
	}

	public void setBaseDefense(int defense) {
		this.baseDefense = defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}


	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}


	public int getMagicDefense() {
		return magicDefense;
	}

	public void setMagicDefense(int magicDefense) {
		this.magicDefense = magicDefense;
	}


	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}


}
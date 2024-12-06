package dtcc.itn262.monster;

public class MonsterAttributes {
	private int level;
	private int activeHealth;
	private int maxHealth;
	private int strength;
	private int defense;
	private int magic;
	private int magicDefense;
	private int speed;
	private int luck;
	private int experience;
	private int gold;
	private int mana;
	private int maxMana;
	private String type;
	private String description;


	// Custom constructor for specific monsters or bosses
	protected MonsterAttributes(MonsterAttributesBuilder builder, int playerLevel) {
		this.activeHealth = builder.activeHealth + (playerLevel * 10);
		this.maxHealth = builder.maxHealth + (playerLevel * 10);
		this.strength = builder.strength + (playerLevel * 5);
		this.defense = builder.defense + playerLevel;
		this.magic = builder.magic + (playerLevel * 5);
		this.magicDefense = builder.magicDefense + playerLevel;
		this.speed = builder.speed + playerLevel;
		this.luck = builder.luck + playerLevel;
		this.mana = builder.mana + (playerLevel * 5);
		this.maxMana = builder.maxMana + (playerLevel * 5);
		this.type = builder.type;
		this.description = builder.description;
		this.experience = builder.experience;
		this.level = builder.level == 0 ? (playerLevel + 4) : builder.level;
		this.gold = builder.gold;
	}


	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getActiveHealth() {
		return activeHealth;
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
		this.mana = mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public void setActiveHealth(int activeHealth) {
		this.activeHealth = activeHealth;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static class MonsterAttributesBuilder { // for generic monsters
		private int maxHealth = 100;
		private int activeHealth = 100;
		private int strength = 10;
		private int defense = 10 ;
		private int magic = 10;
		private int magicDefense = 10;
		private int speed = 5;
		private int luck = 5;
		private int experience = 0;
		private int level = 1;
		private int gold = 0;
		private int maxMana = 50;
		private int mana= 50;
		private String type = "Monster";
		private String description = "A monster";

		// Build method to return a MonsterAttributes instance
		public MonsterAttributes build(int playerLevel) {
			return new MonsterAttributes(this, playerLevel);
		}

		public MonsterAttributesBuilder withHealth(int activeHealth) {
			this.activeHealth = activeHealth;

			return this;
		}

		public MonsterAttributesBuilder withMaxHealth(int maxHealth) {
			this.maxHealth = maxHealth;
			return this;
		}

		public MonsterAttributesBuilder withMana(int mana) {
			this.mana = mana;
			return this;
		}

		public MonsterAttributesBuilder withMaxMana(int maxMana) {
			this.maxMana = maxMana;
			return this;
		}

		public MonsterAttributesBuilder withStrength(int strength) {
			this.strength = strength;
			return this;
		}

		public MonsterAttributesBuilder withDefense(int defense) {
			this.defense = defense;
			return this;
		}

		public MonsterAttributesBuilder withMagic(int magic) {
			this.magic = magic;
			return this;
		}

		public MonsterAttributesBuilder withMagicDefense(int magicDefense) {
			this.magicDefense = magicDefense;
			return this;
		}

		public MonsterAttributesBuilder withSpeed(int speed) {
			this.speed = speed;
			return this;
		}

		public MonsterAttributesBuilder withLuck(int luck) {
			this.luck = luck;
			return this;
		}

		public MonsterAttributesBuilder withType(String type) {
			this.type = type;
			return this;
		}

		public MonsterAttributesBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public MonsterAttributesBuilder withExperience(int experience) {
			this.experience = experience;
			return this;
		}

		public MonsterAttributesBuilder withLevel(int level) {
			this.level = level;
			return this;
		}

		public MonsterAttributesBuilder withGold(int gold) {
			this.gold = gold;
			return this;
		}

/*		// Build method to return a MonsterAttributes instance
		public MonsterAttributes build() {
			return new MonsterAttributes(this);
		}*/
	}
}


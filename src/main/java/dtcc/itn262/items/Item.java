package dtcc.itn262.items;

public abstract class Item implements java.io.Serializable, Cloneable {
	private final String name;
	private final String description;
	private final String type;
	private double dropRate;

	protected Item(String name, String type ,String description, double dropRate) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.dropRate = dropRate;
	}

	protected Item(String name, String description, double dropRate) {
		this.name = name;
		this.description = description;
		this.dropRate = dropRate;
		this.type = "";
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public double getDropRate() {
		return dropRate;
	}

	@Override
	public String toString() {
		return name + ": " + description;
	}

	@Override
	public Item clone() {
		try {
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return (Item) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}

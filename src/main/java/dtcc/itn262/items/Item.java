package dtcc.itn262.items;

public abstract class Item implements java.io.Serializable {
	private final String name;
	private final String description;
	private final String type;

	protected Item(String name, String type ,String description) {
		this.name = name;
		this.type = type;
		this.description = description;
	}

	protected Item(String name, String description) {
		this.name = name;
		this.description = description;
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

	@Override
	public String toString() {
		return name + ": " + description;
	}
}

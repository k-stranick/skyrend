package dtcc.itn262.dungeon;

public class Room {
	private final String name;
	private final String description;
	private final boolean isSpecial;
	private boolean hasNorthExit;
	private boolean hasSouthExit;
	private boolean hasEastExit;
	private boolean hasWestExit;
	private boolean visited;

	public Room(String name, String description, boolean hasNorthExit, boolean hasSouthExit, boolean hasEastExit, boolean hasWestExit, boolean isSpecial) {
		this.name = name;
		this.description = description;
		this.visited = false;
		this.isSpecial = isSpecial; // Set if the room has a fight or story dialogue
		this.hasNorthExit = hasNorthExit;
		this.hasSouthExit = hasSouthExit;
		this.hasEastExit = hasEastExit;
		this.hasWestExit = hasWestExit;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return "This is " + description;
	}

	public boolean hasNorthExit() { return hasNorthExit; }
	public boolean hasSouthExit() { return hasSouthExit; }
	public boolean hasEastExit() { return hasEastExit; }
	public boolean hasWestExit() { return hasWestExit; }

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String toString() {
		return "Room: " + name + "\nDescription: " + description + "\nN: " + hasNorthExit + "\nS: " + hasSouthExit + "\nW: " + hasWestExit + "\nE: " + hasEastExit;
	}
}

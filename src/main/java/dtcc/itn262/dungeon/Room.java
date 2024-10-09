package dtcc.itn262.dungeon;

public class Room {
	private final String name;
	private final String description;
	private final boolean isSpecial;
	private final boolean hasNorthExit;
	private final boolean hasSouthExit;
	private final boolean hasEastExit;
	private final boolean hasWestExit;
	private boolean visited;
	private final int sceneIndex;

	public Room(RoomConfiguration roomConfiguration) {
		this.name = roomConfiguration.name();
		this.description = roomConfiguration.description();
		this.visited = false;
		this.isSpecial = roomConfiguration.isSpecial(); // Set if the room has a fight or story dialogue
		this.hasNorthExit = roomConfiguration.hasNorthExit();
		this.hasSouthExit = roomConfiguration.hasSouthExit();
		this.hasEastExit = roomConfiguration.hasEastExit();
		this.hasWestExit = roomConfiguration.hasWestExit();
		this.sceneIndex = roomConfiguration.sceneIndex();
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

	public int getSceneIndex() {
		return sceneIndex;
	}

	public String toString() {
		return "Room: " + name + "\nDescription: " + description + "\nN: " + hasNorthExit + "\nS: " + hasSouthExit + "\nW: " + hasWestExit + "\nE: " + hasEastExit;
	}
}

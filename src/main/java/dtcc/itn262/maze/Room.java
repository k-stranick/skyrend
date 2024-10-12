package dtcc.itn262.maze;

import dtcc.itn262.items.usableitems.UsableItems;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private final String name;
	private final String description;
	private final boolean isSpecial;
	private final int n;
	private final int s;
	private final int w;
	private final int e;
	private final int sceneIndex;
	private boolean specialEventTriggered;
	private boolean visited;
	private List<UsableItems> items;

	public Room(RoomConfiguration roomConfiguration) {
		this.name = roomConfiguration.name();
		this.description = roomConfiguration.description();
		this.visited = false;
		this.isSpecial = roomConfiguration.isSpecial(); // Set if the room has a fight or story dialogue
		this.specialEventTriggered = false;
		this.n = roomConfiguration.n();
		this.s = roomConfiguration.s();
		this.e = roomConfiguration.e();
		this.w = roomConfiguration.w();
		this.sceneIndex = roomConfiguration.sceneIndex();
		this.items = new ArrayList<>();
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public boolean isSpecialEventTriggered() {
		return specialEventTriggered;
	}

	public void setSpecialEventTriggered(boolean triggered) {
		this.specialEventTriggered = triggered;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return "This is " + description;
	}


	public int getN() {
		return n;
	}

	public int getS() {
		return s;
	}

	public int getW() {
		return w;
	}

	public int getE() {
		return e;
	}

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
		//return "Room: " + name + "\nDescription: " + description + "\nN: " + hasNorthExit + "\nS: " + hasSouthExit + "\nW: " + hasWestExit + "\nE: " + hasEastExit;
		return "Room: " + name + "\nDescription: " + description + "\nN: " + n + "\nS: " + s + "\nW: " + w + "\nE: " + e;

	}

	public void addItem(UsableItems item) {
		items.add(item);
	}

	public List<UsableItems> getItems() {
		return items;
	}

	public boolean hasItems() {
		return !items.isEmpty();
	}
}

package dtcc.itn262.maze;

import dtcc.itn262.items.Item;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.utilities.gamecore.GameLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Room {
	private final int roomIndex;
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
	private final List<Item> items;
	private boolean hasBeenSearched;

	public Room(RoomConfiguration roomConfiguration) {
		this.roomIndex = roomConfiguration.index();
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
		this.hasBeenSearched = false;
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

	public int getRoomIndex() {
		return roomIndex;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
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

	public void addItem(HealingItems item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean hasItems() {
		return !items.isEmpty();
	}

	public boolean hasBeenSearched() {
		return hasBeenSearched;
	}

	public void setHasBeenSearched(boolean hasBeenSearched) {
		this.hasBeenSearched = hasBeenSearched;
	}

	public void displayRoomItems() {
		if (hasItems()) {
			System.out.println("Items in the room:");
			for (int i =0; i < items.size(); i++) {
				System.out.println(i + ". " + items.get(i).getName());
			}
		} else {
			System.out.println("There are no items in this room.");
		}
	}

	public void visitRoom(Set<String> uniqueVisitedRooms)
	{
		try {
			if (this.isVisited()) {
				return;
			}

			this.setVisited(true);  // Mark room as visited
			if (this.isSpecial()) {
				uniqueVisitedRooms.add(this.getName());  // Track special rooms
			}

		} catch (NullPointerException e) {
			GameLogger.logError(Constants.ROOM_ERROR + e.getMessage());
		}
	}
}

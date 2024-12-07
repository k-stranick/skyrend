package dtcc.itn262.maze;

import java.util.List;

/**
 * The `MazeJson` class represents the structure of a maze as defined in a JSON file.
 * It contains a list of lists of `RoomConfiguration` objects, which represent the configuration of each room in the maze.
 * This class is used to deserialize the JSON representation of the maze into Java objects that can be used within the application.
 */
public class MazeJson {
	private List<List<RoomConfiguration>> rooms;

	/**
	 * Gets the list of lists of `RoomConfiguration` objects representing the rooms in the maze.
	 *
	 * @return the list of lists of `RoomConfiguration` objects.
	 */
	public List<List<RoomConfiguration>> getRooms() {
		return rooms;
	}

	/**
	 * Sets the list of lists of `RoomConfiguration` objects representing the rooms in the maze.
	 *
	 * @param rooms the list of lists of `RoomConfiguration` objects.
	 */
	public void setRooms(List<List<RoomConfiguration>> rooms) {
		this.rooms = rooms;
	}
}
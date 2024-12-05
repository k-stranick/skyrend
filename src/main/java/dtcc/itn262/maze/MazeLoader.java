package dtcc.itn262.maze;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MazeLoader {
	public static Room[][] loadMazeFromJson(String jsonFilePath) {
		Gson gson = new Gson();

		try (FileReader reader = new FileReader(jsonFilePath)) {
			// Deserialize the JSON file
			MazeJson mazeJson = gson.fromJson(reader, MazeJson.class);

			// Convert List<List<RoomConfiguration>> to Room[][]
			List<List<RoomConfiguration>> roomConfigurations = mazeJson.getRooms();
			int rows = roomConfigurations.size();
			int cols = roomConfigurations.getFirst().size();
			Room[][] maze = new Room[rows][cols];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					RoomConfiguration config = roomConfigurations.get(i).get(j);
					if (config != null) {
						maze[i][j] = new Room(config);
					} else {
						maze[i][j] = null;
					}
				}
			}

			return maze;
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

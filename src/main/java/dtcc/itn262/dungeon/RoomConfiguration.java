package dtcc.itn262.dungeon;

public record RoomConfiguration(String name, String description, boolean hasNorthExit, boolean hasSouthExit,
								boolean hasEastExit, boolean hasWestExit, boolean isSpecial, int sceneIndex) {


}

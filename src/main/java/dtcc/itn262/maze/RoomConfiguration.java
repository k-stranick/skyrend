package dtcc.itn262.maze;

public record RoomConfiguration(int index,String name, String description,int n, int s, int e,int w, boolean isSpecial, int sceneIndex) {


}
//boolean hasNorthExit, boolean hasSouthExit, boolean hasEastExit, boolean hasWestExit
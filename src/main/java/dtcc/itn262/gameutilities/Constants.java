package dtcc.itn262.gameutilities;

public class Constants {
    //Map Constants
    public static final int CANNOT_TRAVERSE = -1; // used to avoid magic numbers inside code and allow for modification
    public static final int MAX_ROOMS_TO_ESCAPE = 5; // maximum number of rooms to escape the dungeon

    //Combat Constants
    public static final int DEFENSE_BUFF = 5; // defense buff for player



    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}

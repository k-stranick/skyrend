package dtcc.itn262.utilities.gamecore;

public class Constants {
	//Map Constants
	public static final int NO_SCENE = -1; // used to avoid magic numbers inside code and allow for modification

	//Combat Constants
	public static final int DEFENSE_BUFF = 5; // defense buff for player
	public static final int INITIAL_SCENE = 0; // initial scene
	public static final int SCENE_1 = 1; // scene 1
	public static final int SCENE_2 = 2; // scene 2
	public static final int SCENE_3 = 3; // scene 3
	public static final int SCENE_4 = 4; // scene 4
	public static final int SCENE_5 = 5; // scene 5
	public static final int SCENE_6 = 6; // scene 6

	//ERROR Constants
	public static final String ROOM_ERROR = "Room not initialized";

	private Constants() {
		throw new IllegalStateException("Utility class");
	}
}

package dtcc.itn262.utilities.display;

import dtcc.itn262.dungeon.Room;
import java.util.List;
import java.util.Set;
import dtcc.itn262.character.Player;

public class TextDisplayUtility {

    private TextDisplayUtility() {
    }


    public static void printSeparator(int i) {
        for (int j = 0; j < i; j++) {
            System.out.print("-");
        }
        System.out.println();
    }


    // Show the rooms the player has visited
    public static void showProgress(Set<String> uniqueVisitedRooms) {
        System.out.println("Special rooms you have visited: ");
        for (String room : uniqueVisitedRooms) {
            System.out.println(room);
        }
    }


    public static void showMoveHistory(List<String> moveHistory) {
        System.out.println("Move History: ");
        printSeparator(20);
        int counter = 1;
        for (String move : moveHistory) {
            System.out.println(counter + ": Moved to " + move);  // Print each move on a new line
            counter++;
        }
    }


    public static void showCurrentRoom(Room[][] map, Player player) {
        Room currentRoom = map[player.getPlayerRow()][player.getPlayerCol()];
        System.out.println("You are in: " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());
    }
}

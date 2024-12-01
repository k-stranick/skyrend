package dtcc.itn262.utilities.display;

import dtcc.itn262.maze.Room;
import java.util.List;
import java.util.Set;
import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

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

    public static void showBattleScreen(Player player, Monster enemy) {
        String playerHpBar = generateBar(player.getPlayerAttributes().getHealth(), player.getPlayerAttributes().getMaxHealth());
        String enemyHpBar = generateBar(enemy.getMonsterAttributes().getHealth(), enemy.getMonsterAttributes().getMaxHealth());
        String playerMpBar = generateBar(player.getPlayerAttributes().getMana(), player.getPlayerAttributes().getMaxMana());
        String enemyMpBar = generateBar(enemy.getMonsterAttributes().getMana(), enemy.getMonsterAttributes().getMaxMana());

        System.out.println("+------------------------------------------------------------+");
        System.out.println("         Battle: " + player.getHero() + "        vs          " + enemy.getMonster());
        System.out.println("+------------------------------------------------------------+");
        System.out.println("  Hero: " + player.getHero() + "             Enemy: " + enemy.getMonster());
        System.out.println("| HP: " + playerHpBar + "     HP: " + enemyHpBar + "                      |");
        System.out.println("| MP: " + playerMpBar + "     MP: " + enemyMpBar + "                      |");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| [1] Attack    [2] Defend        [3] Use Skill     [4] Scan |");
        System.out.println("| [5] Item      [6] Swap Weapon   [7] Swap Armor    [8] Run  |");
        System.out.println("+------------------------------------------------------------+");
        }

    private static String generateBar(int currentValue, int maxValue) {
        int barLength = 10; // Total length of the bar
        int filledBars = (int) (((double) currentValue / maxValue) * barLength);
        int emptyBars = barLength - filledBars;
		return "[" + "#".repeat(filledBars) + "-".repeat(emptyBars) + "]";
    }

    public static void showMainMenu(){
        AsciiArt.displayAsciiArt("src/main/java/dtcc/itn262/json/ascii-text-art.txt");  // Display the game logo
        System.out.println("========================= START MENU ===============================");
        System.out.println("1. Start New Game - Begin a new adventure and create your hero.");
        System.out.println("2. Load Game - Load a saved game (coming soon).");
        System.out.println("3. View Help - Learn how to play the game.");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

}

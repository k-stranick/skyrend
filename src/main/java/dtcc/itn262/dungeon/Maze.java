package dtcc.itn262.dungeon;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.gameutilities.Constants;
import dtcc.itn262.gameutilities.DisplayUtility;
import dtcc.itn262.gameutilities.Validation;
import dtcc.itn262.monster.Monster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Maze {
    private static Maze instance;
    private final Room[][] map;
    private final int requiredVisitedRooms;
    // private Movement movement;
    private List<String> visitedRooms;
    private final Set<String> uniqueVisitedRooms;
    private final List<String> moveHistory = new ArrayList<>(); // tracks all moves
    private final Player player;


    //constructor
    private Maze() {
        // movement = new Movement();
        map = initializeMap();
        player = Player.createPlayer("Hero", 0, 0);
        requiredVisitedRooms = countSpecialRooms();
        uniqueVisitedRooms = new HashSet<>();  //If a player visits the same room multiple times, the HashSet will only store that room once.
        visitRoom(map[player.getPlayerRow()][player.getPlayerCol()]);  // Mark the starting room as visited
/*
        visitedRooms = new ArrayList<>();
*/

        // rooms = new ArrayList<>();
        //currentRoomIndex = 0;
        //createRooms();
        DisplayUtility.showCurrentRoom(map, player);
    }

    public static Maze getInstance() {
        if (instance == null) {
            instance = new Maze();
        }
        return instance;
    }

    public List<String> getMoveHistory() {
        return moveHistory;
    }

    public Set<String> getUniqueVisitedRooms() {
        return uniqueVisitedRooms;
    }

    public void move(String direction) {
        int newRow = player.getPlayerRow();
        int newCol = player.getPlayerCol();

        switch (direction.toLowerCase()) {
            case "north": newRow--;break;
            case "south": newRow++;break;
            case "east": newCol++;break;
            case "west": newCol--;break;
            default:
                System.out.println("Invalid direction. Use 'north', 'south', 'east', 'west'.");
                return;
        }
        positionValidation(newRow, newCol);
  /*      // Check if the new position is valid
        if (Validation.isValidRoom(newRow, newCol, map)) {
            player.moveTo(newRow, newCol); // Move the player to the new room
            Room newRoom = map[newRow][newCol];
            visitRoom(newRoom); // Mark the new room as visited
            showCurrentRoom(); // Display the new room's details
            checkEscapeCondition(); // Check if the player can escape after moving
            handleSpecialRoom(newRoom); // Handle special events if the room is special
            displayMap();  // Display the updated map
        } else {
            System.out.println("You can't move in that direction.");
        }*/
    }


    private void positionValidation(int newRow, int newCol) {
        // Check if the new position is valid
        if (Validation.isValidRoom(newRow, newCol, map)) {
            player.moveTo(newRow, newCol); // Move the player to the new room
            Room newRoom = map[newRow][newCol];
            processRoomAfterMove(newRoom);
        } else {
            System.out.println("You can't move in that direction.");
        }
    }


    private void processRoomAfterMove(Room newRoom) {
        visitRoom(newRoom);
        DisplayUtility.showCurrentRoom(map, player);
        triggerSpecialEvent(newRoom);
        //displayMap(); // DO I NEED THIS?
        checkEscapeCondition();
        updateMoveHistory(newRoom);
    }

    private void updateMoveHistory(Room room) {
        moveHistory.add("Moved to " + room.getName()); // Add the move to the history

    }


    public void visitRoom(Room currentRoom) {
        if (currentRoom.isSpecial() && !currentRoom.isVisited()) {
            uniqueVisitedRooms.add(currentRoom.getName());  // Track unique visited rooms
        }
        currentRoom.setVisited(true);  // Mark the room as visited when the player enters
    }


    // Display the map with the player's current position
    public void displayMap() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (row == player.getPlayerRow() && col == player.getPlayerCol()) {
                    System.out.print("[o-|-<] ");  // Show player's position
                } else if (map[row][col].isVisited()) {
                    System.out.print("[ ] ");  // Show visited rooms
                } else {
                    System.out.print("[X] ");  // Unvisited rooms
                }
            }
            System.out.println();  // Newline after each row
        }
    }


 /*   // Show the rooms the player has visited
    public void showProgress() {
        System.out.println("Special rooms you have visited: ");
        for (String room : uniqueVisitedRooms) {
            System.out.println(room);
        }
    }*/

 /*   public void showMoveHistory() {
        System.out.println("Move History: ");
        System.out.println("----------------------");
        int counter = 1;
        for (String move : moveHistory) {
            System.out.println(counter + ": Moved to " + move);  // Print each move on a new line
            counter++;
        }
    }*/

/*    private void showCurrentRoom() {
        Room currentRoom = map[player.getPlayerRow()][player.getPlayerCol()];
        System.out.println("You are in: " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());
    }*/


    private void checkEscapeCondition() { //TODO do i need this if i have something ismilar??
        // Check if the player has visited enough rooms to escape
        if (uniqueVisitedRooms.size() >= Constants.MAX_ROOMS_TO_ESCAPE) {
            System.out.println("You have visited enough rooms to escape the city!");
            System.exit(0);  // Exit or trigger escape
        }
    }


    // Define a method to trigger special events
    private void triggerSpecialEvent(Room room) { //TODO add more
        switch (room.getName()){
            case "Ghost Terminal":
                System.out.println("You encounter ArcTech enforcers! Prepare for battle.");
                Monster monster = new Monster("ArcTech Enforcer");
                CombatLogic combat = new CombatLogic(player, monster);
                combat.startFight();
                break;
            case "Aether Nexus":
                System.out.println("The final confrontation with the Council of Circuits awaits...");
                break;
            default:
                System.out.println("No special event in this room.");
        }
    }

    // Method to count special rooms
    private int countSpecialRooms() {
        int count = 0;
        for (Room[] row : map) {
            for (Room room : row) {
                if (room.isSpecial()) {
                    count++;
                }
            }
        }
        return count;
    }

    private Room[][] initializeMap() {
        return new Room[][]{
                {
                        new Room("The Outskirts", "A lawless area on the edge of the city, filled with rogue Aether users and hackers.", 1, -1, 2, -1, false), // Room 0
                        new Room("Neon Corridor", "A bustling district filled with traders, technomancers, and black market dealers.", 3, 0, 4, -1, false),   // Room 1
                        new Room("Abandoned Tech Labs", "A research facility overrun with failed AI experiments and corrupted magic.", -1, -1, 5, 0, true)    // Room 2
                },
                {
                        new Room("The Driftway", "A dangerous floating sky rail system connecting the lower city to the elite Nexus towers.", 6, 1, -1, -1, false),  // Room 3
                        new Room("Aetheric Sanctum", "A hidden sanctum where the raw power of the Aether flows freely. Only the most powerful technomancers dare enter.", -1, 5, -1, 7, true),  // Room 4
                        new Room("Rune Street Markets", "A chaotic black market where rogue technomancers trade AetherGrid enhancements.", 7, -1, -1, 1, false)  // Room 5
                },
                {
                        new Room("Ghost Terminal", "An underground server room where the Ghost Code emerged, guarded by ArcTech enforcers.", 8, -1, -1, 2, true),  // Room 6
                        new Room("Arcane Synth Bay", "A fusion lab for cybernetic enhancements, pulsing with corrupted spells.", 9, 3, -1, -1, false),  // Room 7
                        new Room("Echo Vault", "A hidden digital vault where echoes of past Aether events are stored, distorted by the Ghost Code.", 8, 4, -1, -1, false)  // Room 8
                },
                {
                        new Room("Obsidian Relay", "A central hub of the AetherGrid where the Council of Circuits monitors all magical and digital activity.", 10, 5, -1, 7, true),  // Room 9
                        new Room("Nullspace Hub", "A digital realm within the AetherGrid where space and time distort unpredictably.", 10, 6, -1, -1, false),  // Room 10
                        new Room("Aether Nexus", "The towering heart of Skyrend, controlled by the Council of Circuits.", -1, 8, -1, 9, true)  // Room 11
                }
        };
    }

    public int getRequiredVisitedRooms() {
        return requiredVisitedRooms;
    }
/*
    public void search() {
        Random rand = new Random();
        int intSearch = rand.nextInt(10);
        if (intSearch > 7) {

            keyFound = true;
            System.out.println("You found the key!");
            moves.push("keyFound");

        } else {
            System.out.println("You did not find the key.");
            moves.push("no key found");
        }
    }*/

  /*  private void loadScenes() {
        String file = "src/maze.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; // grab each line  in the text file
            while ((line = br.readLine()) != null) {
                scenes.add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

}



package dtcc.itn262.dungeon;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.monster.Monster;

import java.util.*;

public class Maze {
    private final int MAX_ROOMS_TO_ESCAPE = 5;
    private Movement movement;
    private List<String> visitedRooms;
    private Set<String> uniqueVisitedRooms;
    private List<Room> rooms;
    private int currentRoomIndex;
    private List<String> moveHistory = new ArrayList<>(); // tracks all moves
    private Stack<String> moves = new Stack<>(); // stack of moves
    private Player player;


    //constructor
    public Maze() {
        movement = new Movement();
        visitedRooms = new ArrayList<>();
        uniqueVisitedRooms = new HashSet<>();
        rooms = new ArrayList<>();
        currentRoomIndex = 0;
        createRooms();
        showCurrentRoom();
    }

    private void createRooms() {
        rooms.add(new Room("The Outskirts", "A lawless area on the edge of the city, filled with rogue Aether users and hackers.", 1, -1, 2, -1, false)); // Room 0
        rooms.add(new Room("Neon Corridor", "A bustling district filled with traders, technomancers, and black market dealers.", 3, 0, 4, -1, false)); // Room 1
        rooms.add(new Room("Abandoned Tech Labs", "A research facility overrun with failed AI experiments and corrupted magic.", -1, -1, 5, 0, true)); // Room 2
        rooms.add(new Room("The Driftway", "A dangerous floating sky rail system connecting the lower city to the elite Nexus towers.", 6, 1, -1, -1, false)); // Room 3
        rooms.add(new Room("Aetheric Sanctum", "A hidden sanctum where the raw power of the Aether flows freely. Only the most powerful technomancers dare enter.", -1, 5, -1, 7, true)); // Room 4
        rooms.add(new Room("Rune Street Markets", "A chaotic black market where rogue technomancers trade AetherGrid enhancements.", 7, -1, -1, 1, false)); // Room 5
        rooms.add(new Room("Ghost Terminal", "An underground server room where the Ghost Code emerged, guarded by ArcTech enforcers.", 8, -1, -1, 2, true)); // Room 6
        rooms.add(new Room("Arcane Synth Bay", "A fusion lab for cybernetic enhancements, pulsing with corrupted spells.", 9, 3, -1, -1, false)); // Room 7
        rooms.add(new Room("Echo Vault", "A hidden digital vault where echoes of past Aether events are stored, distorted by the Ghost Code.", 8, 4, -1, -1, false)); // Room 8
        rooms.add(new Room("Obsidian Relay", "A central hub of the AetherGrid where the Council of Circuits monitors all magical and digital activity.", 10, 5, -1, 7, true)); // Room 9
        rooms.add(new Room("Nullspace Hub", "A digital realm within the AetherGrid where space and time distort unpredictably.", 10, 6, -1, -1, false)); // Room 10
        rooms.add(new Room("Aether Nexus", "The towering heart of Skyrend, controlled by the Council of Circuits.", -1, 8, -1, 9, true)); // Room 11
    }

    // Handle movement
    public void move(String direction) {
        Room currentRoom = rooms.get(currentRoomIndex);// get current room
        if (movement.move(currentRoom, direction)) {  // Use Movement class to check if the move is valid

            switch (direction.toLowerCase()) {
                case "north":
                    currentRoomIndex = currentRoom.getN();
                    break;
                case "south":
                    currentRoomIndex = currentRoom.getS();
                    break;
                case "east":
                    currentRoomIndex = currentRoom.getE();
                    break;
                case "west":
                    currentRoomIndex = currentRoom.getW();
                    break;
            }
            System.out.println("You move " + direction + ".");
            showCurrentRoom();  // Show the new room after moving
        }
    }

    public void undoMove() {
        String lastMove = movement.undoMove();
        if (!lastMove.isEmpty()) {
            Room currentroom = rooms.get(currentRoomIndex);
            switch (lastMove.toLowerCase()) {
                case "north":
                    currentRoomIndex = currentroom.getS();
                    break;
                case "south":
                    currentRoomIndex = currentroom.getN();
                    break;
                case "east":
                    currentRoomIndex = currentroom.getW();
                    break;
                case "west":
                    currentRoomIndex = currentroom.getE();
                    break;
            }
            showCurrentRoom();
        }
    }

    public void showMap() {
        // Display a simple text-based map and mark the player's current location
        String[] map = new String[] {
                "[The Outskirts]", "[Neon Corridor]", "[Abandoned Tech Labs]", "[The Driftway]",
                "[Aetheric Sanctum]", "[Rune Street Markets]", "[Ghost Terminal]",
                "[Arcane Synth Bay]", "[Echo Vault]", "[Obsidian Relay]", "[Nullspace Hub]", "[Aether Nexus]"
        };

        // Mark the player's current location
        for (int i = 0; i < map.length; i++) {
            if (i == currentRoomIndex) {
                System.out.print("[[x] " + rooms.get(i).getName() + "]] ");  // Mark current player location
            } else {
                System.out.print(map[i] + " ");
            }
        }
        System.out.println();  // Newline after map display
    }

    // Show the rooms the player has visited
    public void showProgress() {
        System.out.println("Rooms you have visited:");
        for (String room : uniqueVisitedRooms) {
            System.out.println(room);
        }
    }

    private void showCurrentRoom() {
        Room currentRoom = rooms.get(currentRoomIndex);
        System.out.println("You are in: " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());

        uniqueVisitedRooms.add(currentRoom.getName()); // Add the current room to unique visited rooms

        // Trigger special event if the room is marked as special
        if (currentRoom.isSpecial()) {
            System.out.println("A special event occurs here! This could be a fight, story dialogue, or other unique event.");
            // initiate a battle or dialogue based on the game design
            // Example: start a battle or display story
            triggerSpecialEvent(currentRoom);
        }

        // Check if the player has visited enough rooms to escape
        if (uniqueVisitedRooms.size() >= MAX_ROOMS_TO_ESCAPE) {
            System.out.println("You have visited enough rooms to escape the city!");
            System.exit(0);  // Exit or trigger escape
        }
    }

    // Define a method to trigger special events
    private void triggerSpecialEvent(Room room) {
        if (room.getName().equals("Ghost Terminal")) {
            System.out.println("You encounter ArcTech enforcers! Prepare for battle.");
            Monster monster = new Monster("ArcTech Enforcer");
            // Add battle logic (possibly call CombatLogic here)
            CombatLogic combat = new CombatLogic(player, monster);
            combat.startFight();
        } else if (room.getName().equals("Aether Nexus")) {
            System.out.println("The final confrontation with the Council of Circuits awaits...");
            // Add final battle or story logic
        }
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

    /*public void move(String input) {
        try {
            char value = input.toLowerCase().charAt(0);  // character is a byte that represents a letter

            switch (value) {
                case 'n':
                    if (location == scenes.size() - 1) {
                        if (!keyFound) {
                            System.out.println("You need to find the key first!");
                        } else {
                            System.out.println("You have escaped the maze!");
                            moves.push("escaped");

                        }
                        return;
                    }
                    moves.push("north");
                    location++;   //location += 1
                    break;

                case 's':
                    if (location == 0) {
                        System.out.println("You can't go back to the bar. it's closed");
                        return;
                    }
                    moves.push("south");
                    location--;
                    break;
                case 'l':
                    search();
                    break;
                case 'm':
                    System.out.println("You have moved" + moves.size());
                    moves.forEach(i -> {
                        System.out.println(i);
                    });
                    break;
                case 'p':
                    moves.pop();
                    break;
                case 't':
                    System.out.println(moves.peek());
                    break;
                case 'r':
                    location = 0;
                    moves.clear();
                    keyFound = false;
                    System.out.println("You have reset the game");
                    break;
                case 'b':
                    CombatLogic combat = new CombatLogic(new Player("Hero"), new Monster("Skeleton"));
                    combat.startFight();
                default:
                    System.out.println("What did you mean? Invalid move");
                    break;
            }


        } catch (Exception ex) {
            // you in business store it into an Error Log File
            System.out.println(ex.getMessage());
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



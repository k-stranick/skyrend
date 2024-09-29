package dtcc.itn262.dungeon;

import java.util.Stack;

public class Movement {

    private Stack<String> moves = new Stack<>(); // stack of moves

    public boolean move(Room currentRoom, String direction) {
        try {
            direction = direction.toLowerCase();  // character is a byte that represents a letter

            switch (direction) {
                case "north":
                    if (currentRoom.getN() != -1) {
                        moves.push("north");
                        return true;
                    } else {
                        System.out.println("You cannot go north from here!");
                        return false;
                    }
                case "south":
                    if (currentRoom.getS() != -1) {
                        moves.push("south");
                        return true;
                    } else {
                        System.out.println("You cannot go south from here!");
                        return false;
                    }
                case "east":
                    if (currentRoom.getE() != -1) {
                        moves.push("east");
                        return true;
                    } else {
                        System.out.println("You cannot go east from here!");
                        return false;
                    }
                case "west":
                    if (currentRoom.getW() != -1) {
                        moves.push("west");
                        return true;
                    } else {
                        System.out.println("You cannot go west from here!");
                        return false;
                    }
                default:
                    System.out.println("What did you mean? Invalid move use north, south, east, west");
                    return false;
            }
        } catch (Exception ex) {
            // you in business store it into an Error Log File
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public String undoMove() {
        if (moves.isEmpty()) {
            System.out.println("You have not made any moves yet!");
            return "";
        } else {
            return moves.pop();
        }
    }
}


/*
                case "battle":
CombatLogic combat = new CombatLogic(new Player("Hero"), new Monster("Skeleton"));
                    combat.startFight();*/

package dtcc.itn262.utilities.gamecore;

public enum Command {
    // Enum constants for various game commands
    EXIT("exit"),
    HELP("help"),
    MAP("map"),
    PROGRESS("progress"),
    HISTORY("history"),
    SEARCH("search"),
    NORTH("north", "n"),
    SOUTH("south", "s"),
    EAST("east", "e"),
    WEST("west", "w");

    private final String fullName;
    private final String shortName;

    Command(String fullName) {
        this(fullName, fullName);
    }

    Command(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public static Command fromString(String input) {
        for (Command command : Command.values()) {
            if (command.fullName.equalsIgnoreCase(input) || command.shortName.equalsIgnoreCase(input)) {
                return command;
            }
        }
        return null;
    }
}
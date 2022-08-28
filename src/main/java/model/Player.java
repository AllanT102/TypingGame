package model;

// Represents the player account that has a name, and scoreboard
public class Player {
    private static Player thePlayer;
    private String name;
    private Scoreboard sb;

    // REQUIRES: length of name cannot be zero
    // EFFECTS: Constructs a new Player with given name, empty scoreboard
    private Player(String name) {
        this.name = name;
        this.sb = new Scoreboard();
    }

    public static boolean playerExist() {
        return !(thePlayer == null);
    }

    public static Player getPlayerInstance(String name) {
        if (thePlayer == null) {
            thePlayer = new Player(name);
        }
        return thePlayer;
    }

    public String getName() {
        return name;
    }

    public Scoreboard getScoreboard() {
        return sb;
    }

    // MODIFIES: this
    // EFFECTS: sets the player's scoreboard to be the given scoreboard
    public void setScoreboard(Scoreboard sb) {
        this.sb = sb;
    }

}

package model;

import java.util.ArrayList;

// Represents the player account that has a name, and scoreboard
public class Player {

    private String name;
    private Scoreboard sb;

    // REQUIRES: length of name cannot be zero
    // EFFECTS: Constructs a new Player with given name, empty scoreboard
    public Player(String name) {
        this.name = name;
        sb = new Scoreboard();
    }

    public String getName() {
        return name;
    }

    public Scoreboard getScoreboard() {
        return sb;
    }

//    // EFFECTS : gets list of top 5 scores and returns it
//    public ArrayList<Score> getScoresAsList() {
//        return sb.getScoreboardAsList();
//    }
}

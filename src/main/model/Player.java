package model;

import java.util.ArrayList;
import java.util.List;


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
        return null;
    }


    // MODIFIES: this
    // EFFECTS: sets this player's name to given name
    public void setName() {
    }

    // EFFECTS : gets list of top 5 scores and returns it
    public ArrayList<Score> getScoresAsList() {
        return sb.getScoreboardAsList();
    }

    // EFFECTS: gets string of top 5 scores and returns it
    public String getScoresAsString() {
        return sb.getScoreboardAsString();
    }
}

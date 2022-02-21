package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.Writable;

import java.util.ArrayList;

// Represents the player account that has a name, and scoreboard
public class Player implements Writable {

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put(name.toString(), sbToJson());
        return json;
    }

    public JSONArray sbToJson() {
        JSONArray jsArray = new JSONArray();

        for (Score s : sb.getScoreboardAsList()) {
            jsArray.put(s.toJson());
        }
        return jsArray;
    }


//    // EFFECTS : gets list of top 5 scores and returns it
//    public ArrayList<Score> getScoresAsList() {
//        return sb.getScoreboardAsList();
//    }
}

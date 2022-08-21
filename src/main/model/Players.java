package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a list of player accounts
public class Players {
    private HashMap<String, Scoreboard> playerMap;

    // EFFECTS: constructs an empty list of players
    public Players() {
        playerMap = new HashMap<>();
    }

    // EFFECTS: returns player at given index
    public boolean contains(String name) {
        return playerMap.containsKey(name);
    }

    // MODIFIES: this
    // EFFECTS: adds player to the end of the list
    public void addPlayer(String name, Scoreboard info) {
        playerMap.put(name, info);
    }

    public int size() {
        return playerMap.size();
    }

    public Scoreboard getSb(String name) {
        if (playerMap.containsKey(name)) {
            return playerMap.get(name);
        } else {
            return null;
        }
    }

    // Parts of method taken from workroom class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns playerList as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, Scoreboard> entry : playerMap.entrySet()) {
            JSONObject json = new JSONObject();
            json.put("Name", entry.getKey());
            json.put(entry.getKey(), sbToJson(entry.getValue()));
            jsonArray.put(json);
        }
        return jsonArray;
    }

    public JSONArray sbToJson(Scoreboard sb) {
        JSONArray jsArray = new JSONArray();

        for (Score s : sb.getScoreboardAsList()) {
            jsArray.put(s.toJson());
        }
        return jsArray;
    }
}

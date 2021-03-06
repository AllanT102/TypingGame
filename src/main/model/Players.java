package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// Represents a list of player accounts
public class Players {
    private List<Player> playerList;

    // EFFECTS: constructs an empty list of players
    public Players() {
        playerList = new ArrayList<>();
    }

    // EFFECTS: returns player at given index
    public Player getPlayer(int i) {
        return playerList.get(i);
    }

    // EFFECTS: returns size of array
    public int length() {
        return playerList.size();
    }


    // MODIFIES: this
    // EFFECTS: adds player to the end of the list
    public void addPlayer(Player p) {
        playerList.add(p);
    }

    // Parts of method taken from workroom class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns playerList as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : playerList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}

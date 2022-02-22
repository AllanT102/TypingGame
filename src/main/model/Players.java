package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of player accounts
public class Players {
    private List<Player> playerList;

    // EFFECTS: constructs an empty list of players
    public Players() {
        playerList = new ArrayList<Player>();
    }
}

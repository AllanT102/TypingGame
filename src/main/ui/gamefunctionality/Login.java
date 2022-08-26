package ui.gamefunctionality;

import model.Player;
import model.Players;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

// represents class that deals with all login aspects
public class Login {

    private static Login login;
    private static final String JSON_DATA = "./data/player.json";
    private Players players;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // constructs new login object and reads JSON data to load all player data
    private Login() {
        jsonReader = new JsonReader(JSON_DATA);
        jsonWriter = new JsonWriter(JSON_DATA);
        try {
            players = jsonReader.getAllPlayers();
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
    }

    public static Login getInstance() {
        if (login == null) {
            login = new Login();
        }
        return login;
    }

    // MODIFIES: this
    // EFFECTS: saves user data ie. player information
    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write();
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to read file!");
        }
    }

    // MODIFIES: this
    // EFFECTS: returns whether or not the name is valid
    public boolean signIn(String existingName) {
        try {
            Player p = loadPlayer(existingName);
            return p != null ? true : false;
        } catch (IOException e) {
            System.out.println("Error when loading players.");
            return false;
        } catch (JSONException e) {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates player account, checks if player name is already taken, and adds to list of players
    public Boolean signUp(String newName) {
        if (nameIsValid(newName)) {
            Player.getPlayerInstance(newName);
            return true;
        } else {
            Player.getPlayerInstance(newName);
            return false;
        }
    }

    // EFFECTS: checks if name is already in allPlayers
    public Boolean nameIsValid(String name) {
        return !players.contains(name);
    }

    // MODIFIES: this
    // EFFECTS: loads all players stored in JSON_DATA, and adds each individual player to allPlayers
    public Player loadPlayer(String name) throws IOException, JSONException {
        Player p = jsonReader.read(name);
        return p;
//        this.allPlayers = jsonReader.getAllPlayers();
    }

    public Player getPlayer() {
        return Player.getPlayerInstance("");
    }
}

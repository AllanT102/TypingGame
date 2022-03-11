package ui;

import model.Player;
import model.Players;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;


public class Login {
    private static final String JSON_DATA = "./data/player.json";
    private Players allPlayers;
    private Player player;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private LoadInScreen loadInScreen;

    public Login() {
        jsonReader = new JsonReader(JSON_DATA);
        jsonWriter = new JsonWriter(JSON_DATA);
        allPlayers = new Players();
        loadData();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void loadData() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.allPlayers);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to read file!");
        }
    }

    // MODIFIES: this, team
    // EFFECTS: sets loadInScreen
    public void setLoadInScreen(LoadInScreen loadInScreen) {
        if (this.loadInScreen != loadInScreen) {
            removeLoadInScreen();
            this.loadInScreen = loadInScreen;
            this.loadInScreen.setLogin(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    public void removeLoadInScreen() {
        if (this.loadInScreen != null) {
            LoadInScreen oldLIS = this.loadInScreen;
            this.loadInScreen = null;
            oldLIS.removeLogin();
        }
    }


    // MODIFIES: this
    // EFFECTS: displays player creation menu for user
    public void signIn(String existingName) {
        if (loadPlayerData(existingName)) {
            loadPlayerData(existingName);
            loadInScreen.makeLoginMessage("Login, successful!");
            System.out.println("player found");
        } else {
            loadInScreen.makeLoginMessage("Login failed, try again!");
            System.out.println("player not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates player account, checks if player name is already taken
    public void signUp(String newName) {

        while (!nameIsValid(newName)) {
            // DONT KNow whAT to do here yet
        }
        player = new Player(newName);
        allPlayers.addPlayer(player);
    }

    // EFFECTS: checks if name is already in allPlayers
    public Boolean nameIsValid(String name) {
        for (int i = 0; i < allPlayers.length(); i++) {
            if (name.equals(allPlayers.getPlayer(i).getName())) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: tries to find matching player name in allPlayers
    public Boolean loadPlayerData(String name) {
        for (int i = 0; i < allPlayers.length(); i++) {
            String plName = allPlayers.getPlayer(i).getName();
            if (name.equals(plName)) {
                this.player = allPlayers.getPlayer(i);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: loads all players stored in JSON_DATA, and adds each individual player to allPlayers
    public void loadAllPlayers() throws IOException, JSONException {
        jsonReader.read();
        this.allPlayers = jsonReader.getAllPlayers();
    }
}

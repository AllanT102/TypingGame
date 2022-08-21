package ui.gamefunctionality;

import model.Player;
import model.Players;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.LoadInScreenPanel;
import ui.panels.SignUpScreenPanel;
import ui.panels.TypingGamePanel;

import java.io.IOException;

// represents class that deals with all login aspects
public class Login {

    private static Login login;
    private static final String JSON_DATA = "./data/player.json";
    private Players players;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;
    private TypingGamePanel gamePanel;

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

    // MODIFIES: this, team
    // EFFECTS: sets loadInScreen
    public void setLoadInScreen(LoadInScreenPanel loadInScreen) {
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
            LoadInScreenPanel oldLIS = this.loadInScreen;
            this.loadInScreen = null;
            oldLIS.removeLogin();
        }
    }

    // MODIFIES: this, team
    // EFFECTS: sets signUpScreen
    public void setSignUpScreen(SignUpScreenPanel signUpScreen) {
        if (this.signUpScreen != signUpScreen) {
            removeSignUpScreen();
            this.signUpScreen = signUpScreen;
            this.signUpScreen.setLogin(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    public void removeSignUpScreen() {
        if (this.signUpScreen != null) {
            SignUpScreenPanel oldLIS = this.signUpScreen;
            this.signUpScreen = null;
            oldLIS.removeLogin();
        }
    }

    // MODIFIES: this, team
    // EFFECTS: sets gamePanel
    public void setGamePanel(TypingGamePanel gamePanel) {
        if (this.gamePanel != gamePanel) {
            removeGamePanel();
            this.gamePanel = gamePanel;
            this.gamePanel.setLogin(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes gamePanel
    public void removeGamePanel() {
        if (this.gamePanel != null) {
            TypingGamePanel oldG = this.gamePanel;
            this.gamePanel = null;
            oldG.removeLogin();
        }
    }


    // MODIFIES: this
    // EFFECTS: returns whether or not the name is valid
    public boolean signIn(String existingName) {
        try {
            Player p = loadPlayer(existingName);
            if (p != null) {
                return true;
            } else {
                loadInScreen.makeLoginMessage("Login failed, try again!");
                return false;
            }
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
        if (!nameIsValid(newName)) {
            return false;
        } else {
            Player.getPlayerInstance(newName);
            return true;
        }
    }

    // EFFECTS: checks if name is already in allPlayers
    public Boolean nameIsValid(String name) {
        return players.contains(name);
    }

//    // MODIFIES: this
//    // EFFECTS: tries to find matching player name in allPlayers
//    public Boolean loadPlayerData(String name) {
//        for (int i = 0; i < allPlayers.length(); i++) {
//            String plName = allPlayers.getPlayer(i).getName();
//            if (name.equals(plName)) {
//                this.player = allPlayers.getPlayer(i);
//                return true;
//            }
//        }
//        return false;
//    }

    // MODIFIES: this
    // EFFECTS: loads all players stored in JSON_DATA, and adds each individual player to allPlayers
    public Player loadPlayer(String name) throws IOException, JSONException {
        Player p = jsonReader.read(name);
        return p;
//        this.allPlayers = jsonReader.getAllPlayers();
    }

    public Players getAllPlayers() {
        return players;
    }

    public Player getPlayer() {
        return Player.getPlayerInstance("");
    }
}

package persistence;


import model.Player;
import model.Players;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.*;
import java.nio.charset.StandardCharsets;


// Represents a writer that writes JSON representation of the player to file
public class JsonWriter {
    private static final int TAB = 4;
    private FileWriter writer;
    private String destination;
    private Players players;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS opens writer; throws IOException if destination file cannot be opened
    public void open() throws IOException {
        JsonReader reader = new JsonReader(this.destination);
        Player playerData = Player.getPlayerInstance("");
        this.players = reader.getAllPlayers();
        this.players.addPlayer(playerData.getName(), playerData.getScoreboard());

        writer = new FileWriter(this.destination, false);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of player to file
    // write9) is
    public void write() throws IOException {
        JSONArray json = this.players.toJson();
        saveToFile(json.toString(TAB));
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() throws IOException {
        writer.close();
    }


    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) throws IOException {
        writer.write(json);
    }
}
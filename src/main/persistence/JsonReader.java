package persistence;

import model.Player;
import model.Players;
import model.Score;
import model.Scoreboard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads player from JSON data stored in file
public class JsonReader {
    private String source;
    private Players allPlayers;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        this.allPlayers = new Players();
    }

    // EFFECTS: returns allPlayers in JSON_DATA
    public Players getAllPlayers() {
        return this.allPlayers;
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads player from file and returns it and throws IOException if an error occurs while reading data
    public Players read() throws IOException, JSONException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsePlayers(jsonArray);
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException, JSONException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // MIGHT NEED TO INPUT THE STRING NAME INSTEAD SO THAT WE CAN SAVE MULTIPLE PLAYERS! AND ALSO STORE SCORES
    // WITH KEY VALUE OF NAME
    // EFFECTS: parses players from JSON object and returns it
    private Players parsePlayers(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject playerJson = jsonArray.getJSONObject(i);
            String name = playerJson.getString("Name");
            Player p = new Player(name);
            addScores(p, playerJson, name);
            allPlayers.addPlayer(p);
        }
        return allPlayers;
    }

    // MODIFIES: p
    // EFFECTS: parses scores from JSON object and adds them to player
    private void addScores(Player p, JSONObject jsonObject, String name) {
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        for (Object json : jsonArray) {
            JSONObject score = (JSONObject) json;
            p.getScoreboard().addScore(makeScore(score, name));
        }
    }

    // MODIFIES: p
    // EFFECTS: parses score from JSON object and returns an instance of the score
    private Score makeScore(JSONObject jsonObject, String name) {
        double keyNameScore = jsonObject.getDouble("Score");
        double keyNameAcc = jsonObject.getDouble("Accuracy");
        Score score = new Score();
        score.setScore(keyNameScore);
        score.setAcc(keyNameAcc);
        return score;
    }

}

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

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads player from file and returns it and throws IOException if an error occurs while reading data
    public Player read(String name) throws IOException, JSONException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsePlayers(jsonArray, name);
    }

    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException, JSONException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public Players getAllPlayers() throws IOException {
        String jsonData = readFile(source);
        if (jsonData.equals("")) {
            jsonData = "[]";
        }
        JSONArray jsonArray = new JSONArray(jsonData);
        Players players = new Players();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject playerJson = jsonArray.getJSONObject(i);
            String name = playerJson.getString("Name");
            Scoreboard sb = getScoreboard(playerJson, name);
            players.addPlayer(name, sb);
        }
        return players;
    }

    // MIGHT NEED TO INPUT THE STRING NAME INSTEAD SO THAT WE CAN SAVE MULTIPLE PLAYERS! AND ALSO STORE SCORES
    // WITH KEY VALUE OF NAME
    // EFFECTS: parses players from JSON object and returns it
    private Player parsePlayers(JSONArray jsonArray, String playerName) {
        Player p = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject playerJson = jsonArray.getJSONObject(i);
            String name = playerJson.getString("Name");
            if (name.equals(playerName)) {
                p = Player.getPlayerInstance(name);
                addScores(p, playerJson, name);
                break;
            }
        }
        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses scores from JSON object and adds them to player
    private void addScores(Player p, JSONObject jsonObject, String name) {
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        for (Object json : jsonArray) {
            JSONObject score = (JSONObject) json;
            p.getScoreboard().addScore(makeScore(score));
        }
    }

    // MODIFIES: p
    // EFFECTS: parses score from JSON object and returns an instance of the score
    private Score makeScore(JSONObject jsonObject) {
        double keyNameScore = jsonObject.getDouble("Score");
        double keyNameAcc = jsonObject.getDouble("Accuracy");
        Score score = new Score();
        score.setScore(keyNameScore);
        score.setAcc(keyNameAcc);
        return score;
    }

    private Scoreboard getScoreboard(JSONObject jsonObject, String name) {
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        Scoreboard sb = new Scoreboard();
        for (Object json : jsonArray) {
            JSONObject score = (JSONObject) json;
            sb.addScore(makeScore(score));
        }
        return sb;
    }

}

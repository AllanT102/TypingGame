package persistence;

import model.Player;
import model.Score;
import model.Scoreboard;
import org.json.JSONArray;
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
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // MIGHT NEED TO INPUT THE STRING NAME INSTEAD SO THAT WE CAN SAVE MULTIPLE PLAYERS! AND ALSO STORE SCORES
    // WITH KEY VALUE OF NAME
    // EFFECTS: parses workroom from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        Player p = new Player(name);
        addScores(p, jsonObject, name);
        return p;
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

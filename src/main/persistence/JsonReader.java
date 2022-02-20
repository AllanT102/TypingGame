package persistence;

import com.sun.org.apache.xpath.internal.operations.String;
import model.Player;
import org.json.JSONObject;

import java.io.IOException;


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
    public String readFile(String s) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();


    }

}

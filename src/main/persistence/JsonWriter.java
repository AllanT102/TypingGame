package persistence;


import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.*;
import java.nio.charset.StandardCharsets;


// Represents a writer that writes JSON representation of the player to file
public class JsonWriter {
    private static final int TAB = 4;
    private FileWriter writer;
    private JsonReader jsonReader;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS opens writer; throws FileNotFoundException if destination file cannot be opened
    public void open() throws FileNotFoundException, IOException {
        writer = new FileWriter(this.destination, true);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of player to file
    public void write(Player p, JSONArray jsonArray) throws IOException {
        JSONObject json = p.toJson();
        jsonArray.put(json);

        saveToFile(jsonArray.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() throws IOException {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String jsonArray) throws IOException {
        jsonReader = new JsonReader(destination);
        String initialFileData = jsonReader.readFile(destination);
        writer.write(jsonArray);
//        writer.write(initialFileData + json);
    }
}

// SITE THIS a;sldjf;lasjd;flja;sldfj;alskdfj;lasdjfasdf
// store json data as an array and acess each element to find player
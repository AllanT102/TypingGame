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

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS opens writer; throws IOException if destination file cannot be opened
    public void open() throws IOException {
        writer = new FileWriter(this.destination, false);
    }

    // Parts of method taken from JsonWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes JSON representation of player to file
    public void write(Players p) throws IOException {
        JSONArray json = p.toJson();
        saveToFile(json.toString(TAB));
    }

    // Parts of method taken from JsonWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() throws IOException {
        writer.close();
    }

    // Parts of method taken from JsonWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) throws IOException {
        writer.write(json);
    }
}
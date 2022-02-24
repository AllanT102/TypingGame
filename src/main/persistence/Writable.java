package persistence;

import org.json.JSONObject;

public interface Writable {

    // Method taken from Writable interface in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

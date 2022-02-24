package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

// Represents an individual score that is achieved by the user after playing one typing game
public class Score implements Writable {
    private double plScore;
    private double acc;
    private String results;

    // EFFECTS: constructs a score with initial score of 0;
    public Score() {
        this.plScore = 0;
    }

    public String getResults() {
        return this.results;
    }

    public double getScore() {
        return this.plScore;
    }

    public double getAcc() {
        return this.acc;
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: sets score to num, for tests
    public void setScore(double num) {
        this.plScore = num;
    }

    // REQUIRES: accuracy  >= 0
    // MODIFIES: this
    // EFFECTS: sets accuracy to acc
    public void setAcc(double acc) {
        this.acc = acc;
    }

    // REQUIRES: acc must be >= 0
    // MODIFIES: this
    // EFFECTS: calculates score from given run by the product of
    //          percentage accuracy * 1000
    public void calculateScore(double acc) {
        this.plScore = acc * 1000;
    }

    // REQUIRES: tl and tc must be > 0, and tl >= tc
    // MODIFIES: this
    // EFFECTS: calculates percentage accuracy based off of how many matching letters were typed correctly
    public void calculateAccuracy(double tl, double tc) {
        this.acc = Math.round(tc / tl * 100);
    }


    // EFFECTS: sets results
    public void setResults() {
        this.results = new String("Score: " + getScore()
                + " Accuracy: " + getAcc() + "%");
    }

    // Parts of method taken from Thingy class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Score", plScore);
        json.put("Accuracy", acc);
        return json;
    }
}

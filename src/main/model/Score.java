package model;

public class Score {

    private int plScore;
    private int acc;
    private String results;

    // EFFECTS: constructs a score with initial score of 0;
    public Score() {
        this.plScore = 0;
    }

    // MODIFIES: this
    // EFFECTS: calculates score from given run by the product of
    //          percentage accuracy * 1000
    public void calculateScore(int acc) {
        this.plScore = acc * 1000;
    }

    // MODIFIES: this
    // EFFECTS: calculates percentage accuracy based off of how many matching letters were typed correctly
    public void calculateAccuracy(int tl, int tc) {
        this.acc = tc / tl * 100;
    }

    // REQUIRES: must have existing score and accuracy
    // EFFECTS: prints out a string that displays score and accuracy
    public String printResults() {
        results = new String("Score: " + getScore()
                + "Accuracy: " + getAcc());
        return results;
    }

    public int getScore() {
        return this.plScore;
    }

    public int getAcc() {
        return this.acc;
    }


}

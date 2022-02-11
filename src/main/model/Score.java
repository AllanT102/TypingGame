package model;

// represents an individual score that is achieved by the user after playing one typing game
public class Score {

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

    // MODIFIES: this
    // EFFECTS: sets score to num, for tests
    public void setScore(double num) {
        this.plScore = num;
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

    // REQUIRES: must have existing score and accuracy
    // EFFECTS: sets results
    public void setResults() {
        this.results = new String("Score: " + getScore()
                + " Accuracy: " + getAcc() + "%");
    }



}

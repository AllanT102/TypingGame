package model;

public class Score {

    int plScore;
    int acc;
    String results;

    // EFFECTS: constructs a score with initial score of 0;
    public Score() {
        this.plScore = 0;
    }

    // MODIFIES: this
    // EFFECTS: calculates score from given run by the product of
    //          time it took to type paragraph and percentage accuracy * 1000
    public void calculateScore(int time, int acc) {
        this.plScore = time * acc * 1000;
    }

    // MODIFIES: this
    // EFFECTS: calculates percentage accuracy based off of how many matching letters were typed correctly
    public void calculateAccuracy(int tl, int tc) {
        this.acc = tc / tl * 100;
    }

    // REQUIRES: must have existing score and accuracy
    // EFFECTS: prints out a string that displays score and accuracy
    public String printResults() {
        results = new String("Nice job! You got a score of " + getScore()
                + ", and you had an accuracy of " + getAcc());
        return results;
    }

    public int getScore() {
        return this.plScore;
    }

    public int getAcc() {
        return this.acc;
    }


}

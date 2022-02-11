package model;

import java.util.ArrayList;


// Represents the scoreboard that is attached to each player's account,
// keeps a list of previous scores user has achieved
public class Scoreboard {

    private ArrayList<Score> sb;

    // Constructs a scoreboard that initially has an empty list
    public Scoreboard() {
        sb = new ArrayList<>();
    }

    public int getLength() {
        return sb.size();
    }

    public ArrayList<Score> getScoreboardAsList() {
        return sb;
    }


    // MODIFIES: this
    // EFFECTS: resets the scoreboard
    public void resetScoreboard() {
        sb.clear();
    }

    // MODIFIES: this
    // EFFECTS: adds score to scoreboard (list of previous scores) and inserts it in the right spot in descending order
    //          and returns true
    public Boolean addScore(Score s) {
        for (int i = 0; i < sb.size(); i++) {
            if (s.getScore() >= sb.get(i).getScore()) {
                sb.add(i, s);
                return true;
            }
        }
        sb.add(s);
        return true;
    }

    // EFFECTS: returns the scoreboard as a string
    public String convertScoreboardToString() {
        String sbAsString = "Scoreboard: \n";
        for (int i = 0; i < 5; i++) {
            if (sb.size() - 1 < i) {
                return sbAsString;
            } else {
                sbAsString = sbAsString + (i + 1) + ". " + sb.get(i).getScore() + " Points \n";
            }
        }
        return sbAsString;
    }


}

package model;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    ArrayList<Score> sb;
    String sbAsString;

    // Constructs a scoreboard that is initially empty
    public Scoreboard() {
        sb = new ArrayList<>();
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

    public ArrayList<Score> getScoreboardAsList() {
        return sb;
    }

    public String getScoreboardAsString() {
        sbAsString = new String("Scoreboard: ");
        for (int i = 0; i < 6; i++) {
            String newScore = new String(i + ". " + sb.get(i).getScore() + " Points ");
            sbAsString.concat(newScore);
        }
        return sbAsString;
    }


}

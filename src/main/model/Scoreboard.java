package model;

import java.util.ArrayList;
import java.util.Objects;


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

    public Score get(int i) {
        return sb.get(i);
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
    public String convertScoreboardToString(int i) {
        String sbAsString = "";
        sbAsString = sbAsString + (i + 1) + ". " + sb.get(i).getScore() + " Points, "
                + sb.get(i).getAcc() + "% Accuracy";
        return sbAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scoreboard that = (Scoreboard) o;
        for (int i = 0; i < sb.size(); i++) {
            if (i >= that.sb.size()) return false;
            if (!sb.get(i).equals(that.sb.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sb);
    }
}

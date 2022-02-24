package persistence;

import model.Player;
import model.Score;
import model.Scoreboard;

import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Represents general tests for JSON readers/writers
public class JsonTest {

    // EFFECTS: Checks if player has correct name and scoreboard
    protected void checkPlayer(Player p, String name, Scoreboard sb) {
        assertEquals(name, p.getName());
        assertTrue(checkScoreboard(p, sb));
    }

    // EFFECTS: checks each score within the scoreboard to see if scores match
    protected Boolean checkScoreboard(Player p, Scoreboard sb) {
        for (int i = 0; i < max(p.getScoreboard().getLength(), sb.getLength()); i++) {
            try {
                if (getScoreAtIndex(i, p.getScoreboard()).getScore() != getScoreAtIndex(i, sb).getScore()
                        || getScoreAtIndex(i, p.getScoreboard()).getAcc() != getScoreAtIndex(i, sb).getAcc()) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: gets the score at certain index
    private Score getScoreAtIndex(int i, Scoreboard sb) {
        return sb.getScoreboardAsList().get(i);
    }

}

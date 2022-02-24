package model;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    Scoreboard sb;
    Scoreboard sbLbj;
    Scoreboard sbKb;
    Scoreboard sbCurr;
    Score s1;
    Score s2;
    Score s3;
    Score s4;
    Score s5;

    @BeforeEach
    void runBefore() {
        sb = new Scoreboard();
        sbLbj = new Scoreboard();
        sbKb = new Scoreboard();
        sbCurr = new Scoreboard();
        s1 = new Score();
        s2 = new Score();
        s3 =  new Score();
        s4 = new Score();
        s5 = new Score();

        s1.setAcc(100);
        s1.setScore(100000);
        s2.setAcc(14);
        s2.setScore(41000);
        s3.setAcc(3);
        s3.setScore(3000);
        s4.setAcc(41);
        s4.setScore(1234);
        s5.setAcc(41);
        s5.setScore(41000);

        sb.addScore(s1);
        sb.addScore(s2);
        sb.addScore(s3);

        sbLbj.addScore(s4);
        sbLbj.addScore(s2);

        sbKb.addScore(s2);
        sbKb.addScore(s1);

        sbCurr.addScore(s5);
        sbCurr.addScore(s4);

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./NoSuchFile");
        try {
            Players players = reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        } catch (JSONException e) {
            fail("IOException was expected");
        }
    }

    @Test
    void testEmptyPlayer() {
        JsonReader reader = new JsonReader("./data/testReadPlayerEmpty.json");
        try {
            Players p = reader.read();
            assertEquals(0, p.length());
            fail("JSONException was expected.");
        } catch (IOException e) {
            fail("No IOException expected, couldn't read file");
        } catch (JSONException e) {
            // pass
        }
    }

    @Test
    void testReaderOnePlayer() {
        JsonReader reader = new JsonReader("./data/testReadOnePlayer.json");
        try {
            Players p = reader.read();
            assertEquals(1, p.length());
            checkPlayer(p.getPlayer(0), "Allan", sb);
            assertEquals(p, reader.getAllPlayers());
        } catch (IOException e) {
            fail("No IOException expected, couldn't read file");
        } catch (JSONException e) {
            fail("No JSONException expected, error occurred when reading file.");
        }
    }

    @Test
    void testReadMultiplePlayers() {
        JsonReader reader = new JsonReader("./data/testReadMultiplePlayers.json");
        try {
            Players p = reader.read();
            assertEquals(4, p.length());
            checkPlayer(p.getPlayer(0), "Allan", sb);
            checkPlayer(p.getPlayer(1), "Lebron James", sbLbj);
            checkPlayer(p.getPlayer(2), "Kobe Bryant", sbKb);
            checkPlayer(p.getPlayer(3), "Stephen Curry", sbCurr);

            // testing checkScoreboard
            assertTrue(checkScoreboard(p.getPlayer(0), sb));
            assertFalse(checkScoreboard(p.getPlayer(0), sbLbj));
            assertFalse(checkScoreboard(p.getPlayer(1), sbKb));
            assertFalse(checkScoreboard(p.getPlayer(2), sb));
            assertFalse(checkScoreboard(p.getPlayer(1), sbCurr));
            assertFalse(checkScoreboard(p.getPlayer(2), sbCurr));

        } catch (IOException e) {
            fail("No IOException expected, couldn't read file");
        } catch (JSONException e) {
            fail("No JSONException expected, error occurred when reading file.");
        }
    }


}

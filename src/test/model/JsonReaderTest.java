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
    Score s1;
    Score s2;
    Score s3;

    @BeforeEach
    void runBefore() {
        sb = new Scoreboard();

        s1 = new Score();
        s1.setAcc(100);
        s1.setScore(100000);
        sb.addScore(s1);

        s2 = new Score();
        s2.setAcc(41);
        s2.setScore(41000);
        sb.addScore(s2);

        s3 =  new Score();
        s3.setAcc(3);
        s3.setScore(3000);
        sb.addScore(s3);

        sbLbj = new Scoreboard();
        sbLbj.addScore(s1);
        sbLbj.addScore(s2);

        sbKb = new Scoreboard();
        sbKb.addScore(s2);
        sbKb.addScore(s3);

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
            assertEquals(3, p.length());
            checkPlayer(p.getPlayer(0), "Allan", sb);
            checkPlayer(p.getPlayer(1), "Lebron James", sbLbj);
            checkPlayer(p.getPlayer(2), "Kobe Bryant", sbKb);
            assertTrue(checkScoreboard(p.getPlayer(0), sb));
            assertFalse(checkScoreboard(p.getPlayer(0), sbLbj));
            assertFalse(checkScoreboard(p.getPlayer(1), sbKb));
        } catch (IOException e) {
            fail("No IOException expected, couldn't read file");
        } catch (JSONException e) {
            fail("No JSONException expected, error occurred when reading file.");
        }
    }


}

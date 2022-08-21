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
            Player players = reader.read("");
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
            Players p = reader.getAllPlayers();
            assertEquals(0, p.size());
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
            Players players = reader.getAllPlayers();
            assertEquals(1, players.size());
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
            Players players = reader.getAllPlayers();
            assertEquals(4, players.size());
            assertEquals(players.getSb("Allan"), sb);
            assertEquals(players.getSb("Lebron James"), sbLbj);
            assertEquals(players.getSb("Kobe Bryant"), sbKb);
        } catch (IOException e) {
            fail("No IOException expected, couldn't read file");
        } catch (JSONException e) {
            fail("No JSONException expected, error occurred when reading file.");
        }
    }

    @Test
    void testRead() {
        JsonReader reader = new JsonReader("./data/testReadMultiplePlayers.json");
        try {
            Player p = reader.read("Lebron James");
            assertEquals(p, Player.getPlayerInstance(""));
        } catch (Exception e) {
            fail("No exception expected");
        }
    }

    @Test
    void testReadNoMatch() {
        JsonReader reader = new JsonReader("./data/testReadMultiplePlayers.json");
        try {
            Player p = reader.read("dong");
            assertNull(p);
        } catch (Exception e) {
            fail("No exception expected");
        }
    }


}

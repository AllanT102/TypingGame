package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    Player p1;
    Player p2;
    Player p3;
    Scoreboard sb;
    Scoreboard sbLbj;
    Scoreboard sbKb;
    Score s1;
    Score s2;
    Score s3;

    @BeforeEach
    void runBefore() {
        p1 = new Player("Allan");
        p2 = new Player("Lebron James");
        p3 = new Player("Kobe Bryant");

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

        p1.setScoreboard(sb);
        p2.setScoreboard(sbLbj);
        p3.setScoreboard(sbKb);
    }

    @Test
    void testInvalidFileName() {
        try {
            Players p = new Players();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write(p);
            writer.close();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoPlayers() {
        try {
            Players p = new Players();
            JsonWriter writer = new JsonWriter("./data/testWriteNoPlayers.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteNoPlayers.json");
            Players checkP = new Players();
            assertEquals(0, checkP.length());
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    void testWriteOnePlayers() {
        try {
            Players p = new Players();
            p.addPlayer(p1);
            JsonWriter writer = new JsonWriter("./data/testWriteOnePlayers.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteOnePlayers.json");
            Players checkP = reader.read();
            assertEquals(1, checkP.length());
            checkPlayer(checkP.getPlayer(0), "Allan", sb);
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    void testWriteMultiplePlayers() {
        try {
            Players p = new Players();
            p.addPlayer(p1);
            p.addPlayer(p2);
            p.addPlayer(p3);
            JsonWriter writer = new JsonWriter("./data/testWriteMultiplePlayers.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteMultiplePlayers.json");
            Players checkP = reader.read();
            assertEquals(3, checkP.length());
            checkPlayer(checkP.getPlayer(0), "Allan", sb);
            checkPlayer(checkP.getPlayer(1), "Lebron James", sbLbj);
            checkPlayer(checkP.getPlayer(2), "Kobe Bryant", sbKb);
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    void testCheckScoreboard() {
        assertFalse(checkScoreboard(p2, sbKb));
        assertTrue(checkScoreboard(p1, sb));
    }
}

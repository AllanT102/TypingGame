package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    Scoreboard sb;
    Scoreboard sbKb;
    Score s1;
    Score s2;
    Score s3;

    @BeforeEach
    void runBefore() {
        Player.getPlayerInstance("Allan");

        sb = new Scoreboard();
        sbKb = new Scoreboard();

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


        Player.getPlayerInstance("Allan").setScoreboard(sb);
    }

    @Test
    void testInvalidFileName() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write();
            writer.close();
            fail("Exception was expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void testWriteOnePlayers() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteOnePlayers.json");
            writer.open();
            writer.write();
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteOnePlayers.json");
            Players players = reader.getAllPlayers();
            Player checkP = reader.read("Allan");
            assertEquals(1, players.size());
            assertEquals(checkP.getName(), "Allan");
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    void testWriteExistingPlayer() {

    }

    @Test
    void testWriteMultiplePlayers() {

    }


    @Test
    void testCheckScoreboard() {
        assertFalse(checkScoreboard(Player.getPlayerInstance("Jonny"), sbKb));
        assertTrue(checkScoreboard(Player.getPlayerInstance("Allan"), sb));
    }
}

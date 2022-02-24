package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player pTest;
    private Scoreboard sb;
    private Score s1;
    private Score s2;

    @BeforeEach
    void runBefore() {
        pTest = new Player("Allan");

        sb = new Scoreboard();
        s1 = new Score();
        s1.setScore(100);
        s1.setAcc(100);
        sb.addScore(s1);
        s2 = new Score();
        s2.setScore(1000);
        s2.setAcc(1);
        sb.addScore(s2);

    }

    @Test
    void testConstructor() {
        assertEquals("Allan", pTest.getName());
        assertTrue(pTest.getScoreboard().getScoreboardAsList().isEmpty());
    }

    @Test
    void testSetScoreboard() {
        pTest.setScoreboard(sb);
        assertEquals(sb, pTest.getScoreboard());

    }

    @Test
    void testToJson() {
        pTest.setScoreboard(sb);
        assertTrue(pTest.toJson() instanceof JSONObject);
    }
}
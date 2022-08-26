package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {
    private Scoreboard sb1;
    private Scoreboard sb2;
    private Scoreboard sb3;
    private Score s1;
    private Score s2;
    private Score s3;
    private Players p;

    @BeforeEach
    void runBefore() {
        p = new Players();
        sb1 = new Scoreboard();
        sb2 = new Scoreboard();
        sb3 = new Scoreboard();
        s1 = new Score();
        s1.setScore(1000);
        s1.setAcc(1);
        s2 = new Score();
        s2.setScore(2000);
        s2.setAcc(2);
        s3 = new Score();
        s3.setScore(3000);
        s3.setAcc(3);
        sb1.addScore(s1);
        sb2.addScore(s2);
        sb3.addScore(s3);
        sb3.addScore(s2);
        sb3.addScore(s1);
    }

    @Test
    void testConstructor() {
        assertEquals(p.size(), 0);
    }

    @Test
    void testContains() {
        p.addPlayer("Allan", sb1);
        assertTrue(p.contains("Allan"));
        assertFalse(p.contains("cas"));
        assertEquals(p.getSb("Allan"), sb1);
    }

    @Test
    void testToJson() {
        JsonReader reader = new JsonReader("");
        p.addPlayer("Allan", sb1);
        p.addPlayer("Jonny", sb2);
        JSONArray arr = p.toJson();

        assertEquals(arr.length(), 2);
        JSONArray jon = arr.getJSONObject(0).getJSONArray("Jonny");
        JSONObject jonobj = (JSONObject) jon.get(0);
        double jds = jonobj.getDouble("Score");
        double jda = jonobj.getDouble("Accuracy");
        assertEquals(jds, s2.getScore());
        assertEquals(jda, s2.getAcc());

        JSONArray allan = arr.getJSONObject(1).getJSONArray("Allan");
        JSONObject allanobj = (JSONObject) allan.get(0);
        double als = allanobj.getDouble("Score");
        double ala = allanobj.getDouble("Accuracy");
        assertEquals(als, s1.getScore());
        assertEquals(ala, s1.getAcc());
    }

    @Test
    void testSbToJson() {
        JSONArray arr = p.sbToJson(sb1);
        JSONArray multArr = p.sbToJson(sb3);
        assertEquals(arr.length(), 1);
        assertEquals(multArr.length(), 3);
    }

}

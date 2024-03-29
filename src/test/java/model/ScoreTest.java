package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    private Score scoreTest;

    @BeforeEach
    void runBefore(){
        scoreTest = new Score();
        Player.getPlayerInstance("Allan");
    }

    @Test
    void testConstructor(){
        assertEquals(0, scoreTest.getScore());
    }

    @Test
    void testEquals() {
        Score s1 = new Score();
        s1.setAcc(1);
        Score s2 = new Score();
        s2.setAcc(1);
        assertTrue(s1.equals(s2));
        s2.setScore(1000);
        assertFalse(s1.equals(s2));
    }

    @Test
    void testHashCode() {
        Score s1 = new Score();
        Score s2 = new Score();
        s2.setAcc(1);
        assertFalse(s1.hashCode() == s2.hashCode());
    }

    @Test
    void testCalculateScore(){
        scoreTest.calculateScore(1);
        assertEquals(1000, scoreTest.getScore());

        scoreTest.calculateScore(0);
        assertEquals(0, scoreTest.getScore());

        scoreTest.calculateScore(50);
        assertEquals(50 * 1000, scoreTest.getScore());
    }

    @Test
    void testCalculateAccuracy(){
        scoreTest.calculateAccuracy(1,1);
        assertEquals(100, scoreTest.getAcc());

        scoreTest.calculateAccuracy(25,25);
        assertEquals(100, scoreTest.getAcc());

        scoreTest.calculateAccuracy(50,49);
        assertEquals(98, scoreTest.getAcc());

        scoreTest.calculateAccuracy(50,25);
        assertEquals(50, scoreTest.getAcc());
    }

    @Test
    void testSetResults(){
        scoreTest.calculateScore(1);
        scoreTest.calculateAccuracy(1,1);
        scoreTest.setResults(Player.getPlayerInstance(""));

        assertEquals("Score: 1000.0 Accuracy: 100.0%", scoreTest.getResults());
    }

    @Test
    void testSetScore() {
        scoreTest.setScore(100);
        assertEquals(100, scoreTest.getScore());
        scoreTest.setScore(0);
        assertEquals(0, scoreTest.getScore());
        scoreTest.setScore(1);
        assertEquals(1, scoreTest.getScore());
    }

    @Test
    void testSetAcc() {
        scoreTest.setAcc(10);
        assertEquals(10, scoreTest.getAcc());
    }

    @Test
    void testToJson() {
        scoreTest.setScore(100);
        scoreTest.setAcc(100);
        assertTrue(scoreTest.toJson() instanceof JSONObject);
    }

}

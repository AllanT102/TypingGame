package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardTest {

    private Scoreboard sbTest;
    private Score s1;
    private Score s2;
    private Score s3;
    private Score s4;
    private Score s5;

    @BeforeEach
    void runBefore(){
        sbTest = new Scoreboard();
        s1 = new Score();
        s2 = new Score();
        s3 = new Score();
        s4 = new Score();
        s5 = new Score();
        s1.setScore(1000);
        s2.setScore(1500);
        s3.setScore(2000);
        s4.setScore(2000);
        s5.setScore(3000);

    }

    @Test
    void testConstructor(){
        assertTrue(sbTest.getScoreboardAsList().isEmpty());
    }

    @Test
    void testResetScoreboard(){
        sbTest.addScore(s1);
        sbTest.addScore(s2);
        assertEquals(2, sbTest.getLength());
        sbTest.resetScoreboard();
        assertEquals(0, sbTest.getLength());
    }

    @Test
    void testAddScore(){
        sbTest.addScore(s1);
        assertEquals(1, sbTest.getLength());

        sbTest.addScore(s2);
        assertEquals(2, sbTest.getLength());
        assertEquals(s2, sbTest.getScoreboardAsList().get(0));
        assertEquals(s1, sbTest.getScoreboardAsList().get(1));

        assertTrue(sbTest.addScore(s3));
        assertEquals(3, sbTest.getLength());
        assertEquals(s3, sbTest.getScoreboardAsList().get(0));
        assertEquals(s2, sbTest.getScoreboardAsList().get(1));
        assertEquals(s1, sbTest.getScoreboardAsList().get(2));

        sbTest.addScore(s4);
        assertEquals(4, sbTest.getLength());
        assertEquals(s4, sbTest.getScoreboardAsList().get(0));
        assertEquals(s3, sbTest.getScoreboardAsList().get(1));
        assertEquals(s2, sbTest.getScoreboardAsList().get(2));
        assertEquals(s1, sbTest.getScoreboardAsList().get(3));

        sbTest.addScore(s5);
        assertEquals(5, sbTest.getLength());
        assertEquals(s5, sbTest.getScoreboardAsList().get(0));
        assertEquals(s4, sbTest.getScoreboardAsList().get(1));
        assertEquals(s3, sbTest.getScoreboardAsList().get(2));
        assertEquals(s2, sbTest.getScoreboardAsList().get(3));
        assertEquals(s1, sbTest.getScoreboardAsList().get(4));

        assertTrue(sbTest.addScore(s5));
    }

    @Test
    void testConvertScoreboardToString(){
        sbTest.addScore(s1);
        assertEquals("1. 1000.0 Points, 0.0% Accuracy",
                sbTest.convertScoreboardToString(0));
        sbTest.addScore(s2);
        sbTest.addScore(s3);
        assertEquals("2. 1500.0 Points, 0.0% Accuracy", sbTest.convertScoreboardToString(1));
        sbTest.addScore(s1);
        assertEquals("3. 1000.0 Points, 0.0% Accuracy", sbTest.convertScoreboardToString(2));
    }
}

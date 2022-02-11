package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    Score scoreTest;

    @BeforeEach
    void runBefore(){
        scoreTest = new Score();
    }

    @Test
    void testConstructor(){
        assertEquals(0, scoreTest.getScore());
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
        scoreTest.setResults();

        assertEquals("Score: 1000.0 Accuracy: 100.0%", scoreTest.getResults());
    }

    @Test
    void testSetScore() {
        scoreTest.setScore(100);
        assertEquals(100, scoreTest.getScore());
    }

}

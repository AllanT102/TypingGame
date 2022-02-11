package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player pTest;

    @BeforeEach
    void runBefore() {
        pTest = new Player("Allan");
    }

    @Test
    void testConstructor() {
        assertEquals("Allan", pTest.getName());
        assertTrue(pTest.getScoreboard().getScoreboardAsList().isEmpty());
    }
}
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Scoreboard sb;
    private Score s1;
    private Score s2;

    @BeforeEach
    void runBefore() {
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
    void testSetScoreboard() {
        assertFalse(Player.playerExist());
        Player.getPlayerInstance("Allan");
        Player.getPlayerInstance("").setScoreboard(sb);
        assertEquals(sb, Player.getPlayerInstance("").getScoreboard());
    }

    @Test
    void testConstructor() {
        Player.getPlayerInstance("Allan");
        assertEquals("Allan", Player.getPlayerInstance("").getName());
        assertEquals(Player.getPlayerInstance("").getScoreboard().getScoreboardAsList().size(), 2);
    }

}
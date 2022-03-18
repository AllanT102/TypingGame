package ui.panels;

import model.Player;
import ui.gameFunctionality.Login;
import ui.misc.Countdown;

import javax.swing.*;
import java.awt.*;

public class GameMenuPanel extends JPanel {
    private Login login;
    private Player player;
    private Countdown countdown;
    private JButton quitButton;
    private JButton playButton;
    private JButton highscoreButton;
    private JPanel gameScreens;
    private CardLayout menuCL;
    private ScoreboardPanel scoreboardScreen;

    // Constructs the game panel
    // EFFECTS: sets up the game and play screen
    public TypingGamePanel() {
        super();
        this.setLayout(null);

        menuCL = new CardLayout();
        gameScreens = new JPanel();
        scoreboardScreen = new ScoreboardPanel();
        gameScreens.setLayout(menuCL);
        gameScreens.add(scoreboardScreen, "scoreboard");
        add(gameScreens);
    }
}

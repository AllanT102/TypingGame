package ui.panels;

import model.Player;
import ui.gameFunctionality.Login;
import ui.misc.Countdown;

import static java.awt.Color.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingGamePanel extends JPanel implements ActionListener {
    protected int width = 500;
    protected int height = 500;

    private Login login;
    private Player player;
    private Countdown countdown;
    private JButton quitButton;
    private JButton playButton;
    private JButton highscoreButton;

    // Constructs the game panel
    // EFFECTS: sets up the game and play screen
    public TypingGamePanel() {
        super();
        this.setLayout(null);
        this.countdown = new Countdown();
        for (Component c : countdown.getCountdownIcons()) {
            this.add(c);
        }
    }

    // Called when player is instantiated
    // MODIFIES: this
    // EFFECTS: sets up the game and play screen
    public void init() {
        setWelcomeMessage();
        setPlayButton();
        setHighscoreButton();
        setQuitButton();
    }

    // MODIFIES: this
    // EFFECTS: sets setLogin
    public void setLogin(Login login) {
        if (this.login != login) {
            removeLogin();
            this.login = login;
            this.login.setGamePanel(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    public void removeLogin() {
        if (this.login != null) {
            Login oldG = this.login;
            this.login = null;
            oldG.removeGamePanel();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public JButton makeButton(String name, int ycoord) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
        button.setBounds(width / 2 - 180, ycoord, 350, 50);
        button.addActionListener(this);
        button.setBackground(black);
        button.setForeground(white);
        button.setOpaque(true);
        this.add(button);
        return button;
    }

    public void setPlayButton() {
        this.playButton = makeButton("Play", 100);
    }

    public void setHighscoreButton() {
        this.highscoreButton = makeButton("Highscores", 200);
    }

    public void setQuitButton() {
        this.quitButton = makeButton("Quit", 300);
    }

    public void setWelcomeMessage() {
        JLabel welcomeMessage = new JLabel("Welcome, " + this.player.getName() + "!", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 25));
        welcomeMessage.setBounds(0, 10, width, 30);
        welcomeMessage.setForeground(black);
        welcomeMessage.setBackground(lightGray);
        welcomeMessage.setOpaque(true);
        this.add(welcomeMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play")) {
            this.playButton.setVisible(false);
            this.highscoreButton.setVisible(false);
            this.quitButton.setVisible(false);
            countdown.startCountdown();
            countdown.getTimer().start();
        }
    }
}
package ui.panels;

import model.Player;
import ui.misc.Countdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class MenuPanel extends JPanel implements ActionListener {
    protected int width = 500;
    protected int height = 500;

    private Player player;
    private Countdown countdown;
    private JButton quitButton;
    private JButton playButton;
    private JButton highscoreButton;
    private TypingGamePanel typingGamePanel;

    // Constructs the game panel
    // EFFECTS: sets up the game and play screen
    public MenuPanel(TypingGamePanel t) {
        super();
        this.typingGamePanel = t;
        this.setLayout(null);
        this.countdown = new Countdown();
        for (Component c : countdown.getCountdownIcons()) {
            this.add(c);
        }
    }

    // Called when player is instantiated
    // MODIFIES: this
    // EFFECTS: sets up the game and play screen
    public void init(Player player) {
        this.player = player;
        setWelcomeMessage();
        setPlayButton();
        setHighscoreButton();
        setQuitButton();
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
        } else if (action.equals("Highscores")) {
            typingGamePanel.getMenuCL().show(typingGamePanel, "scoreboard");
            typingGamePanel.getSbPanel().createScoreboard();
            System.out.println("=presed");
        }
    }
}

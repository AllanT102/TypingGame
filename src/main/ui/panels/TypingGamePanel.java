package ui.panels;

import model.Player;
import ui.gameFunctionality.Login;
import static java.awt.Color.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TypingGamePanel extends JPanel implements ActionListener {
    protected int width = 500;
    protected int height = 500;
    protected int sec = 3;

    private Login login;
    private Player player;
    private Timer timer;
    private List<JComponent> countdownIcons;

    // Constructs the game panel
    // EFFECTS: sets up the game and play screen
    public TypingGamePanel() {
        super();
        this.setLayout(null);
    }

    // Called when player is instantiated
    // MODIFIES: this
    // EFFECTS: sets up the game and play screen
    public void init() {
        setCountdownIcons();
        setWelcomeMessage();
        setPlayButton();
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

    public void setPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.setActionCommand("play");
        playButton.setBounds(width / 2 - 180, 100, 350, 50);
        playButton.addActionListener(this);
        playButton.setBackground(black);
        playButton.setForeground(white);
        playButton.setOpaque(true);
        this.add(playButton);
    }

    public void setCountdownIcons() {
        countdownIcons = new ArrayList<>();

        JLabel three = new JLabel(new ImageIcon("./images/three.png"));
        JLabel two = new JLabel(new ImageIcon("./images/two.png"));
        JLabel one = new JLabel(new ImageIcon("./images/one.png"));
        three.setBounds(width / 2, height / 2, 100, 100);
        two.setBounds(width / 2, height / 2, 100, 100);
        one.setBounds(width / 2, height / 2, 100, 100);
        three.setVisible(false);
        two.setVisible(false);
        one.setVisible(false);
        this.add(three);
        this.add(two);
        this.add(one);
        countdownIcons.add(one);
        countdownIcons.add(two);
        countdownIcons.add(three);
    }

    public void setWelcomeMessage() {
        JLabel welcomeMessage = new JLabel("Welcome, " + this.player.getName() + "!", SwingConstants.LEFT);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeMessage.setBounds(0,5, width, 30);
        welcomeMessage.setForeground(black);
        welcomeMessage.setBackground(lightGray);
        welcomeMessage.setOpaque(true);
        this.add(welcomeMessage);
    }

    public void startCountdown() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sec == 0) {
                    timer.stop();
                }
                countdownIcons.get(sec - 1).setVisible(true);
                System.out.println(sec);
                sec--;
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play")) {
            startCountdown();
        }
    }
}

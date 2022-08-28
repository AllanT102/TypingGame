package ui.panels;

import model.Player;
import ui.gamefunctionality.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

// represents menu panel
public class MenuPanel extends JPanel implements ActionListener {
    private final int width = 500;
    private final int height = 500;
    private JButton quitButton;
    private JButton playButton;
    private JButton highscoreButton;
    private TypingGamePanel typingGamePanel;

    // Constructs the game panel
    // MODIFIES: t
    // EFFECTS: sets up the game and play screen
    public MenuPanel(TypingGamePanel t) {
        super();
        this.typingGamePanel = t;
        this.setLayout(null);
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
    // EFFECTS: helper method to make a button
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

    // MODIFIES: this
    // EFFECTS: creates play button
    public void setPlayButton() {
        this.playButton = makeButton("Play", 100);
    }

    // MODIFIES: this
    // EFFECTS: creates highscore button
    public void setHighscoreButton() {
        this.highscoreButton = makeButton("Highscores", 200);
    }

    // MODIFIES: this
    // EFFECTS: creates quit button
    public void setQuitButton() {
        this.quitButton = makeButton("Save and Quit", 300);
    }

    // MODIFIES: this
    // EFFECTS: sets a welcome message at top of screen
    public void setWelcomeMessage() {
        JLabel welcomeMessage = new JLabel("Welcome, "
                + Player.getPlayerInstance("").getName() + "!", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeMessage.setBounds(0, 0, width, 40);
        welcomeMessage.setForeground(black);
        welcomeMessage.setBackground(new Color(0, 102, 204));
        welcomeMessage.setOpaque(true);
        this.add(welcomeMessage);
    }

    // MODIFIES: this
    // EFFECTS: processes button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play")) {
            typingGamePanel.setWordPanel();
            typingGamePanel.getWordPanel().init();
        } else if (action.equals("Save and Quit")) {
            int promptResult = JOptionPane.showConfirmDialog(null,
                    "Do you want to save your player data?", "Confirm Close", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ImageIcon("./images/bye.png").getImage()
                            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
            if (promptResult == JOptionPane.YES_OPTION) {
                if (Player.playerExist()) {
                    Login.getInstance().saveData();
                }
                System.exit(0);
            } else if (promptResult == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else if (action.equals("Highscores")) {
            typingGamePanel.getMenuCL().show(typingGamePanel, "scoreboard");
            try {
                typingGamePanel.getSbPanel().updateScoreboard();
            } catch (Exception exception) {
                typingGamePanel.getSbPanel().createScoreboard();
            }
        }
    }
}

package ui.panels;

import model.Player;
import model.Score;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.Color.*;

public class EndGamePanel extends JPanel implements ActionListener {
    private int width = 500;
    private TypingGamePanel typingGamePanel;
    private Player player;
    private Score score;

    // MODIFIES: gamePanel
    // EFFECTS: constructs end game panel that displays after user finishes playing a game
    public EndGamePanel(TypingGamePanel gamePanel, Player player, Score score) {
        super();
        this.typingGamePanel = gamePanel;
        this.player = player;
        this.score = score;
        setLayout(null);
        makeReplayButton();
        makeQuitButton();
        makeGoBackToMenuButton();
        makeScoreLine();
        setVisible(true);
        gamePanel.add(this, "endgame");
        gamePanel.getMenuCL().show(gamePanel, "endgame");
    }

    // EFFECTS: helper method to make a button
    public void makeButton(String name, int ycoord) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
        button.setBounds(width / 2 - 180, ycoord, 350, 50);
        button.addActionListener(this);
        button.setBackground(black);
        button.setForeground(white);
        button.setOpaque(true);
        this.add(button);
    }

    // EFFECTS: makes score line that displays after game
    public void makeScoreLine() {
        JLabel scoreLine = new JLabel(score.getResults(), SwingConstants.CENTER);
        scoreLine.setFont(new Font("Serif", Font.BOLD, 30));
        scoreLine.setBounds(0, 0, width, 40);
        scoreLine.setForeground(black);
        scoreLine.setBackground(new Color(0, 102, 204));
        scoreLine.setOpaque(true);
        this.add(scoreLine);
    }

    // EFFECTS: makes replay button
    public void makeReplayButton() {
        makeButton("Play again", 100);
    }

    // EFFECTS: makes back to menu button
    public void makeGoBackToMenuButton() {
        makeButton("Menu", 200);
    }

    // EFFECTS: makes quit button
    public void makeQuitButton() {
        makeButton("Save and Quit", 300);
    }

    // MODIFIES: this
    // EFFECTS: processes button clicks (play again, menu, save and quit
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play again")) {
            typingGamePanel.setWordPanel();
            typingGamePanel.getWordPanel().init();
        } else if (action.equals("Menu")) {
            typingGamePanel.getMenuCL().show(typingGamePanel, "menu panel");
        } else if (action.equals("Save and Quit")) {
            int promptResult = JOptionPane.showConfirmDialog(null,
                    "Do you want to save your player data?",
                    "Confirm Close", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(new ImageIcon("./images/bye.png").getImage()
                            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
            if (promptResult == JOptionPane.YES_OPTION) {
                typingGamePanel.getLogin().saveData();
                System.exit(0);
            } else if (promptResult == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
    }
}

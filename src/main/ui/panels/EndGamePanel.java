package ui.panels;

import model.Player;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class EndGamePanel extends JPanel implements ActionListener {
    private int width = 500;

    private TypingGamePanel typingGamePanel;
    private Player player;

    public EndGamePanel(TypingGamePanel gamePanel, Player p) {
        super();
        this.typingGamePanel = gamePanel;
        this.player = player;
        setLayout(null);
        makeReplayButton();
        makeQuitButton();
        makeGoBackToMenuButton();
        setVisible(true);
        gamePanel.add(this, "endgame");
        gamePanel.getMenuCL().show(gamePanel, "endgame");
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

    public void makeReplayButton() {
        makeButton("Play again", 100);
    }

    public void makeGoBackToMenuButton() {
        makeButton("Menu", 200);
    }

    public void makeQuitButton() {
        makeButton("Quit", 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Play again")) {
            typingGamePanel.setWordPanel();
            typingGamePanel.getWordPanel().init();
        } else if (action.equals("Menu")) {
            typingGamePanel.getMenuCL().show(typingGamePanel, "menu panel");
        } else if (action.equals("Quit")) {
            int promptResult = JOptionPane.showConfirmDialog(null,
                    "Do you want to save your player data?",
                    "Confirm Close", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (promptResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}

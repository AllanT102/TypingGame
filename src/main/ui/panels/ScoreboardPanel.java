package ui.panels;

import com.sun.beans.editors.IntegerEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.Color.*;

public class ScoreboardPanel extends JPanel implements ActionListener {
    private int width = 500;

    private TypingGamePanel typingGamePanel;

    public ScoreboardPanel(TypingGamePanel t) {
        super();
        this.typingGamePanel = t;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createTitle();
        this.add(Box.createRigidArea(new Dimension(0,20)));

        setVisible(true);
    }

    public void createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("SCOREBOARD", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setForeground(black);
        title.setBackground(lightGray);
        title.setOpaque(true);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setMaximumSize(new Dimension(width, 70));
        titlePanel.add(title);
        this.add(titlePanel);
    }

    // called after highscore button is clicked so that player is already assigned to class
    public void createScoreboard() {
        JPanel scoreboard = new JPanel();
        scoreboard.setLayout(new BoxLayout(scoreboard, BoxLayout.LINE_AXIS));
        for (int i = 0; i < 5; i++) {
            try {
                JLabel scoreline = new JLabel(typingGamePanel.getPlayer().getScoreboard().convertScoreboardToString(i));
                scoreline.setFont(new Font("Serif", Font.BOLD, 25));
                scoreline.setHorizontalTextPosition((int) LEFT_ALIGNMENT);
                scoreline.setAlignmentX(CENTER_ALIGNMENT);
                scoreline.setBackground(lightGray);
                scoreline.setMaximumSize(new Dimension(width, 50));
                this.add(scoreline);
            } catch (IndexOutOfBoundsException e) {
                // do nothing
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

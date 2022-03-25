package ui.panels;

import com.sun.beans.editors.IntegerEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class ScoreboardPanel extends JPanel implements ActionListener {
    private int width = 500;
    private int tileHeight = 300;
    private int rowHeight = tileHeight / 6;
    private Font rowFont = new Font("Serif", Font.PLAIN, 25);

    private TypingGamePanel typingGamePanel;
    private JPanel scoreTile;
    private JPanel accTile;
    private JPanel scoreboard;

    public ScoreboardPanel(TypingGamePanel t) {
        super();
        this.typingGamePanel = t;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createTitle();
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        setVisible(true);
    }

    public void createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("Scoreboard", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(black);
        title.setBackground(new Color(0, 102, 204));
        title.setOpaque(true);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setAlignmentY(50);
        title.setPreferredSize(new Dimension(width, 40));
        title.setMaximumSize(new Dimension(width, 40));
        titlePanel.add(title);
        this.add(titlePanel);
    }

    // called after highscore button is clicked so that player is already assigned to class
    public void createScoreboard() {
        scoreboard = new JPanel();
        scoreboard.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        scoreboard.add(createNumbersTile());
        scoreboard.add(createScoreTile());
        scoreboard.add(createAccTile());
        addToScoreTile();
        addToAccTile();
        this.add(scoreboard);
        addBackButton();
    }

    public void updateScoreboard() {
        this.scoreTile.removeAll();
        this.accTile.removeAll();
        addToScoreTile();
        addToAccTile();
    }

    public JPanel createPanel(int w) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMaximumSize(new Dimension(40, rowHeight));
        panel.setPreferredSize(new Dimension(w, tileHeight));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        return panel;
    }

    public JPanel createScoreTile() {
        JPanel scoreTile = createPanel(width / 3);
        this.scoreTile = scoreTile;
        return scoreTile;
    }

    public JPanel createAccTile() {
        JPanel accTile = createPanel(width / 3);
        this.accTile = accTile;
        return accTile;
    }

    public JPanel createNumbersTile() {
        JPanel numberTile = createPanel(40);
        JLabel blankSpace = new JLabel("");
        blankSpace.setFont(rowFont);
        blankSpace.setPreferredSize(new Dimension(40, rowHeight));
        blankSpace.setMaximumSize(new Dimension(40, rowHeight));
        numberTile.add(blankSpace);

        for (int i = 0; i < 5; i++) {
            JLabel number = new JLabel(Integer.toString(i + 1) + ".");
            highlightTopThree(i, number);
            number.setOpaque(true);
            number.setFont(rowFont);
            number.setPreferredSize(new Dimension(40, rowHeight));
            number.setMaximumSize(new Dimension(40, rowHeight));
            numberTile.add(number);
        }

        return numberTile;
    }

    public void addToScoreTile() {
        addTileTitle("Score", this.scoreTile);
        for (int i = 0; i < 5; i++) {
            try {
                String score = Double.toString(typingGamePanel.getPlayer().getScoreboard().get(i).getScore());
                createSubTile(scoreTile, score, i);
            } catch (IndexOutOfBoundsException e) {
                // do nothing
            }
        }
    }

    public void addToAccTile() {
        addTileTitle("Accuracy", this.accTile);
        for (int i = 0; i < 5; i++) {
            try {
                String acc = Double.toString(typingGamePanel.getPlayer().getScoreboard().get(i).getAcc());
                createSubTile(accTile, acc + "%", i);
            } catch (IndexOutOfBoundsException e) {
                // do nothing
            }
        }
    }

    public void highlightTopThree(int i, JLabel row) {
        switch (i) {
            case 0:
                row.setBackground(new Color(0, 102, 204));
                break;
            case 1:
                row.setBackground(new Color(102, 102, 255));
                break;
            case 2:
                row.setBackground(new Color(178, 102, 255));
                break;
            default:
                row.setBackground(null);
        }
    }


    public void createSubTile(JPanel tile, String info, int i) {
        JLabel sub = new JLabel(info);
        highlightTopThree(i, sub);
        sub.setOpaque(true);
        sub.setFont(rowFont);
        sub.setPreferredSize(new Dimension(width / 3, rowHeight));
        sub.setMaximumSize(new Dimension(width / 3, rowHeight));
        tile.add(sub);
    }

    private void addTileTitle(String name, JPanel tile) {
        JLabel title = new JLabel(name);
        title.setMaximumSize(new Dimension(width, rowHeight));
        title.setFont(rowFont);
        tile.add(title);
    }

    private void addBackButton() {
        JButton button = new JButton("Back to Menu");
        button.addActionListener(this);
        button.setForeground(blue);
        button.setActionCommand("back");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("back")) {
            typingGamePanel.getMenuCL().show(typingGamePanel, "menu panel");
        }
    }
}

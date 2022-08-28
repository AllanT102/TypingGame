package ui.panels;

import model.Player;
import javax.swing.*;
import java.awt.*;

// Represents typing game panel
public class TypingGamePanel extends JPanel {
    private CardLayout menuCL;
    private MenuPanel menuPanel;
    private ScoreboardPanel sbPanel;
    private WordPanel wordPanel;

    // EFFECTS: constructs typing game panel which will hold other panels (menu panel, scoreboard)
    public TypingGamePanel() {
        super();
        menuCL = new CardLayout();
        this.setLayout(menuCL);

        menuPanel = new MenuPanel(this);
        sbPanel = new ScoreboardPanel(this);

        add(menuPanel, "menu panel");
        add(sbPanel, "scoreboard");
        menuCL.show(this, "menu panel");
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a new wordPanel with new paragraph by removing the old wordPanel
    public void setWordPanel() {
        this.wordPanel = null;
        WordPanel newWordPanel = new WordPanel(this, Player.getPlayerInstance(""));
        this.wordPanel = newWordPanel;
        add(newWordPanel, "word panel");
        menuCL.show(this, "word panel");
    }

    public MenuPanel getMenuPanel() {
        return this.menuPanel;
    }

    public ScoreboardPanel getSbPanel() {
        return sbPanel;
    }

    public WordPanel getWordPanel() {
        return wordPanel;
    }

    public CardLayout getMenuCL() {
        return menuCL;
    }
}
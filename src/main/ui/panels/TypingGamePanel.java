package ui.panels;

import model.Player;
import ui.gamefunctionality.Login;
import javax.swing.*;
import java.awt.*;

public class TypingGamePanel extends JPanel {
    private Login login;
    private Player player;
    private CardLayout menuCL;
    private MenuPanel menuPanel;
    private ScoreboardPanel sbPanel;


    // Constructs the game panel
    // EFFECTS: sets up the game and play screen
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

    public MenuPanel getMenuPanel() {
        return this.menuPanel;
    }

    public ScoreboardPanel getSbPanel() {
        return sbPanel;
    }

    public CardLayout getMenuCL() {
        return menuCL;
    }

    public Player getPlayer() {
        return player;
    }
}
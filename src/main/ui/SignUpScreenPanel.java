package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class SignUpScreenPanel extends PreGamePanel {

    // Constructs the sign up page
    // EFFECTS: sets up the sign in panel with options to sign up
    public SignUpScreenPanel() {
        super();

        makeTitle();
        makeUsername();
        makeUsernameTextField();
        makeLoginOptionButtonOne("Already have an account? Login here!", "login");
        makeLoginOptionButtonTwo("Sign up", "sign up");
        makeLoginMessage("");
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: sets TypingGame
    protected void setGame(TypingGame game) {
        if (this.game != game) {
            removeGame();
            this.game = game;
            this.game.setSignUpScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes TypingGame
    protected void removeGame() {
        if (this.game != null) {
            TypingGame oldGame = this.game;
            this.game = null;
            oldGame.removeSignUpScreen();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) this.getComponent(2);
        String action = e.getActionCommand();

        if (action.equals("login")) {
            game.getSignUpScreen().setVisible(false);
            game.getLoadInScreen().setVisible(true);
        } else if (action.equals("sign up")) {
            System.out.println("sign up pressed");
        }
    }
}

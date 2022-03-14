package ui;

import javax.swing.*;
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
        JLabel successMessage = (JLabel) this.getComponent(5);
        String username = textField.getText();
        String action = e.getActionCommand();

        if (action.equals("login")) {
            game.getCl().show(game.getScreens(), "loadInScreen");
        } else if (action.equals("sign up")) {
            if (!login.signUp(username)) {
                successMessage.setText("Player name is taken, please try again.");
            } else {
                successMessage.setText("Successfully created player!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets setLogin
    protected void setLogin(Login login) {
        if (this.login != login) {
            removeLogin();
            this.login = login;
            this.login.setSignUpScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    protected void removeLogin() {
        if (this.login != null) {
            Login oldS = this.login;
            this.login = null;
            oldS.removeSignUpScreen();
        }
    }

}

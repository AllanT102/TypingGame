package ui.panels;

import ui.gamefunctionality.Login;
import ui.frame.TypingGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents sign up screen
public class SignUpScreenPanel extends PreGamePanel {

    // EFFECTS: constructs sign up panel and sets up the sign in panel with options to sign up
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
    public void setGame(TypingGame game) {
        if (this.game != game) {
            removeGame();
            this.game = game;
            this.game.setSignUpScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes TypingGame
    public void removeGame() {
        if (this.game != null) {
            TypingGame oldGame = this.game;
            this.game = null;
            oldGame.removeSignUpScreen();
        }
    }

//    // MODIFIES: this
//    // EFFECTS: sets setLogin
//    public void setLogin(Login login) {
//        if (this.login != login) {
//            removeLogin();
//            this.login = login;
//            this.login.setSignUpScreen(this);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: removes team from this office
//    public void removeLogin() {
//        if (this.login != null) {
//            Login oldS = this.login;
//            this.login = null;
//            oldS.removeSignUpScreen();
//        }
//    }

    // MODIFIES: this
    // EFFECTS: processes button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) this.getComponent(2);
        JLabel successMessage = (JLabel) this.getComponent(5);
        String username = textField.getText();
        String action = e.getActionCommand();

        if (action.equals("login")) {
            game.getCl().show(game.getScreens(), "loadInScreen");
        } else if (action.equals("sign up")) {
            Login login = Login.getInstance();
            if (!login.signUp(username)) {
                successMessage.setText("Player name is taken, please try again.");
            } else {
                game.getCl().show(game.getScreens(), "typingGamePanel");
//                game.getTypingGamePanel().setPlayer(login.getPlayer());
                game.getTypingGamePanel().getMenuPanel().init();
            }
        }
    }
}

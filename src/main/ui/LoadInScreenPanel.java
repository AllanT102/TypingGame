package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;



public class LoadInScreenPanel extends PreGamePanel {

    // Constructs the load in screen
    // EFFECTS: sets up the panel with pregame login options
    public LoadInScreenPanel() {
        super();

        makeTitle();
        makeUsername();
        makeUsernameTextField();
        makeLoginOptionButtonOne("Don't have an account? Sign up!", "sign up");
        makeLoginOptionButtonTwo("Login", "login");
        makeLoginMessage("");
    }

    // MODIFIES: this
    // EFFECTS: sets TypingGame
    protected void setGame(TypingGame game) {
        if (this.game != game) {
            removeGame();
            this.game = game;
            this.game.setLoadInScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes TypingGame
    protected void removeGame() {
        if (this.game != null) {
            TypingGame oldGame = this.game;
            this.game = null;
            oldGame.removeLoadInScreen();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets setLogin
    protected void setLogin(Login login) {
        if (this.login != login) {
            removeLogin();
            this.login = login;
            this.login.setLoadInScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    protected void removeLogin() {
        if (this.login != null) {
            Login oldL = this.login;
            this.login = null;
            oldL.removeLoadInScreen();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes button clicks by loading player information or switching windows to player creation
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) this.getComponent(2);
        JLabel successMessage = (JLabel) this.getComponent(5);
        String username = textField.getText();
        String action = e.getActionCommand();

        if (action.equals("login")) {
            Boolean success = login.signIn(username);
            if (success) {
                game.getCl().show(game.getScreens(), "typingGamePanel");
            } else {
                successMessage.setText("Login failed, try again!");
            }
        } else if (action.equals("sign up")) {
            game.getCl().show(game.getScreens(), "signUpScreen");
        }
    }
}



// HAVE TO ADD WHEN EXIT IS CLICKED, AUTO SAVE FILE!
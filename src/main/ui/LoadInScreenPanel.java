package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;


public class LoadInScreenPanel extends PreGamePanel {
    private Login login;


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
                successMessage.setText("Login successful!");
                game.getContentPane().removeAll();
                gamePanel = new TypingGamePanel();
                game.getContentPane().add(gamePanel);
                game.revalidate();
            } else {
                successMessage.setText("Login failed, try again!");
                System.out.println("bye");
            }
        } else if (action.equals("sign up")) {
            this.game.getLoadInScreen().setVisible(false);
            game.getContentPane().add(game.getSignUpScreen());
        }
    }
}



// HAVE TO ADD WHEN EXIT IS CLICKED, AUTO SAVE FILE!
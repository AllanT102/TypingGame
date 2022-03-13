package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;


public class LoadInScreen extends PreGamePanel {
    private TypingGame game;
    private TypingGamePanel gamePanel;
    private Login login;


    // Constructs the load in screen
    // EFFECTS: sets up the panel with pregame login options
    public LoadInScreen() {
        super();
        setLayout(null);

        makeTitle();
        makeUsername();
        makeUsernameTextField();
        makeLoginButton();
        makeSignUpButton();
        makeLoginMessage("");
    }

    // MODIFIES: this
    // EFFECTS: sets TypingGame
    public void setGame(TypingGame game) {
        if (this.game != game) {
            removeGame();
            this.game = game;
            this.game.setLoadInScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes TypingGame
    public void removeGame() {
        if (this.game != null) {
            TypingGame oldGame = this.game;
            this.game = null;
            oldGame.removeLoadInScreen();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets setLogin
    public void setLogin(Login login) {
        if (this.login != login) {
            removeLogin();
            this.login = login;
            this.login.setLoadInScreen(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team from this office
    public void removeLogin() {
        if (this.login != null) {
            Login oldL = this.login;
            this.login = null;
            oldL.removeLoadInScreen();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates username text
    private void makeUsername() {
        JLabel label = new JLabel("Username", SwingConstants.RIGHT);
        label.setBounds(width / 2 - borderThickness - 100 - userFieldW / 2,
                height / 2 - borderThickness - userFieldH / 2, userFieldW, userFieldH);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setOpaque(true);
        this.add(label);
    }

    // MODIFIES: this
    // EFFECTS: creates username text field
    private void makeUsernameTextField() {
        JTextComponent username = new JTextField();
        username.setName("username");
        username.setBounds(width / 2 - borderThickness - userFieldW / 2,
                height / 2 - borderThickness - textFieldH / 2, textFieldW, textFieldH);
        username.setOpaque(true);
        this.add(username);
    }

    // MODIFIES: this
    // EFFECTS: makes sign up button
    private void makeSignUpButton() {
        JButton button = new JButton("Don't have an account? Sign up!");
        button.setName("sign up");
        button.setActionCommand("sign up");
        button.setForeground(blue);
        button.setBounds(width / 2 - 180, height  * 3 / 4, 350, userFieldH);
        button.addActionListener(this);
        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates login button
    private void makeLoginButton() {
        JButton button = new JButton("Login");
        button.setName("login");
        button.setActionCommand("login");
        button.setForeground(blue);
        button.setBounds(this.getComponent(2).getX() + textFieldW / 2, height / 2
                - borderThickness - textFieldH / 2 + textFieldH + 10, userFieldW, userFieldH);
        button.addActionListener(this);
        this.add(button);
    }

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
            System.out.println("sign up pressed");
        }
    }
}



// HAVE TO ADD WHEN EXIT IS CLICKED, AUTO SAVE FILE!
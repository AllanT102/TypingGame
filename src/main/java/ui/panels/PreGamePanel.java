package ui.panels;

import ui.frame.TypingGame;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

// abstract class for a pregame panel
public abstract class PreGamePanel extends JPanel implements ActionListener {
    protected final int width = 500;
    protected final int height = 500;
    protected final int userFieldW = 90;
    protected final int userFieldH = 25;
    protected final int textFieldW = 165;
    protected final int textFieldH = 25;
    protected final int borderThickness = 10;

    protected TypingGame game;

    // EFFECTS: constructs pre-game panel
    public PreGamePanel() {
        super();
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: creates title
    protected void makeTitle() {
        JLabel title = new JLabel("TYPING GAME   ", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setBounds(0, height / 5, width, 100);
        title.setOpaque(true);
        title.setForeground(black);
        title.setBackground(new Color(0, 102, 204));
        this.add(title);
    }

    // MODIFIES: this
    // EFFECTS: creates username text
    protected void makeUsername() {
        JLabel label = new JLabel("Username", SwingConstants.RIGHT);
        label.setBounds(width / 2 - borderThickness - 100 - userFieldW / 2,
                height / 2 - borderThickness - userFieldH / 2, userFieldW, userFieldH);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setOpaque(true);
        this.add(label);
    }

    // MODIFIES: this
    // EFFECTS: creates username text field
    protected void makeUsernameTextField() {
        JTextComponent username = new JTextField();
        username.setName("username");
        username.setBounds(width / 2 - borderThickness - userFieldW / 2,
                height / 2 - borderThickness - textFieldH / 2, textFieldW, textFieldH);
        username.setOpaque(true);
        this.add(username);
    }

    // MODIFIES: this
    // EFFECTS: makes login message
    public void makeLoginMessage(String loginMessage) {
        JLabel message = new JLabel(loginMessage, SwingConstants.LEFT);
        message.setName("message");
        message.setBounds(this.getComponent(2).getX(), height - 200, textFieldW + 100, textFieldH);
        this.add(message);
    }

    // MODIFIES: this
    // EFFECTS: makes sign up or sign in button according to which panel (bottom button)
    protected void makeLoginOptionButtonOne(String s, String command) {
        JButton button = new JButton(s);
        button.setActionCommand(command);
        button.setForeground(blue);
        button.setBounds(width / 2 - 180, height  * 3 / 4, 350, userFieldH);
        button.addActionListener(this);
        this.add(button);
    }

    // MODIFIES: this
    // EFFECTS: makes sign up or sign in button according to which panel (right underneath user input)
    protected void makeLoginOptionButtonTwo(String s, String command) {
        JButton button = new JButton(s);
        button.setActionCommand(command);
        button.setForeground(blue);
        button.setBounds(this.getComponent(2).getX() + textFieldW / 2, height / 2
                - borderThickness - textFieldH / 2 + textFieldH + 10, userFieldW, userFieldH);
        button.addActionListener(this);
        this.add(button);
    }

}

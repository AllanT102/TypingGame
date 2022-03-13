package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;


public class LoadInScreen extends JPanel implements ActionListener {
    private int width = 500;
    private int height = 500;
    private int userFieldW = 90;
    private int userFieldH = 25;
    private int textFieldW = 165;
    private int textFieldH = 25;
    private int borderThickness = 10;

    private JPanel panel;
    private JFrame frame;
    private Login login;
    private TypingGamePanel gamePanel;

    public LoadInScreen() {
        panel = new JPanel();
        frame = new JFrame();
        gamePanel = new TypingGamePanel();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);
        Border border = new TitledBorder(new LineBorder(Color.black, borderThickness));
        frame.getRootPane().setBorder(border);

        makeTitle();
        makeUsername();
        makeUsernameTextField();
        makeLoginButton();
        makeSignUpButton();
        makeLoginMessage("");


        frame.setVisible(true);
    }

    // MODIFIES: this, team
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
        panel.add(label);
    }

    // MODIFIES: this
    // EFFECTS: creates title
    private void makeTitle() {
        JLabel title = new JLabel("TYPING GAME", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setBounds(0, height / 5, width, 100);
        title.setOpaque(true);
        title.setForeground(black);
        title.setBackground(lightGray);
        panel.add(title);
    }

    // MODIFIES: this
    // EFFECTS: makes
    public void makeLoginMessage(String loginMessage) {
        JLabel message = new JLabel(loginMessage, SwingConstants.LEFT);
        message.setName("message");
        message.setBounds(panel.getComponent(2).getX(), height - 200, textFieldW, textFieldH);
        panel.add(message);
    }

    // MODIFIES: this
    // EFFECTS: creates username text field
    private void makeUsernameTextField() {
        JTextComponent username = new JTextField();
        username.setName("username");
        username.setBounds(width / 2 - borderThickness - userFieldW / 2,
                height / 2 - borderThickness - textFieldH / 2, textFieldW, textFieldH);
        username.setOpaque(true);
        panel.add(username);
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
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates login button
    private void makeLoginButton() {
        JButton button = new JButton("Login");
        button.setName("login");
        button.setActionCommand("login");
        button.setForeground(blue);
        button.setBounds(panel.getComponent(2).getX() + textFieldW / 2, height / 2
                - borderThickness - textFieldH / 2 + textFieldH + 10, userFieldW, userFieldH);
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) panel.getComponent(2);
        JLabel successMessage = (JLabel) panel.getComponent(5);
        String username = textField.getText();
        String action = e.getActionCommand();

        if (action.equals("login")) {
            Boolean success = login.signIn(username);
            if (success) {
                successMessage.setText("Login successful!");
                frame.getContentPane().removeAll();
                frame.getContentPane().add(gamePanel);
                frame.revalidate();
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
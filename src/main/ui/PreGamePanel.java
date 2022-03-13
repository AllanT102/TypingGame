package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.black;
import static java.awt.Color.lightGray;

public abstract class PreGamePanel extends JPanel implements ActionListener {
    protected int width = 500;
    protected int height = 500;
    protected int userFieldW = 90;
    protected int userFieldH = 25;
    protected int textFieldW = 165;
    protected int textFieldH = 25;
    protected int borderThickness = 10;

    public PreGamePanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: creates title
    protected void makeTitle() {
        JLabel title = new JLabel("TYPING GAME", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setBounds(0, height / 5, width, 100);
        title.setOpaque(true);
        title.setForeground(black);
        title.setBackground(lightGray);
        this.add(title);
    }

    // MODIFIES: this
    // EFFECTS: makes login message
    protected void makeLoginMessage(String loginMessage) {
        JLabel message = new JLabel(loginMessage, SwingConstants.LEFT);
        message.setName("message");
        message.setBounds(this.getComponent(2).getX(), height - 200, textFieldW, textFieldH);
        this.add(message);
    }


}

package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import java.awt.*;

import static java.awt.Color.*;


public class LoadInScreen {
    private int width = 500;
    private int height = 500;
    private int userFieldW = 90;
    private int userFieldH = 25;
    private int textFieldW = 165;
    private int textFieldH = 25;
    private int borderThickness = 10;

    private JPanel panel;
    private JFrame frame;

    public LoadInScreen() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        Border border = new TitledBorder(new LineBorder(Color.black, borderThickness));
        frame.getRootPane().setBorder(border);

        JLabel title = new JLabel("TYPING GAME", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD,40));
        title.setBounds(0, height / 5, 500, 100);
        title.setOpaque(true);
        title.setForeground(black);
        title.setBackground(lightGray);
        panel.add(title);

        JLabel label = new JLabel("Username", SwingConstants.RIGHT);
        label.setBounds(width / 2 - borderThickness - 100 - userFieldW / 2,
                height / 2 - borderThickness - userFieldH / 2, userFieldW, userFieldH);
        label.setFont(new Font("Serif", Font.BOLD,20));
        label.setOpaque(true);
        panel.add(label);

        JTextField username = new JTextField();
        username.setBounds(width / 2 - borderThickness - userFieldW / 2,
                height / 2 - borderThickness - textFieldH / 2, textFieldW, textFieldH);
        username.setOpaque(true);
        panel.add(username);

        JButton button = new JButton("Login");
        button.setForeground(blue);
        button.setOpaque(true);
        System.out.println(username.getX());
        button.setBounds(username.getX() + textFieldW / 2, height / 2 - borderThickness - textFieldH / 2 + textFieldH + 10, userFieldW, userFieldH);
        panel.add(button);

    }
}

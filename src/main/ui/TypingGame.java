package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

// A typing game class where users can create a player, practice their typing skills, and view scores
public class TypingGame extends JFrame {
    private int width = 500;
    private int height = 500;

    private int borderThickness = 10;

    // constructs main window
    // EFFECTS: sets up window in which Typing Game will be played and sign up/ login page will be
    public TypingGame() {
        super("Typing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        Border border = new TitledBorder(new LineBorder(Color.black, borderThickness));
        this.getRootPane().setBorder(border);
    }

}

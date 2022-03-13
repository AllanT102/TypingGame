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

    private LoadInScreen loadInScreen;

    // constructs main window
    // EFFECTS: sets up window in which Typing Game will be played and sign up/ login page will be
    public TypingGame(LoadInScreen loadInScreen) {
        super("Typing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        Border border = new TitledBorder(new LineBorder(Color.black, borderThickness));
        this.getRootPane().setBorder(border);
        this.add(loadInScreen);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets loadInScreen
    public void setLoadInScreen(LoadInScreen l) {
        if (this.loadInScreen != l) {
            removeLoadInScreen();
            this.loadInScreen = l;
            this.loadInScreen.setGame(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes loadInScreen
    public void removeLoadInScreen() {
        if (this.loadInScreen != null) {
            LoadInScreen oldL = this.loadInScreen;
            this.loadInScreen = null;
            oldL.removeGame();
        }
    }

}

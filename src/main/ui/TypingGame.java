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

    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;

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



    // MODIFIES: this
    // EFFECTS: sets loadInScreen and adds to frame
    protected void setLoadInScreen(LoadInScreenPanel l) {
        if (this.loadInScreen != l) {
            removeLoadInScreen();
            this.loadInScreen = l;
            this.loadInScreen.setGame(this);
            add(l);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes loadInScreen
    protected void removeLoadInScreen() {
        if (this.loadInScreen != null) {
            LoadInScreenPanel oldL = this.loadInScreen;
            this.loadInScreen = null;
            oldL.removeGame();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets signUpScreen but doesn't add to frame yet
    protected void setSignUpScreen(SignUpScreenPanel l) {
        if (this.signUpScreen != l) {
            removeSignUpScreen();
            this.signUpScreen = l;
            this.signUpScreen.setGame(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes signUpScreen
    protected void removeSignUpScreen() {
        if (this.signUpScreen != null) {
            SignUpScreenPanel oldS = this.signUpScreen;
            this.signUpScreen = null;
            oldS.removeGame();
        }
    }

    public LoadInScreenPanel getLoadInScreen() {
        return loadInScreen;
    }

    public SignUpScreenPanel getSignUpScreen() {
        return signUpScreen;
    }
}

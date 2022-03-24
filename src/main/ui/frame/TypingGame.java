package ui.frame;

import ui.panels.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

// A typing game class where users can create a player, practice their typing skills, and view scores
public class TypingGame extends JFrame {
    private int width = 500;
    private int height = 500;
    private int borderThickness = 10;

    private JPanel screens;
    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;
    private TypingGamePanel typingGamePanel;
    private CardLayout cl;

    // constructs main window
    // EFFECTS: sets up window in which Typing Game will be played and sign up/ login page will be
    public TypingGame() {
        super("Typing Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        centreWindow(this);
        Border border = new TitledBorder(new LineBorder(Color.black, borderThickness));
        this.getRootPane().setBorder(border);

        cl = new CardLayout();
        screens = new JPanel();
        loadInScreen = new LoadInScreenPanel();
        signUpScreen = new SignUpScreenPanel();
        typingGamePanel = new TypingGamePanel();


        screens.setLayout(cl);
        screens.add(loadInScreen, "loadInScreen");
        screens.add(signUpScreen, "signUpScreen");
        screens.add(typingGamePanel, "typingGamePanel");


        cl.show(screens, "loadInScreen");
        add(screens);
        promptUserClose();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets loadInScreen and adds to frame
    public void setLoadInScreen(LoadInScreenPanel l) {
        if (this.loadInScreen != l) {
            removeLoadInScreen();
            this.loadInScreen = l;
            this.loadInScreen.setGame(this);
            this.loadInScreen.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes loadInScreen
    public void removeLoadInScreen() {
        if (this.loadInScreen != null) {
            LoadInScreenPanel oldL = this.loadInScreen;
            this.loadInScreen = null;
            oldL.removeGame();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets signUpScreen and adds to frame, but visibility is false
    public void setSignUpScreen(SignUpScreenPanel l) {
        if (this.signUpScreen != l) {
            removeSignUpScreen();
            this.signUpScreen = l;
            this.signUpScreen.setGame(this);
            this.signUpScreen.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes signUpScreen
    public void removeSignUpScreen() {
        if (this.signUpScreen != null) {
            SignUpScreenPanel oldS = this.signUpScreen;
            this.signUpScreen = null;
            oldS.removeGame();
        }
    }

    public void promptUserClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int promptResult = JOptionPane.showConfirmDialog(null,
                        "Do you want to save your player data?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (promptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public LoadInScreenPanel getLoadInScreen() {
        return loadInScreen;
    }

    public SignUpScreenPanel getSignUpScreen() {
        return signUpScreen;
    }

    public TypingGamePanel getTypingGamePanel() {
        return typingGamePanel;
    }

    public CardLayout getCl() {
        return cl;
    }

    public JPanel getScreens() {
        return screens;
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }


}

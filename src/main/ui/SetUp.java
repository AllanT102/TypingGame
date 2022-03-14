package ui;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.black;

public class SetUp {
    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;
    private Login login;
    private TypingGame game;


    // EFFECTS: class to handle setup of loading screen
    public SetUp() {
        game = new TypingGame();
        login = new Login();

        loadInScreen = game.getLoadInScreen();
        signUpScreen = game.getSignUpScreen();

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(this.loadInScreen);

        game.setSignUpScreen(this.signUpScreen);
        signUpScreen.setGame(this.game);

        game.setLoadInScreen(this.loadInScreen);
        loadInScreen.setGame(this.game);
    }
}

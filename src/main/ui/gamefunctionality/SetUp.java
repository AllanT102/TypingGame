package ui.gamefunctionality;

import ui.frame.TypingGame;
import ui.panels.LoadInScreenPanel;
import ui.panels.SignUpScreenPanel;
import ui.panels.TypingGamePanel;

// Class that sets up entire game
public class SetUp {
    private Login login;
    private TypingGame game;


    // EFFECTS: constructs class to handle setup of loading screen and bidirectional relationships
    public SetUp() {
        game = new TypingGame();
        login = new Login();

        LoadInScreenPanel loadInScreen = game.getLoadInScreen();
        SignUpScreenPanel signUpScreen = game.getSignUpScreen();
        TypingGamePanel gamePanel = game.getTypingGamePanel();

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(loadInScreen);

        signUpScreen.setLogin(this.login);
        login.setSignUpScreen(signUpScreen);

        game.setSignUpScreen(signUpScreen);
        signUpScreen.setGame(this.game);

        game.setLoadInScreen(loadInScreen);
        loadInScreen.setGame(this.game);

        login.setGamePanel(gamePanel);
        gamePanel.setLogin(this.login);
    }
}

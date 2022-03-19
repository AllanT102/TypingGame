package ui.gamefunctionality;

import ui.frame.TypingGame;
import ui.panels.LoadInScreenPanel;
import ui.panels.SignUpScreenPanel;
import ui.panels.TypingGamePanel;

public class SetUp {
    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;
    private TypingGamePanel gamePanel;
    private Login login;
    private TypingGame game;


    // EFFECTS: class to handle setup of loading screen
    public SetUp() {
        game = new TypingGame();
        login = new Login();

        loadInScreen = game.getLoadInScreen();
        signUpScreen = game.getSignUpScreen();
        gamePanel = game.getTypingGamePanel();

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(this.loadInScreen);

        signUpScreen.setLogin(this.login);
        login.setSignUpScreen(this.signUpScreen);

        game.setSignUpScreen(this.signUpScreen);
        signUpScreen.setGame(this.game);

        game.setLoadInScreen(this.loadInScreen);
        loadInScreen.setGame(this.game);

        login.setGamePanel(this.gamePanel);
        gamePanel.setLogin(this.login);
    }
}

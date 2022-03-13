package ui;

public class SetUp {
    private LoadInScreenPanel loadInScreen;
    private SignUpScreenPanel signUpScreen;
    private Login login;
    private TypingGame game;


    // EFFECTS: class to handle setup of loading screen
    public SetUp() {
        loadInScreen = new LoadInScreenPanel();
        signUpScreen = new SignUpScreenPanel();
        login = new Login();
        game = new TypingGame();

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(this.loadInScreen);

        game.setSignUpScreen(this.signUpScreen);
        signUpScreen.setGame(this.game);

        game.setLoadInScreen(this.loadInScreen);
        loadInScreen.setGame(this.game);

        game.setVisible(true);
    }
}

package ui;

public class SetUp {
    private LoadInScreen loadInScreen;
    private Login login;
    private TypingGame game;

    // EFFECTS: class to handle setup of loading screen
    public SetUp() {
        loadInScreen = new LoadInScreen();
        login = new Login();
        game = new TypingGame(loadInScreen);

        game.setLoadInScreen(this.loadInScreen);
        loadInScreen.setGame(this.game);

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(this.loadInScreen);
    }
}

package ui;

public class SetUp {
    private LoadInScreen loadInScreen;
    private Login login;

    // EFFECTS: class to handle setup of loading screen
    public SetUp() {
        loadInScreen = new LoadInScreen();
        login = new Login();

        loadInScreen.setLogin(this.login);
        login.setLoadInScreen(this.loadInScreen);
    }
}

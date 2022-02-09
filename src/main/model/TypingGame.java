package model;

public class TypingGame {

    Player player;
    Score score;
    Paragraph paragraph;

    // Constructs a Typing Game
    // EFFECTS: creates a paragraph words on screen, constructs a score, and player
    public TypingGame(String name) {
        player = new Player(name);
        score = new Score();
        paragraph = new Paragraph();
    }

}

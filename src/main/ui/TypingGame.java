package ui;

import model.Paragraph;
import model.Player;
import model.Score;
import model.Scoreboard;
import java.util.Scanner;

public class TypingGame {

    private Player player;
    private Score score;
    private Paragraph paragraph;
    private Scanner input;

    // Constructs a Typing Game
    // EFFECTS: creates a paragraph words on screen, constructs a score, and player
    public TypingGame() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            mainMenu();
            command = input.next();

            if (command.equals("Q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void processCommand(String command) {
        if (command.equals("P")) {
            startGame();
        } else if (command.equals("S")) {
            displayHighscores();
        } else {
            System.out.println("Selection is not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner system, and sets delimiter
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        playerCreationMenu();
    }

    private void startGame() {
        paragraph = new Paragraph();
        score = new Score();

        System.out.println("\nType the following words as best and fast as you can! Press Enter when you are done!\n");
        System.out.println(paragraph.getParagraphAsString());
        String typedParagraph = input.next();
        paragraph.setInputPara(typedParagraph);

        String endGame = input.nextLine();
        paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), typedParagraph);

        displayScore();

    }

    private void displayScore() {
        score.calculateAccuracy(paragraph.getTotalChar(),
                paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), paragraph.getInputPara()));
        score.calculateScore(score.getAcc());
        score.setResults();
        player.getScoreboard().addScore(score);

        System.out.println("Congratulations! These are your results. \n" + score.getResults());
    }

    private void displayHighscores() {
        System.out.println("Here are your top 5 scores: ");
        System.out.println(player.getScoreboard().convertScoreboardToString());
    }

    // EFFECTS: displays player creation menu for user
    private void playerCreationMenu() {
        System.out.println("Typing Game\n");
        System.out.println("Let's create your account first! Enter your username below!");
        createPlayer();
    }

    // REQUIRES: input name must be at least 3 letters long
    // EFFECTS: creates player account
    private void createPlayer() {
        String name = input.next();
        player = new Player(name);
        System.out.println("You have chosen the name: \n" + player.getName());
    }

    // EFFECTS: displays player creation menu for user
    private void mainMenu() {
        System.out.println("\nWelcome " + player.getName() + "!");
        System.out.println("\nPress P to play\n");
        System.out.println("Press S to find your top 5 scores\n");
        System.out.printf("Press Q if you are done playing\n");
    }


}

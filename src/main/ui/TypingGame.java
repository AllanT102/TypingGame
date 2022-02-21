package ui;

import model.Paragraph;
import model.Player;
import model.Score;
import model.Scoreboard;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

// A typing game class where users can create a player, practice their typing skills, and view scores
public class TypingGame {
    private static final String JSON_DATA = "./data/player.json";
    private Player player;
    private Score score;
    private Paragraph paragraph;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs a Typing Game
    // EFFECTS: creates a paragraph words on screen, constructs a score, and player
    public TypingGame() throws FileNotFoundException {
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
                try {
                    jsonWriter.open();
                    jsonWriter.write(this.player);
                    jsonWriter.close();
                    System.out.println("Player data has been saved! \n");
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file: " + JSON_DATA);
                } catch (IOException e) {
                    System.out.println("Unable to read file!");
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
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
        jsonReader = new JsonReader(JSON_DATA);
        jsonWriter = new JsonWriter(JSON_DATA);
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        playerCreationMenu();
    }

    // MODIFIES: this
    //EFFECTS: starts the typing game
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

    // MODIFIES: this
    // EFFECTS: displays score and adds score to player history
    private void displayScore() {
        score.calculateAccuracy(paragraph.getTotalChar(),
                paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), paragraph.getInputPara()));
        score.calculateScore(score.getAcc());
        score.setResults();
        player.getScoreboard().addScore(score);

        System.out.println("Congratulations! These are your results. \n" + score.getResults());
    }

    // EFFECTS: displays top 5 scores of player
    private void displayHighscores() {
        System.out.println("Here are your top 5 scores: ");
        System.out.println(player.getScoreboard().convertScoreboardToString());
    }

    // MODIFIES: this
    // EFFECTS: displays player creation menu for user
    private void playerCreationMenu() {
        System.out.println("Typing Game\n");
        System.out.println("Do you already have an account? Press Y if yes, N if no.");
        String yn = input.next();

        if (yn.equals("Y")) {
            System.out.println("Type in your username!");
            String existingName = input.next();
            try {
                loadPlayerData(existingName);
                System.out.println("Welcome back, " + existingName);
            } catch (IOException e) {
                System.out.println("Player name is not found\n");
                System.out.println("If you would like to try again, press T.\n"
                        + "If you would like to create a new player, press N.");
                String command = input.next();
                processCreationCommand(command);
            }
        } else if (yn.equals("N")) {
            System.out.println("No problem, let's create a new account for you. Enter below a username!");
            createPlayer();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in player creation menu
    private void processCreationCommand(String command) {
        if (command.equals("T")) {
            playerCreationMenu();
        } else if (command.equals("N")) {
            System.out.println("Let's create your account first! Enter your username below!");
            createPlayer();
        }
    }



    // REQUIRES: input name must be at least 3 letters long
    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: loads player data stored in JSON_DATA
    private void loadPlayerData(String name) throws IOException {
        jsonReader.read();
    }


}

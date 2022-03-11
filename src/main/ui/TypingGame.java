package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// A typing game class where users can create a player, practice their typing skills, and view scores
public class TypingGame {
    private static final String JSON_DATA = "./data/player.json";
    private Players allPlayers;
    private Player player;
    private Score score;
    private Paragraph paragraph;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private LoadInScreen loadInScreen;

    // Constructs a Typing Game
    // EFFECTS: creates a paragraph words on screen, constructs a score, and player
    public TypingGame() throws FileNotFoundException {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.allPlayers);
            jsonWriter.close();
            new LoadInScreen();
        } catch (IOException e) {
            System.out.println("Unable to read file!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner system, and sets delimiter
    private void init() {
        jsonReader = new JsonReader(JSON_DATA);
        jsonWriter = new JsonWriter(JSON_DATA);
        allPlayers = new Players();
        try {
            loadAllPlayers();
        } catch (IOException e) {
            System.out.println("Error when loading players.");
        } catch (JSONException e) {
            // do nothing
        }

        input = new Scanner(System.in);
        input.useDelimiter("\n");

//        playerCreationMenu();
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
    private void signIn(String existingName) {
        if (loadPlayerData(existingName)) {
            loadPlayerData(existingName);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates player account, checks if player name is already taken
    private void signUp(String newName) {

        while (!nameIsValid(newName)) {
            // DONT KNow whAT to do here yet
        }
        player = new Player(newName);
        allPlayers.addPlayer(player);
    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command in player creation menu
//    private void processCreationCommand(String command) {
//        if (command.equals("T")) {
//            playerCreationMenu();
//        } else if (command.equals("Y")) {
//            System.out.println("Let's create your account first! Enter your username below!");
//            createPlayer();
//        } else {
//            System.out.println("Invalid key, please try again\n");
//
//            playerCreationMenu();
//        }
//    }

    // EFFECTS: checks if name is already in allPlayers
    public Boolean nameIsValid(String name) {
        for (int i = 0; i < allPlayers.length(); i++) {
            if (name.equals(allPlayers.getPlayer(i).getName())) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: displays player creation menu for user
    private void mainMenu() {
        System.out.println("\nWelcome " + player.getName() + "!");
        System.out.println("\nPress P to play\n");
        System.out.println("Press S to find your top 5 scores\n");
        System.out.printf("Press Q if you are done playing\n");
    }

    // MODIFIES: this
    // EFFECTS: tries to find matching player name in allPlayers
    private Boolean loadPlayerData(String name) {
        for (int i = 0; i < allPlayers.length(); i++) {
            String plName = allPlayers.getPlayer(i).getName();
            if (name.equals(plName)) {
                this.player = allPlayers.getPlayer(i);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: loads all players stored in JSON_DATA, and adds each individual player to allPlayers
    private void loadAllPlayers() throws IOException, JSONException {
        jsonReader.read();
        this.allPlayers = jsonReader.getAllPlayers();
    }


}

package ui;

import model.Paragraph;
import model.Player;
import model.Score;
import model.Scoreboard;

import javax.swing.*;
import java.awt.event.*;

import java.security.Key;
import java.util.Scanner;

public class TypingGame {

    private Player player;
    private Score score;
    private Paragraph paragraph;
    private Scanner input;

    private KeyListener kl;
    private KeyEvent ke;

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
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner system, and sets delimiter
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        paragraph = new Paragraph();
        score = new Score();
        playerCreationMenu();
    }

    private void startGame() {
        System.out.println("\nType the following words as best and fast as you can! Press Enter when you are done!\n");
        System.out.println(paragraph.getParagraphAsString());
        String typedParagraph = input.next();
        paragraph.setInputPara(typedParagraph);

        String endGame = input.nextLine();
        paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), typedParagraph);

        displayScore();

    }

    private void displayScore() {
        score.calculateAccuracy(paragraph.getTotalChar(), paragraph.getTypedCorrect());
        score.calculateScore(score.getAcc());
        player.getScoreboard().addScore(score);

        System.out.println("Congratulations! These are your results. \n" + score.printResults());
    }

    private void displayHighscores() {
        System.out.println("Here are your top 5 scores: ");
        player.getScoreboard().getScoreboardAsString();
    }

    // EFFECTS: displays player creation menu for user
    private void playerCreationMenu() {
        System.out.println("\n Typing Game");
        System.out.println("\n Let's create your account first! Enter your username below!");
        createPlayer();
    }

    // REQUIRES: input name must be at least 3 letters long
    // EFFECTS: creates player account
    private void createPlayer() {
        String name = input.next();
        player = new Player(name);
        System.out.println("You have chosen the name " + player.getName());
    }

    // EFFECTS: displays player creation menu for user
    private void mainMenu() {
        System.out.println("\n Welcome " + player.getName());
        System.out.println("\n Press P to play");
        System.out.println("\n Press S to find your top 5 scores");
        System.out.printf("\n Press Q if you are done playing\n");
    }


}

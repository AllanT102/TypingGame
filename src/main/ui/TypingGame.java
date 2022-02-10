package ui;

import model.Paragraph;
import model.Player;
import model.Score;

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
    private TypingGame() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            playerCreationMenu();
            mainMenu();
            command = input.next();

            if (command.equals("P")) {
                startGame();
            } else if (command.equals("S")) {
                displayHighscores();
            } else if (command.equals("Q")) {
                keepGoing = false;
            }
        }
        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner system, and sets delimiter
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void startGame() {
        paragraph = new Paragraph();
        score = new Score();

        System.out.println("\nType the following words as best and fast as you can! Press Enter when you are done!");
        System.out.println(paragraph.getParagraphAsString());
        String typedParagraph = input.next();
        paragraph.setInputPara(typedParagraph);

        // add user input "enter" so that program knows game is done



        paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), typedParagraph);
    }

    private class EnterHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }

        // EFFECTS:
    }


    private void endGame(KeyEvent ke){
        if (ke.getKeyCode() == KeyEvent.VK_ENTER){
            score.calculateAccuracy(paragraph.getTotalChar(), paragraph.getTypedCorrect());
            score.calculateScore(score.getAcc());
            
            System.out.println("Congratulations! These are your results. \n" + score.printResults());
        }
    }

    private void displayScore(){
        }

    private void displayHighscores() {
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
    }


}

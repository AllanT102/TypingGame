package ui.panels;

import model.Paragraph;
import model.Player;
import model.Score;
import ui.gamefunctionality.Countdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Color.*;

// represents panel that will display typing words
public class WordPanel extends JPanel {
    private final int width = 500;
    private final int height = 500;
    private final int charPerLine = 44;

    private Countdown countdown;
    private Paragraph paragraph;
    private Score score;
    private JTextField textField;
    private TypingGamePanel gamePanel;
    private JLabel wordsToType;
    private EndGamePanel endGamePanel;

    // MODIFIES: gamePanel, player
    // EFFECTS: constructs a wordPanel object with paragraph that player will type and a countdown that will display
    //          before game starts
    public WordPanel(TypingGamePanel gamePanel, Player player) {
        super();
        setLayout(null);
        paragraph = new Paragraph();
        this.gamePanel = gamePanel;
        this.countdown = new Countdown();
        for (Component c : countdown.getCountdownIcons()) {
            this.add(c);
        }
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initial startup of game including countdown, starting game,
    //          and creating a text-field for user to type in
    public void init() {
        countdown.startCountdown();
        countdown.getTimer().start();
        startGame();
        createTextField();
        setTimeout(() -> textField.setEditable(true), 3900);
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    // MODIFIES: this
    // EFFECTS: starts game by displaying words on screen
    public void startGame() {
        wordsToType = new JLabel("");
        wordsToType.setText("<html><p style=\"width:300px;text-align:center;\">"
                + paragraph.getParagraphAsString() + "</p></html>");
        wordsToType.setFont(new Font("Serif", Font.PLAIN, 20));
        wordsToType.setBounds(35, 50, 400, 150);
        this.add(wordsToType);
    }

    // MODIFIES: this
    // EFFECTS: creates text field for user to type in and processes user input
    public void createTextField() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Serif", Font.PLAIN, 20));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    evt.consume();
                } else if (c == KeyEvent.VK_ENTER) {
                    calculateScore();
                    stopGame();
                }
            }
        });
        textField.setBounds(35, height / 2, 400, 50);
        textField.setBackground(lightGray);
        textField.setOpaque(true);
        EventQueue.invokeLater(() -> textField.requestFocusInWindow());
        this.add(textField);
    }

    // MODIFIES: this
    // EFFECTS: calculates score that the user got
    public Score calculateScore() {
        this.score = new Score();
        String userInputText = textField.getText();
        score.calculateAccuracy(paragraph.getTotalChar(),
                paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), userInputText));
        score.calculateScore(score.getAcc());
        score.setResults(Player.getPlayerInstance(""));
        Player.getPlayerInstance("").getScoreboard().addScore(score);
        return this.score;
    }

    // EFFECTS: stops game by creating end game panel
    public void stopGame() {
        endGamePanel = null;
        endGamePanel = new EndGamePanel(this.gamePanel, this.score);
    }

    public Countdown getCountdown() {
        return countdown;
    }
}

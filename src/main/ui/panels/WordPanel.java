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

public class WordPanel extends JPanel {
    private int width = 500;
    private int height = 500;
    private int charPerLine = 44;

    private Countdown countdown;
    private Paragraph paragraph;
    private Player player;
    private Score score;
    private JTextField textField;
    private TypingGamePanel gamePanel;
    private JLabel wordsToType;

    public WordPanel(TypingGamePanel gamePanel, Player player) {
        super();
        setLayout(null);
        paragraph = new Paragraph();
        this.player = player;
        this.gamePanel = gamePanel;
        this.countdown = new Countdown();
        for (Component c : countdown.getCountdownIcons()) {
            this.add(c);
        }
        setVisible(true);
    }

    public void init() {
        countdown.startCountdown();
        countdown.getTimer().start();
//        // pause main thread until timer is notified
//        synchronized (this) {
//            this.wait();
//            System.out.println("waiting");
//        }
        startGame();

//        try {
        createTextField();
//        } catch (InterruptedException exception) {
//            // do nthing
//        }
    }

    public void startGame() {
        wordsToType = new JLabel("");
        wordsToType.setText("<html><p style=\"width:300px;text-align:center;\">"
                + paragraph.getParagraphAsString() + "</p></html>");
        wordsToType.setFont(new Font("Serif", Font.PLAIN, 20));
        wordsToType.setBounds(35, 50, 400, 150);
        this.add(wordsToType);
    }

    public void resetText() {
        String newPara = paragraph.convertParaToString(paragraph.generateParagraph());
        paragraph.setParagraph(newPara);
        wordsToType.setText("<html><p style=\"width:300px;text-align:center;\">"
                + newPara + "</p></html>");
        textField.setText("");
    }

    public void createTextField() {
        textField = new JTextField();
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

    public Score calculateScore() {
        this.score = new Score();
        String userInputText = textField.getText();
        score.calculateAccuracy(paragraph.getTotalChar(),
                paragraph.getNumTypedCorrect(paragraph, paragraph.getParagraphAsString(), userInputText));
        score.calculateScore(score.getAcc());
        score.setResults();
        player.getScoreboard().addScore(score);
        return this.score;
    }

    public void stopGame() {
        EndGamePanel endGamePanel = new EndGamePanel(this.gamePanel, this.gamePanel.getPlayer(), this.score);
    }

    public Countdown getCountdown() {
        return countdown;
    }
}

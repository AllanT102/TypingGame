package ui.panels;


import model.Paragraph;
import ui.misc.Countdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import static java.awt.Color.*;

public class WordPanel extends JPanel {
    private int width = 500;
    private int height = 500;
    private int charPerLine = 44;

    private Countdown countdown;
    private Paragraph paragraph;
    private JTextField textField;

    public WordPanel() {
        super();
        setLayout(null);
        paragraph = new Paragraph();

        this.countdown = new Countdown();
        for (Component c : countdown.getCountdownIcons()) {
            this.add(c);
        }
        setVisible(true);
    }

    public void init() throws InterruptedException {
        countdown.startCountdown();
        countdown.getTimer().start();
//        // pause main thread until timer is notified
//        synchronized (this) {
//            this.wait();
//            System.out.println("waiting");
//        }
        startGame();
        createTextField();
    }

    public void startGame() {
        JLabel wordsToType = new JLabel("");
        wordsToType.setText("<html><p style=\"width:300px;text-align:center;\">"
                + paragraph.getParagraphAsString() + "</p></html>");
        wordsToType.setFont(new Font("Serif", Font.PLAIN, 20));
        wordsToType.setBounds(35, 50, 400, 150);
        this.add(wordsToType);
    }

    public void createTextField() {
        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    evt.consume();
                } else if (c == KeyEvent.VK_ENTER) {
                    stopGame();
                }
            }
        });
        textField.setBounds(35,height / 2,400, 50);
        textField.setBackground(lightGray);
        textField.setOpaque(true);
        textField.setVisible(true);
        this.add(textField);
    }

    public void stopGame() {

    }
}

package ui.gamefunctionality;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Countdown that represents the countdown that displays on screen
public class Countdown {
    private int sec = 3;
    private int width = 500;
    private int height = 500;

    private List<JComponent> countdownIcons;
    private Timer timer;

    // constructs the countdown
    // EFFECTS: creates a new countdown with an empty list of icons and sets the icons
    public Countdown() {
        countdownIcons = new ArrayList<>();
        setCountdownIcons();
    }

    // MODIFIES: this
    // EFFECTS: adds scaled images to list of icons
    public void setCountdownIcons() {
        ImageIcon threeScaled =
                new ImageIcon(new ImageIcon("./images/three.png").getImage()
                        .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon twoScaled =
                new ImageIcon(new ImageIcon("./images/two.png").getImage()
                        .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon oneScaled =
                new ImageIcon(new ImageIcon("./images/one.png").getImage()
                        .getScaledInstance(75, 75, Image.SCALE_DEFAULT));

        JLabel three = new JLabel(threeScaled);
        JLabel two = new JLabel(twoScaled);
        JLabel one = new JLabel(oneScaled);

        three.setBounds(width / 2 - 60, height / 2 - 90, 100, 100);
        two.setBounds(width / 2 - 60, height / 2 - 90, 100, 100);
        one.setBounds(width / 2 - 60, height / 2 - 90, 100, 100);

        three.setVisible(false);
        two.setVisible(false);
        one.setVisible(false);
        countdownIcons.add(one);
        countdownIcons.add(two);
        countdownIcons.add(three);
    }

    // MODIFIES: this
    // EFFECTS: starts the countdown by constructing a timer object that starts at sec, and displays each image in list
    //          icons and hides previous icon
    public void startCountdown() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sec == 0) {
                        countdownIcons.get(sec).setVisible(false);
                        timer.stop();

//                        // notify your current thread that it can resume execution
//                        synchronized (this) {
//                            this.notify();
//                        }
                    }
                    countdownIcons.get(sec - 1).setVisible(true);
                    if (sec < countdownIcons.size()) {
                        countdownIcons.get(sec).setVisible(false);
                    }
                    sec--;
                } catch (IndexOutOfBoundsException exception) {
                    // do nothing because need to recursively set last image to false,
                    // first picture will never have a greater index
                }
            }
        });
    }

    public List<JComponent> getCountdownIcons() {
        return countdownIcons;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getSec() {
        return sec;
    }
}

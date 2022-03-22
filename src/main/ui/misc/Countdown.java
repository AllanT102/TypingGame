package ui.misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Countdown {
    private int sec = 3;
    private int width = 500;
    private int height = 500;

    private List<JComponent> countdownIcons;
    private Timer timer;

    public Countdown() {
        countdownIcons = new ArrayList<>();
        setCountdownIcons();
    }

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
                    System.out.println(sec);
                    sec--;
                } catch (IndexOutOfBoundsException exception) {
                    // do nothing
                }
            }
        });

        System.out.println("hello");
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

package ui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardPanel extends JPanel implements ActionListener {

    public ScoreboardPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel score1 = new JLabel("score 1");
        JLabel score2 = new JLabel("score 2");
        add(score1);
        add(score2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

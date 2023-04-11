import javax.swing.*;
import java.awt.*;

public class EndGame extends JDialog {

    JPanel panel;
    JLabel label;
    JButton regular;
    JButton vegas;
    JButton vegasCont;
    View view;
    public EndGame(JFrame frame) {
        super(frame, "Instructions", true);
        setLayout(new GridLayout(3, 0));

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 0));
        panel.setBackground(new Color(62, 210, 175));
        label = new JLabel("<html><B>Congratulations for completing the game!</B></html>", SwingConstants.CENTER);

        regular = new JButton("New Regular Rules Game");
        regular.addActionListener(e -> {
            view.controller.setRegular();
            view.newG("New Game");
            setVisible(false);
        });

        vegas = new JButton("New Vegas Rules Game");
        vegas.addActionListener(e -> {
            view.controller.setVegas();
            view.controller.setVegasContinous(false);
            view.newG("New Game");
            setVisible(false);
        });

        vegasCont = new JButton("New Vegas Rules Continuous Game");
        vegasCont.addActionListener(e -> {
            view.controller.setVegas();
            view.controller.setVegasContinous(true);
            view.controller.setVegasScore(0);
            view.newG("New Game");
            setVisible(false);
        });

        add(label);
        panel.add(regular);
        panel.add(vegas);
        panel.add(vegasCont);
        add(panel);

    }

}

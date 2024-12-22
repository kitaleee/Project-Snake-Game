import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MenuPanel extends JPanel {
    JButton startButton, exitButton;

    MenuPanel(JFrame frame) {
        this.setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(3, 1));

        JLabel title = new JLabel("Snake Game", SwingConstants.CENTER);
        title.setFont(new Font("Ink Free", Font.BOLD, 50));
        title.setForeground(Color.green);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        startButton.addActionListener(e -> {
            frame.setContentPane(new GamePanel(frame));
            frame.validate();
        });

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        exitButton.addActionListener(e -> System.exit(0));

        this.add(title);
        this.add(startButton);
        this.add(exitButton);
    }
}

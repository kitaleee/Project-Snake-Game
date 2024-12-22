import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class RankingPanel extends JPanel {
    RankingPanel(JFrame frame) {
        this.setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("All Scores", SwingConstants.CENTER);
        title.setFont(new Font("Ink Free", Font.BOLD, 50));
        title.setForeground(Color.white);
        this.add(title, BorderLayout.NORTH);

        JTextArea rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setBackground(Color.black);
        rankingArea.setForeground(Color.white);
        rankingArea.setFont(new Font("Ink Free", Font.PLAIN, 20));

        StringBuilder rankingText = new StringBuilder();
        List<Integer> scores = loadScores();
        for (int i = 0; i < scores.size(); i++) {
            rankingText.append((i + 1) + ". " + scores.get(i) + "\n");
        }
        rankingArea.setText(rankingText.toString());
        this.add(new JScrollPane(rankingArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        backButton.addActionListener(e -> {
            frame.setContentPane(new MenuPanel(frame));
            frame.validate();
        });
        this.add(backButton, BorderLayout.SOUTH);
    }

    private List<Integer> loadScores() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Collections.reverseOrder());
        return scores;
    }
}

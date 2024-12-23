import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
class RankingPanel extends JPanel {
    RankingPanel(JFrame frame) {
        this.setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Leaderboard", SwingConstants.CENTER);
        title.setFont(new Font("Ink Free", Font.BOLD, 50));
        title.setForeground(Color.white);
        this.add(title, BorderLayout.NORTH);

        JTextArea rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setBackground(Color.black);
        rankingArea.setForeground(Color.white);
        rankingArea.setFont(new Font("Ink Free", Font.PLAIN, 20));

        List<String> scores = loadScores();
        scores = getTopScores(scores, 10); // Limit to top 10 scores
        scores.forEach(score -> rankingArea.append(score + "\n"));
        this.add(new JScrollPane(rankingArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        backButton.addActionListener(e -> {
            frame.setContentPane(new MenuPanel(frame));
            frame.validate();
        });
        this.add(backButton, BorderLayout.SOUTH);
    }

    private List<String> loadScores() {
        Set<String> uniqueScores = new HashSet<>();
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (uniqueScores.add(line)) { // Prevent duplicates
                    scores.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading ranking file: " + e.getMessage());
        }
        return scores;
    }

    private List<String> getTopScores(List<String> scores, int limit) {
    PriorityQueue<Integer> topScores = new PriorityQueue<>(Comparator.reverseOrder()); 
    scores.forEach(score -> {
        try {
            int value = Integer.parseInt(score.split(":")[1].trim());
            topScores.add(value);
            if (topScores.size() > limit) {
                topScores.poll();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid score entry: " + score);
        }
    });
    
    List<String> result = new ArrayList<>();
    int rank = 1;
    while (!topScores.isEmpty()) {
        result.add(rank++ + ". Score: " + topScores.poll());
    }
    return result;
}

}

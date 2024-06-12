package NextGenGaming.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Score implements Serializable {
    private int score;

    public Score() {
        score =0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }
        public void saveScore() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("scores.dat"))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a method to load the top 3 scores from the file
    public static List<Score> loadTopScores() {
        List<Score> topScores = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            while (true) {
                Score score = (Score) inputStream.readObject();
                topScores.add(score);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Sort the scores in descending order
        topScores.sort(Comparator.comparingInt(Score::getScore).reversed());
        // Return only the top 3 scores
        return topScores.subList(0, Math.min(topScores.size(), 3));
    }

}
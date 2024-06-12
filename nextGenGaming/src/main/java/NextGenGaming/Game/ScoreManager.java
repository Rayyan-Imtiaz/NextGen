
package NextGenGaming.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String SCORE_FILE = "scores.dat";

    public static void saveScore(Score score) {
        List<Score> scores = loadScores();
        scores.add(score);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
            oos.writeObject(scores);
            System.out.println("Score saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Score getHighestScore() {
        Score highestScore = new Score();

        List<Score> scores = loadScores();
        for (Score savedScore : scores) {
            if (savedScore.getScore() > highestScore.getScore()) {
                highestScore = savedScore;
            }
        }

        return highestScore;
    }

private static List<Score> loadScores() {
    List<Score> scores = new ArrayList<>();

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORE_FILE))) {
        try {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                // If the object is a List, cast it appropriately
                scores = (List<Score>) obj;
            } else if (obj instanceof Score) {
                // If the object is a single Score, add it to the list
                scores.add((Score) obj);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } catch (EOFException e) {
        // End of file reached, do nothing
    } catch (IOException e) {
        e.printStackTrace();
    }

    return scores;
}
}

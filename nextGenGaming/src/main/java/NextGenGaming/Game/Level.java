package NextGenGaming.Game;
public class Level {
    private int stage;
    private int obstaclesCount;
    public Level() {
        this.stage = 1;
        updateObstaclesCount();
    }
    public Level(int initialStage) {
        this.stage = initialStage;
        updateObstaclesCount();
    }
    public void setStage(int stage) {
        this.stage = stage;
        updateObstaclesCount();
    }
    public int getStage() {
        return stage;
    }
    public int getObstaclesCount() {
        return obstaclesCount;
    }
    public void increaseScore(int score) {
        if (score >= stage * 50) {
            stage++;
            updateObstaclesCount();
        }
    }
    private void updateObstaclesCount() {
        obstaclesCount = stage * 3;
    }
    public void resetStage() {
        stage = 1;
        updateObstaclesCount();
    }
}


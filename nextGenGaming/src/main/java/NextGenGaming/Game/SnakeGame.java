package NextGenGaming.Game;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
public class SnakeGame extends JPanel implements ActionListener, KeyListener ,Serializable,Movement{
    public JButton button = new JButton();
    private final int DOT_SIZE = 50;
    private final int B_WIDTH = 1020;
    private final int B_HEIGHT = 580;
    private BufferedImage background;
    private BufferedImage snakeHeadUp;
    private BufferedImage snakeHeadDown;
    private BufferedImage snakeHeadLeft;
    private BufferedImage snakeHeadRight;
    private BufferedImage snakeBodyVerticle;
    private BufferedImage snakeBodyUP;
    private BufferedImage mice;
    private static Timer timer;
    public boolean gameOver = false;
    private List<Point> snake;
    private int snakeSize;
    private int direction;
    private int miceX;
    private int miceY;
    private boolean miceEaten = true;
    private List<Obstacle> obstacles;
    private Score score;
    private Level level;
    private static final String SCORE_FILE = "highscore.dat";
    public static SnakePanel snakePanel = new SnakePanel();
    public SnakeGame() {
        setLayout(null);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        for (int key : List.of(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_R, KeyEvent.VK_ENTER)) {
            String keyString = KeyStroke.getKeyStroke(key, 0).toString();
            inputMap.put(KeyStroke.getKeyStroke(keyString), keyString);
            actionMap.put(keyString, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyPressed(new KeyEvent(SnakeGame.this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, KeyEvent.CHAR_UNDEFINED));
                }
            });
        }
        initializeImages();
        initGame();
        move();
        button.setBounds(955,0,50,50);
        button.setVisible(true);
        button.addActionListener(this);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        snakePanel.jButton1.addActionListener(this);
        snakePanel.jButton2.addActionListener(this);
        snakePanel.jButton5.addActionListener(this);
        
        this.add(button);
        snakePanel.setVisible(true);
        this.add(snakePanel);
        snakePanel.setBounds(1020,0,150,580);
    }
    private void initializeImages() {
        try {
            background = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\background.png");
            snakeHeadUp = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_head_up.png");
            snakeHeadDown = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_head_down.png");
            snakeHeadLeft = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_head_left.png");
            snakeHeadRight = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_head_right.png");
            snakeBodyUP = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_body_verticle.png");
            snakeBodyVerticle = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\snake_body_horizental.png");
            mice = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\mice.png");
            button.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\icons8-menu-bar.gif"));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    private BufferedImage loadImage(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("Image file not found: " + fileName);
        }
        return ImageIO.read(file);
    }
    private void initGame() {
        easyMode();
        timer.start();
        snake = new LinkedList<>();
        snakeSize = 1;
        for (int i = 0; i < snakeSize; i++) {
            snake.add(new Point(100 - i * DOT_SIZE, 100));
        }
        level = new Level();
        initializeObstacles();
        spawnMice();
        score = new Score();
        gameOver = false;
    }
    private void initializeObstacles() {
        obstacles = new ArrayList<>();
        updateObstacles();
    }
    private void updateObstacles() {
        obstacles.clear();
        int stage = level.getStage();
        switch (stage) {
            case 1:
                //GameSounds.hisSnakeSound();
                obstacles.add(new Obstacle(100, 200, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 200, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 200, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(150, 400, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(850, 200, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(850, 400, DOT_SIZE, DOT_SIZE, Color.RED));
                break;
            case 2:
                try {
                    background = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\background2.png");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                obstacles.add(new Obstacle(100, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 150, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 150, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                GameSounds.hisSnakeSound();
                break;

            case 3:
                try {
                    background = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\background3.png");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                GameSounds.hisSnakeSound();
                obstacles.add(new Obstacle(450, 300, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(500, 300, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(450, 350, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(500, 350, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 100, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 150, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 150, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(850, 450, DOT_SIZE, DOT_SIZE, Color.BLACK));
                break;
            default:
                try {
                    background = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\background3.png");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                obstacles.add(new Obstacle(100, 200, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(100, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 200, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(800, 400, DOT_SIZE, DOT_SIZE, Color.BLACK));
                obstacles.add(new Obstacle(150, 200, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(150, 400, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(850, 200, DOT_SIZE, DOT_SIZE, Color.RED));
                obstacles.add(new Obstacle(850, 400, DOT_SIZE, DOT_SIZE, Color.RED));
                break;


        }
    }
    private int getRandomCoordinate(int max) {
        return new Random().nextInt(max / DOT_SIZE) * DOT_SIZE;
    }
    public void move() {
        int headX = snake.get(0).x;
        int headY = snake.get(0).y;

        if (direction == KeyEvent.VK_RIGHT) {
            headX += DOT_SIZE;
        } else if (direction == KeyEvent.VK_LEFT) {
            headX -= DOT_SIZE;
        } else if (direction == KeyEvent.VK_UP) {
            headY -= DOT_SIZE;
        } else if (direction == KeyEvent.VK_DOWN) {
            headY += DOT_SIZE;
        }

        Point newHead = new Point(headX, headY);

        if (newHead.equals(new Point(miceX, miceY)) && !miceEaten) {
            snakeSize++;
            miceEaten = true;
            spawnMice();
            updateScore();
        } else {
            snake.remove(snakeSize - 1);
        }

        if (snake.contains(newHead) || headX >= B_WIDTH || headX < 0 || headY >= B_HEIGHT || headY < 0) {
            handleGameOver();
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(newHead)) {
                handleGameOver();
            }
        }
        snake.add(0, newHead);
    }
    private void spawnMice() {
        do {
            miceX = getRandomCoordinate(B_WIDTH);
            miceY = getRandomCoordinate(B_HEIGHT);
        } while (miceSpawnedOnObstacle());
        miceEaten = false;
    }
    private boolean miceSpawnedOnObstacle() {
        Point micePoint = new Point(miceX, miceY);
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(micePoint)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void paint(Graphics g) {

        g.drawImage(background, 0, 0, B_WIDTH, B_HEIGHT, null);

        if (timer.isRunning() && !gameOver) {
            drawObstacles(g);
            drawSnake(g);
            drawMice(g);
            drawScore(g);
            snakePanel.repaint();
            button.repaint();
        } else {
            drawGameOver(g);
            snakePanel.repaint();
            button.repaint();
        }


    }
    private void drawObstacles(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }
    private void drawSnake(Graphics g) {
        BufferedImage headImage = null;
        BufferedImage bodyImage = null;

        if (direction == KeyEvent.VK_RIGHT) {
            headImage = snakeHeadRight;
            bodyImage = snakeBodyUP;
        } else if (direction == KeyEvent.VK_LEFT) {
            headImage = snakeHeadLeft;
            bodyImage = snakeBodyUP;
        } else if (direction == KeyEvent.VK_UP) {
            headImage = snakeHeadUp;
            bodyImage = snakeBodyVerticle;
        } else if (direction == KeyEvent.VK_DOWN) {
            headImage = snakeHeadDown;
            bodyImage = snakeBodyVerticle;
        }

        g.drawImage(headImage, snake.get(0).x, snake.get(0).y, DOT_SIZE, DOT_SIZE, null);

        for (int i = 1; i < snakeSize; i++) {
            g.drawImage(bodyImage, snake.get(i).x, snake.get(i).y, DOT_SIZE, DOT_SIZE, null);
        }
    }
    private void drawMice(Graphics g) {
        g.drawImage(mice, miceX, miceY, DOT_SIZE, DOT_SIZE, null);
    }
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.drawString("Score: " + score.getScore(), 10, 20);
        g.drawString("Level: " + level.getStage(), 10, 40);
    }
    private void drawGameOver(Graphics g) {
        String message = "Game Over";
        String restartMessage = "";
        Font font = new Font("Helvetica", Font.BOLD, 48);
        Font smallerFont = new Font("Helvetica", Font.BOLD, 24);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, B_WIDTH / 2 - 150, B_HEIGHT / 2);
        g.setFont(smallerFont);
        g.drawString(restartMessage, B_WIDTH / 2 - 120, B_HEIGHT / 2 + 60);
    }
    private void handleGameOver() {
        GameSounds.crashSnakeSound();
        timer.stop();
        gameOver = true;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (timer.isRunning() && !gameOver) {
            move();
            repaint();
        
        }if (e.getSource() == snakePanel.jButton5) {
            List<Score> topScores = Score.loadTopScores();
            StringBuilder topScoresMessage = new StringBuilder("Highest Score:\n");
            for (int i = 0; i < topScores.size(); i++) {
            topScoresMessage.append(i + 1).append(". ").append(topScores.get(i).getScore()).append("\n");
            }
            JOptionPane.showMessageDialog(this, topScoresMessage.toString(), "Top Scores", JOptionPane.INFORMATION_MESSAGE);
        }if(e.getSource()==snakePanel.jButton2){
            JOptionPane.showMessageDialog(this, "Score is saved Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void updateScore() {
        GameSounds.eatSnakeSound();
        score.increaseScore(10);
        level.increaseScore(score.getScore());
        if (level.getStage() > 1) {
            updateObstacles();
        }
        score.saveScore();
    }
    public void easyMode(){
        timer= new Timer(300,this);
    }
    public void mediumMode(){
        timer=new Timer(200,this);
        
    }
    public void hardMode(){
        timer = new Timer(100,this);
    }
    public void impossibleMode(){
        timer = new Timer(50,this);
    }
    public void stopGame(){
        timer = new Timer(1000,this);
    }
    
    public void startGame(){
        if(!gameOver)
            timer.start();
        
    }
    

    public void restartGame() {
        gameOver = false;
        timer = new Timer(300, this);
        timer.start();
        level.resetStage();
        updateObstacles();
        button.repaint();
        snakePanel.repaint();

        try {
            background = loadImage("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\background.png");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        snake.clear();
        snakeSize = 1;
        for (int i = 0; i < snakeSize; i++) {
            snake.add(new Point(100 - i * DOT_SIZE, 100));
        }
        spawnMice();
        score.resetScore();
        direction = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && KeyEvent.VK_RIGHT != direction) {
            direction = KeyEvent.VK_LEFT;
            GameSounds.moveSnakeSound();
        } else if (key == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT) {
            direction = KeyEvent.VK_RIGHT;
            GameSounds.moveSnakeSound();
        } else if (key == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) {
            direction = KeyEvent.VK_UP;
            GameSounds.moveSnakeSound();
        } else if (key == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) {
            direction = KeyEvent.VK_DOWN;
            GameSounds.moveSnakeSound();
        }else if(key==KeyEvent.VK_ENTER){
            if(!gameOver){
                this.stopGame();
            }    
        }

    }
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    @Override
    public void keyTyped(KeyEvent e){
        
    }
}
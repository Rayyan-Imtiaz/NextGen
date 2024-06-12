
package NextGenGaming.Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, MouseListener, KeyListener,Movement {

    private static final long serialVersionUID = 1L;
    public static BirdPanel birdMenu= new BirdPanel();
    private final int WIDTH = 1020, HEIGHT = 580;
    public JButton button= new JButton();
    private Rectangle bird;
    private ArrayList<Rectangle> columns;
    private int ticks, yMotion, score;
    public boolean gameOver, started;
    private Random rand;
    private Image birdImage;
    Timer timer;
    
    
    public FlappyBird() {
        timer = new Timer(20, this);
        button.setVisible(true);
        button.addActionListener(this);

        rand = new Random();
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(this);
        addKeyListener(this);
        setLayout(null);
        birdMenu.setVisible(true);
        add(birdMenu);
        birdMenu.setBounds(1020,0,150,580);
        add(button);
        button.setBounds(955,0,50,50);
        initializeImages();
        bird = new Rectangle(WIDTH/2-100, HEIGHT/2 , 50, 35);
        columns = new ArrayList<>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }
        public void initializeImages(){
            button.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\icons8-menu-bar.gif"));
        try {
            birdImage = ImageIO.read(new File("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\bird.png"));
        } catch (IOException ex) {
            Logger.getLogger(FlappyBird.class.getName()).log(Level.SEVERE, null, ex);
        }
            birdImage = birdImage.getScaledInstance(50, 35, Image.SCALE_SMOOTH);
        }


	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
                    columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
                    columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
                    columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
                    columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
            g.setColor(Color.green.darker());
            g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void move()
	{
		if (gameOver)
		{
                        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button){
            timer.stop();
            birdMenu.setVisible(true);
            birdMenu.setBounds(1020,0,150,580);
            button.setVisible(false);
            new Thread(() -> {
                for (int i = 0; i<=150; i+=5) {
                    birdMenu.repaint();
                    birdMenu.setBounds(1020-i, 0, 150, 580);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
		}

		else {
			int speed = 10;
			ticks++;
			if (started)
			{
				for (int i = 0; i < columns.size(); i++)
				{
					Rectangle column = columns.get(i);
					column.x -= speed;
				}
				if (ticks % 2 == 0 && yMotion < 15)
				{
					yMotion += 2;
				}
				for (int i = 0; i < columns.size(); i++)
				{
					Rectangle column = columns.get(i);
					if (column.x + column.width < 0)
					{
						columns.remove(column);
						if (column.y == 0)
						{
							addColumn(false);
						}
					}
				}
				bird.y += yMotion;

				for (Rectangle column : columns)
				{
					if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
					{
						score++;
					}

					if (column.intersects(bird))
					{
						gameOver = true;
						GameSounds.hitBirdSound();
						timer.stop();
					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;
					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
				GameSounds.hitBirdSound();
				timer.stop();
			}
			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
				GameSounds.hitBirdSound();
				timer.stop();
			}
		}
		repaint();
        }
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        button.repaint();
        birdMenu.repaint();
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        // Draw bird image
        g.drawImage(birdImage, bird.x, bird.y, this);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        if (!started) {
            g.setFont(new Font("Arial", Font.BOLD, 80));
            g.drawString("Press W to start!", 180, HEIGHT / 2 - 50);
        }

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 90));
            g.drawString("Game Over!", 205, HEIGHT / 2 - 60);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("Press 'W' to play again!", 175, HEIGHT / 2 - 0);

        }

        if (!gameOver && started) {
            g.drawString(String.valueOf(score), WIDTH / 2 - 450, 50);
        }
    }



	@Override
	public void mouseClicked(MouseEvent e)
	{
		move();
                GameSounds.jumpBirdSound();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
            if (e.getKeyCode() == KeyEvent.VK_W)
		{
                    if(gameOver==true){
                        restart();
		}
		else{
                    move();
                    GameSounds.jumpBirdSound();
		}

            }
	}
	public void easyMode(){
		timer= new Timer(20,this);
	}
	public void mediumMode(){
		timer=new Timer(16,this);

	}
	public void hardMode(){
		timer = new Timer(13,this);
	}
	public void impossibleMode(){
		timer = new Timer(10,this);
	}

	public void restart(){
		timer.restart();
		move();
		repaint();

	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

}
package NextGenGaming.Game;
import static NextGenGaming.Game.SnakeGame.snakePanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;


public class Animation extends JFrame implements ActionListener {
    LoginPanel  loginPanel = new LoginPanel();
    IntroPanel introPanel = new IntroPanel();
    SignUpPanel signUpPanel= new SignUpPanel();
    JButton menuButton =new JButton();
    GameMenu menu = new GameMenu();
    SnakeGame game = new SnakeGame();
    FlappyBird flappyBirdPanel = new FlappyBird();
    MineSweeper mineSweeper = new MineSweeper();
    JButton button = new JButton();
    
    public Animation(){
        initComponent();
    }
    public void initComponent(){
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(1020,580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
       
        introPanel.setLayout(null);
        menuButton.setVisible(false);
        
        button.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        button.setForeground(new java.awt.Color(0, 0, 0));
        button.setText("Press here to Start");
        button.setContentAreaFilled(false);
        button.setBounds(430, 690, 150, 30);
        button.setCursor(new Cursor(HAND_CURSOR));
        button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                buttonColor(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                buttonColorRemove(evt);
            }
        });
        
        button.addActionListener(this);
        
        loginPanel.jButton2.addActionListener(this);
        signUpPanel.jButton2.addActionListener(this);
        signUpPanel.jButton1.addActionListener(this);
        loginPanel.jButton1.addActionListener(this);
        
        menu.jButton1.addActionListener(this);
        menu.jButton2.addActionListener(this);
        menu.jButton3.addActionListener(this);
        
        game.button.addActionListener(this);
        
        SnakeGame.snakePanel.jButton1.addActionListener(this);
        SnakeGame.snakePanel.jButton2.addActionListener(this);
        SnakeGame.snakePanel.jButton3.addActionListener(this);
        SnakeGame.snakePanel.jButton4.addActionListener(this);
        
        flappyBirdPanel.button.addActionListener(this);
        
        FlappyBird.birdMenu.jButton1.addActionListener(this);
        FlappyBird.birdMenu.jButton2.addActionListener(this);
        FlappyBird.birdMenu.jButton3.addActionListener(this);
        FlappyBird.birdMenu.jButton4.addActionListener(this);
        FlappyBird.birdMenu.jButton5.addActionListener(this);
        
        MineSweeper.panel2.jButton1.addActionListener(this);
        
        
        JLabel label = new JLabel();
        label.setBounds(600, 150, 200, 200);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\logo.gif").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
        
        introPanel.add(label);
        introPanel.add(button);
        
        add(introPanel);
        introPanel.setBounds(0, 0, 1020, 580);
        
        add(loginPanel);
        loginPanel.setBounds(1020,0,1020,580);
        loginPanel.setVisible(true);
        
        add(signUpPanel);
        signUpPanel.setBounds(1020,0,1020,580);
        signUpPanel.setVisible(true);
        
        add(menu);
        menu.setBounds(1020,0,1020,580);
        menu.setVisible(true);
        
        game.setLayout(null);
        game.setVisible(true);
        add(game);
        game.setBounds(1020,0,1020,560);
        
        setVisible(true);  
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                abcd(evt);
            }
        });     
    }
    
    
    public void abcd(java.awt.event.WindowEvent evt){
        new Thread(() -> {
            int i =0;
            while (i <= 580) {
                introPanel.jLabel1.setBounds(-450+i,200,450,100);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
               i += 3;
            }
        }).start();
        
        new Thread(() -> {
            int i =0;
            while (i <= 200) {
                button.setBounds(430, 690-i, 150, 30);
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
               i += 1;
            }
        }).start();
    }
    
    public void buttonColor(MouseEvent evt){
        button.setContentAreaFilled(true);
        button.setBackground(new Color(0,255,204));
    }
    public void buttonColorRemove(MouseEvent evt){
        button.setContentAreaFilled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==button) {
            new Thread(() -> {
                for (int i = 0; i <= 1020; i += 5) {
                    introPanel.setBounds(-i, 0, 1020, 580);
                    loginPanel.setBounds(1020-i, 0, 1020, 580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
        if(e.getSource()==loginPanel.jButton2){
            
                new Thread(() -> {
                    for (int i = 0; i <= 1020; i+=5) {
                        loginPanel.setBounds(-i, 0, 1020, 580);
                        signUpPanel.setBounds(1020-i,0,1020,580);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();    
        }
        
        if(e.getSource()==signUpPanel.jButton2){
            new Thread(() -> {
                for (int i = 1020; i>=0; i-=5) {
                    loginPanel.setBounds(-i, 0, 1020, 580);
                    signUpPanel.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } 
        if(e.getSource()==signUpPanel.jButton1){
            if(signUpPanel.validSignIn()==true){
                new Thread(() -> {
                    for (int i = 0; i<=1020; i+=5) {
                        menu.setBounds(1020-i, 0, 1020, 580);
                        signUpPanel.setBounds(-i,0,1020,580);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        if(e.getSource()==loginPanel.jButton1){
          if(loginPanel.isUserValid()==true){
              System.out.println("Login Successfull");
            new Thread(() -> {
                for (int i = 0; i<=1020; i+=5) {
                    menu.setBounds(1020-i, 0, 1020, 580);
                    loginPanel.setBounds(-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start(); 
        }else{
                JOptionPane.showMessageDialog(this, "Data is invalid try again","Invalid Data",  JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==menu.jButton1){
            game.setLayout(null);
            game.setBounds(1020,0,1020,580);
            game.stopGame();
            game.restartGame();
            game.startGame();
            menu.setBounds(0,0,1020,560);
            new Thread(() -> {
                for (int i = 0; i<=1020; i+=5) {
                    menu.setBounds(-i, 0, 1020, 580);
                    game.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }).start(); 
        }if(e.getSource()==game.snakePanel.jButton4){
            game.setLayout(null);
            SnakeGame.snakePanel.setBounds(1020,0,150,580);
            SnakeGame.snakePanel.setVisible(false);
            SnakeGame.snakePanel.repaint();
            game.button.setVisible(true);
            game.button.repaint();
            String s = (String)SnakeGame.snakePanel.jComboBox1.getSelectedItem();
            if(s.equalsIgnoreCase("Hard")){
                game.hardMode();
                game.startGame();  
            }
            if (s.equalsIgnoreCase("Easy")){
                game.easyMode();
                game.startGame();
            }
            if (s.equalsIgnoreCase("Medium")){
                game.mediumMode();
                game.startGame();
            }
            if(s.equalsIgnoreCase("Impossible")){
                game.impossibleMode();
                game.startGame();
            }
        }
        if (e.getSource()==SnakeGame.snakePanel.jButton1){
            game.button.setVisible(true);
            SnakeGame.snakePanel.setVisible(false);
            game.gameOver=true;
            game.stopGame();
            menu.setBounds(0,0,1020,580);
            game.setBounds(1020,0,1020,580);
            new Thread(() -> {
                for (int i =1020; i>=0; i-=5) {
                    game.repaint();
                    menu.setBounds(-i, 0, 1020, 580);
                    game.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start(); 
        }if(e.getSource()==game.button){
            game.stopGame();
            SnakeGame.snakePanel.setVisible(true);
            SnakeGame.snakePanel.setBounds(1020,0,150,580);
            game.button.setVisible(false);
            new Thread(() -> {
                for (int i = 0; i<=150; i+=5) {
                    SnakeGame.snakePanel.repaint();
                    SnakeGame.snakePanel.setBounds(1020-i, 0, 150, 580);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }if( e.getSource()==SnakeGame.snakePanel.jButton3){
            SnakeGame.snakePanel.setBounds(1020,0,150,580);
            game.button.setVisible(true);
            game.gameOver=true;
            game.restartGame();    
        }if(e.getSource()==menu.jButton3){
            
            add(flappyBirdPanel);
            flappyBirdPanel.setBounds(1020,0,1020,580);
            flappyBirdPanel.requestFocusInWindow();


            new Thread(() -> {
                for (int i = 0; i<=1020; i+=5) {
                    menu.setBounds(-i, 0, 1020, 580);
                    flappyBirdPanel.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }).start(); 
        }
        if (e.getSource()==menu.jButton2)
        {
            add(mineSweeper);
            mineSweeper.resetBoard();
            mineSweeper.setBounds(1020,0,1020,580);
            new Thread(() -> {
                for (int i = 0; i<=1020; i+=5) {
                    menu.setBounds(-i, 0, 1020, 580);
                    mineSweeper.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
        if(e.getSource()==MineSweeper.panel2.jButton1)
        {
            mineSweeper.panel2.stopMineSweeperTimer();
            mineSweeper.setBounds(0,0,1020,580);
            new Thread(() -> {
                for (int i = 1020;i>=0 ;i-=5) {
                    menu.setBounds(-i, 0, 1020, 580);
                    mineSweeper.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        
        }
        if( e.getSource()==FlappyBird.birdMenu.jButton3){
            FlappyBird.birdMenu.setBounds(1020,0,150,580);
            flappyBirdPanel.button.setVisible(true);
            flappyBirdPanel.started=false;
            flappyBirdPanel.timer.stop();
            flappyBirdPanel.restart();
        }
        if (e.getSource()==FlappyBird.birdMenu.jButton1){
            flappyBirdPanel.button.setVisible(true);
            FlappyBird.birdMenu.setVisible(false);
            flappyBirdPanel.restart();
            flappyBirdPanel.timer.stop();
            menu.setBounds(0,0,1020,580);
            flappyBirdPanel.setBounds(1020,0,1020,580);
            new Thread(() -> {
                for (int i =1020; i>=0; i-=5) {
                    flappyBirdPanel.repaint();
                    menu.setBounds(-i, 0, 1020, 580);
                    flappyBirdPanel.setBounds(1020-i,0,1020,580);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
        if(e.getSource()==FlappyBird.birdMenu.jButton4){
            flappyBirdPanel.setLayout(null);
            FlappyBird.birdMenu.setBounds(1020,0,150,580);
            FlappyBird.birdMenu.setVisible(false);
            FlappyBird.birdMenu.repaint();
            flappyBirdPanel.button.setVisible(true);
            flappyBirdPanel.button.repaint();
            String s = (String)FlappyBird.birdMenu.jComboBox1.getSelectedItem();
            if(s.equalsIgnoreCase("Hard")){
                flappyBirdPanel.hardMode();
                flappyBirdPanel.restart();
            }
            if (s.equalsIgnoreCase("Easy")){
                flappyBirdPanel.easyMode();
                flappyBirdPanel.restart();
            }
            if (s.equalsIgnoreCase("Medium")){
                flappyBirdPanel.mediumMode();
                flappyBirdPanel.restart();
            }
            if(s.equalsIgnoreCase("Impossible")){
                flappyBirdPanel.impossibleMode();
                flappyBirdPanel.restart();
            }
            if (e.getSource() == FlappyBird.birdMenu.jButton5) {
                java.util.List<Score> topScores = Score.loadTopScores();
                StringBuilder topScoresMessage = new StringBuilder("Highest Score:\n");
                for (int i = 0; i < topScores.size(); i++) {
                    topScoresMessage.append(i + 1).append(". ").append(topScores.get(i).getScore()).append("\n");
                }
                JOptionPane.showMessageDialog(this, topScoresMessage.toString(), "Top Scores", JOptionPane.INFORMATION_MESSAGE);
            }
            if(e.getSource()==FlappyBird.birdMenu.jButton2){
                JOptionPane.showMessageDialog(this, "Score is saved Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
    }
    
}

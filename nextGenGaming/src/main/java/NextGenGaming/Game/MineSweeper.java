package NextGenGaming.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class MineSweeper extends JPanel implements ActionListener,MouseListener {

    private final int tileSize = 50;
    private final int numRows = 8;
    private final int numCols = numRows;
    private final int boardWidth = (numCols * tileSize)+60;
    private final int boardHeight = (numRows * tileSize)+30;
    public static SweeperPanel panel2;
    private JPanel frame=new JPanel();
    private JLabel textLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private JPanel boardPanel = new JPanel();
    private final int mineCount = 10;
    private MineTile[][] board = new MineTile[numRows][numCols];
    private ArrayList<MineTile> mineList;
    private Random random = new Random();
    private ThemeClass themeClass = new ThemeClass();
    private int tilesClicked = 0;
    private boolean gameOver = false;
    private boolean cleared = false;

    public MineSweeper() {
        initializeBoard();
        setMines();
        initializeFrame();
    }

    private void initializeFrame() {

        panel2 = new SweeperPanel();
        panel2.jButton2.addActionListener(this);
        panel2.jButton3.addActionListener(this);
        panel2.jButton4.addActionListener(this);
        panel2.jButton5.addActionListener(this);
        panel2.jButton6.addActionListener(this);
        panel2.jButton7.addActionListener(this);
        panel2.jButton8.addActionListener(this);
        
        setSize(1020,560);        
        setLayout(null);

        frame.setSize(boardWidth, boardHeight);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesweeper: " + Integer.toString(mineCount));
        textLabel.setBackground(new Color(204,102,0));
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setVisible(true);
        frame.add(boardPanel);

        panel2.setVisible(true);
        add(panel2);
        panel2.setBounds(510,0,510,580);
        add(frame);
        frame.setBounds(0,0,515,540);
        
        frame.setVisible(true);
        setVisible(true);    


    }

    private void initializeBoard() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;
                configureMineTile(tile);
                boardPanel.add(tile);
            }
        }
        updateThemeColors();
    }

    private void configureMineTile(MineTile tile) {
        tile.setFocusable(false);
        tile.setMargin(new Insets(0, 0, 0, 0));
        tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));

        tile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
            }
        });

        tile.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!gameOver && tile.isEnabled()) {
                    tile.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
        }

            public void mouseExited(MouseEvent e) {
                if (!gameOver && tile.isEnabled()) {
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            }
        });
    }

    private void setMines() {
        mineList = new ArrayList<>();
        int mineLeft = mineCount;
        while (mineLeft > 0) {
            int r = random.nextInt(numRows);
            int c = random.nextInt(numCols);
            MineTile tile = board[r][c];
            if (!mineList.contains(tile)) {
                mineList.add(tile);
                mineLeft--;
            }
        }
    }

    private void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("💣");
        }
        gameOver = true;
        textLabel.setText("Game Over!");
        panel2.stopMineSweeperTimer();
        GameSounds.sweepLost();
    }

    private void checkMine(int r, int c) {
        if (r < 0 || r >= numRows || c < 0 || c >= numCols || !board[r][c].isEnabled()) {
            return;
        }

        MineTile tile = board[r][c];
        tile.setEnabled(false);
        tilesClicked++;

        int minesFound = countAdjacentMines(r, c);
        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText("");
            revealAdjacentMines(r, c);
        }
        tile.setBackground(Color.WHITE);

        if (tilesClicked == numRows * numCols - mineList.size()) {
            gameOver = true;
            textLabel.setText("Mines Cleared!");
            cleared = true;
            GameSounds.sweepWin();
        }
    }

    private int countAdjacentMines(int r, int c) {
        int minesFound = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                minesFound += countMine(i, j);
            }
        }
        return minesFound - countMine(r, c);
    }

    private void revealAdjacentMines(int r, int c) {
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                checkMine(i, j);
            }
        }
    }

    private void handleMouseClick(MouseEvent e) {
        if (gameOver) {
            return;
        }

        MineTile tile = (MineTile) e.getSource();
        if (e.getButton() == MouseEvent.BUTTON1) {
            handleLeftClick(tile);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            handleRightClick(tile);
        }
    }

    private void handleLeftClick(MineTile tile) {
        panel2.startMineSweeperTimer();
        if (tile.getText().equals("")) {
            if (mineList.contains(tile)) {
                revealMines();
            } else {
                checkMine(tile.r, tile.c);
            }
            GameSounds.sweepLeft();
        }
    }
    private MineTile[][] getMineTiles() {
        MineTile[][] mineTiles = new MineTile[numRows][numCols];
        Component[] components = boardPanel.getComponents();
        int index = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                mineTiles[i][j] = (MineTile) components[index++];
            }
        }

        return mineTiles;
    }

    private void handleRightClick(MineTile tile) {
        if (tile.getText().equals("") && tile.isEnabled()) {
            tile.setText("🚩");
            GameSounds.sweepRight();
        } else if (tile.getText().equals("🚩")) {
            tile.setText("");
            GameSounds.sweepRight();
        }
    }

    private int countMine(int r, int c) {
        if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
            return 0;
        }
        return mineList.contains(board[r][c]) ? 1 : 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==panel2.jButton2){
            if(cleared){
                MineFile.store(SweeperPanel.getSecond());
                JOptionPane.showMessageDialog(this, "Game saved Succesfully","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "First Complete the game","Error", JOptionPane.INFORMATION_MESSAGE);        }
        }
    
        if (e.getSource() == panel2.jButton4) {
            textLabel.setBackground(new Color(204,102,0));
            panel2.setBackground(new Color(204,102,0));
            MineTile[][] mineTiles = getMineTiles();
            themeClass.setOrangeTheme(mineTiles);
        }
        if (e.getSource() == panel2.jButton5) {
            textLabel.setBackground(Color.BLUE.brighter());
            panel2.setBackground(Color.BLUE.brighter());
            MineTile[][] mineTiles = getMineTiles();
            themeClass.setBlueTheme(mineTiles);
        }
        if (e.getSource() == panel2.jButton6) {
            textLabel.setBackground(Color.RED.brighter());
            panel2.setBackground(Color.RED.brighter());
            MineTile[][] mineTiles = getMineTiles();
            themeClass.setRedTheme(mineTiles);
        }
        if (e.getSource() == panel2.jButton7) {
            textLabel.setBackground(Color.LIGHT_GRAY);
            panel2.setBackground(Color.LIGHT_GRAY);
            MineTile[][] mineTiles = getMineTiles();
            themeClass.setGreyTheme(mineTiles);
        }
        if(e.getSource()==panel2.jButton3){
            resetBoard();
        }
        if(e.getSource()==panel2.jButton8)
        {
            MineFile.displayHigh();
        }
        
    }


    private void updateThemeColors() {
        MineTile[][] mineTiles = getMineTiles();
        themeClass.setOrangeTheme(mineTiles); 
    }
    public void resetBoard() {

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                MineTile tile = board[r][c];
                tile.setText("");
                tile.setEnabled(true);
                tile.setBackground(null); 
            }
        }
    
        tilesClicked = 0;
        gameOver = false;
        mineList.clear();
        setMines();

        updateThemeColors();
        textLabel.setText("Minesweeper: " + Integer.toString(mineCount));

        panel2.resetMineSweeperTimer();
    }   

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

}

package NextGenGaming.Game;
import javax.swing.*;

class GameFrame extends JFrame  {

    public final int WIDTH = 1020, HEIGHT = 580;
    public GameFrame()
    {
      initializeFrame();
    }
    void initializeFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
}

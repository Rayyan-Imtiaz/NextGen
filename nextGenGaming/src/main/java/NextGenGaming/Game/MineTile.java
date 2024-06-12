package NextGenGaming.Game;
import javax.swing.*;
import java.awt.*;

public class MineTile extends JButton {
    int r;
    int c;

    public MineTile(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public void setBackgroundColor(Color color) {
        setBackground(color);
    }
}

package NextGenGaming.Game;
import java.awt.*;
class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Obstacle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    public boolean collidesWith(Point point) {
        return (point.x >= x && point.x < x + width && point.y >= y && point.y < y + height);
    }
}

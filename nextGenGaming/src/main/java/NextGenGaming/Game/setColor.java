
package NextGenGaming.Game;

import java.awt.Color;

public abstract class setColor {
    public abstract void setOrangeTheme(MineTile[][] grid);

    public abstract void setBlueTheme(MineTile[][] grid);

    public abstract void setRedTheme(MineTile[][] grid);

    public abstract void setGreyTheme(MineTile[][] grid);
    
    public void setTileColors(MineTile[][] grid, Color lightColor, Color darkColor) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    grid[i][j].setBackground(lightColor);
                } else {
                    grid[i][j].setBackground(darkColor);
                }
            }
        }
    }
}

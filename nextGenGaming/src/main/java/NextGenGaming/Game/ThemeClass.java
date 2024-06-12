package NextGenGaming.Game;
import javax.swing.*;
import java.awt.*;

public class ThemeClass extends setColor{
    @Override
    public void setOrangeTheme(MineTile[][] grid) {
        super.setTileColors(grid, Color.ORANGE.brighter(), Color.ORANGE.darker());
    }
    @Override
    public void setBlueTheme(MineTile[][] grid) {
        super.setTileColors(grid, Color.BLUE.brighter(), Color.BLUE.darker());
    }
    @Override
    public void setRedTheme(MineTile[][] grid) {
        super.setTileColors(grid, Color.RED.brighter(), Color.RED.darker());
    }
    @Override
    public void setGreyTheme(MineTile[][] grid) {
        super.setTileColors(grid, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }


}

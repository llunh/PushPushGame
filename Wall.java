package term_;

import java.awt.*;
import javax.swing.*;

public class Wall {
    private int x;
    private int y;
    private Image wallImage;

    public Wall(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        wallImage = new ImageIcon("wall.png").getImage();
    }

    public void draw(Graphics g, int tileSize) {
        g.drawImage(wallImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

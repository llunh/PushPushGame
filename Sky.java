package term_;

import java.awt.*;
import javax.swing.*;

public class Sky {
    private int x;
    private int y;
    private Image skyImage;

    public Sky(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        skyImage = new ImageIcon("sky.png").getImage();
    }

    public void draw(Graphics g, int tileSize) {
        g.drawImage(skyImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}


package term_;

import java.awt.*;
import javax.swing.*;

public class Block {
    private int x;
    private int y;
    private Image blockImage;

    public Block(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        blockImage = new ImageIcon("block.png").getImage();
    }

    public void move(int _dx, int _dy) {
        x += _dx;
        y += _dy;
    }

    public void setPosition(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public void draw(Graphics g, int tileSize) {
        g.drawImage(blockImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

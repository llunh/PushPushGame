package term_;

import java.awt.*;
import javax.swing.*;

public class Player {
    private int x;
    private int y;
    private Image playerImage;

    public Player(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        playerImage = new ImageIcon("character.png").getImage();
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
        // 이미지 그리기
        g.drawImage(playerImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

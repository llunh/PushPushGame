package term_;

import java.awt.*;
import javax.swing.*;

public class Goal {
    private int x;
    private int y;
    private Image goalImage;

    public Goal(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        goalImage = new ImageIcon("goal.gif").getImage();
    }

    public void draw(Graphics g, int tileSize) {
        // 원래는 초록색 원을 그렸지만 이미지로 대체
        g.drawImage(goalImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

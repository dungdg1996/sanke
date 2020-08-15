package model;

import java.awt.*;

public class Point extends GameObject {
    public Point(int x, int y) {
        super(x, y);
    }

    public Point() {
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(floatX, floadY, 10, 10);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(floatX, floadY, 10, 10);
    }
}

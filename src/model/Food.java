package model;

import view.GamePanel;
import observer.ObserverReset;

import java.awt.*;
import java.util.Random;

public class Food extends GameObject implements ObserverReset {
    private final Random random;

    public Food() {
        random = new Random();
    }

    public void init() { // Khoi tao

        int x = random.nextInt(GamePanel.B_WIDTH);
        int y = random.nextInt(GamePanel.B_HEIGHT);

        x = x - x % 10; // conf khoong x giu nguyeen
        y = y - y % 10;

        Rectangle rect = new Rectangle(x, y, 10, 10);
        setFloatX(rect.x); // quan trọng chỗ lày
        setFloadY(rect.y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(getFloatX(), getFloadY(), 10, 10);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(getFloatX(), getFloadY(), 10, 10);
    }

    @Override
    public String toString() {
        return "Food{" +
                "x=" + floatX +
                ", y=" + floadY +
                '}';
    }

    @Override
    public void update() {
        init();
    }
}

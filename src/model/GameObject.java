package model;

import java.awt.*;

public abstract class GameObject {
    protected int floatX;
    protected int floadY;

    public GameObject() {
    }

    public GameObject(int floatX, int floadY) {
        this.floatX = floatX;
        this.floadY = floadY;
    }

    public int getFloatX() {
        return floatX;
    }

    public void setFloatX(int floatX) {
        this.floatX = floatX;
    }

    public int getFloadY() {
        return floadY;
    }

    public void setFloadY(int floadY) {
        this.floadY = floadY;
    }

    public abstract void draw(Graphics graphics);

}

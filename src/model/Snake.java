package model;

import observer.ObserverReset;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject implements ObserverReset {
    public static final int DIR_LEFT = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_UP = 2;
    public static final int DIR_DOWN = 3;
    public static final int INIT_SIZE = 10; // kich thuoc dau game

    private final List<Point> listPoints;
    private int direction;

    public Snake() {
        super(30, 30);
        listPoints = new ArrayList<>();
        init();
    }

    public void init(){
        listPoints.clear();
        for (int i = INIT_SIZE; i >= 0; i--){
            listPoints.add(new Point(floatX - i*10 , floadY));
        }
        this.direction = DIR_RIGHT;
    }

    public void move(){
        for (int i = 0; i < listPoints.size() - 1; i++){
            Point truoc = listPoints.get(i);
            Point sau = listPoints.get(i + 1);
            truoc.setFloatX(sau.getFloatX());
            truoc.setFloadY(sau.getFloadY());
        }
        Point head = getHead();
        switch (direction){
            case DIR_LEFT:
                head.setFloatX(head.getFloatX() - 10);
                break;
            case DIR_RIGHT:
                head.setFloatX(head.getFloatX() + 10);
                break;
            case DIR_UP:
                head.setFloadY(head.getFloadY() - 10);
                break;
            case DIR_DOWN:
                head.setFloadY(head.getFloadY() + 10);
                break;
        }

    }

    @Override
    public void draw(Graphics graphics) {
        for (Point point : listPoints) {
            point.draw(graphics);
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public List<Point> getListPoints() {
        return listPoints;
    }

    public Point getHead() {
        return listPoints.get(listPoints.size() - 1);
    }

    @Override
    public int getFloatX() {
        return getHead().getFloatX();
    }

    @Override
    public int getFloadY() {
        return getHead().getFloadY();
    }

    @Override
    public String toString() {
        return "Snake{" +
                "x=" + floatX +
                ", y=" + floadY +
                '}';
    }

    @Override
    public void update() {
        init();
    }
}

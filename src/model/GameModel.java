package model;

import observer.ObserverReset;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Subject {
    private Snake snake;
    private Food food;

    private final List<ObserverReset> observerResets = new ArrayList<>();

    public GameModel(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public void registerObserver(ObserverReset o) {
        observerResets.add(o);
    }

    @Override
    public void removeObserver(ObserverReset o) {
        observerResets.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (ObserverReset o : observerResets) o.update();
    }
}

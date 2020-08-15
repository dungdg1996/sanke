package view;

import model.Food;
import model.Point;
import model.Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    public static final int B_WIDTH = 500; // chiều ngang mặt định
    public static final int B_HEIGHT = 300; // chiều dọc mặt định
    public static final int DELAY = 100; // tốc độ di chuyển của rắn


    private final Snake snake;
    private final Food food;
    private boolean alive = true; // còn sống
    private int scores;

    private Timer timer;

    public GamePanel() {
        snake = new Snake();
        food = new Food();
        initBoard();
    }

    public GamePanel(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
        timer = new Timer(DELAY, this);

        initBoard();
    }

    private void initBoard() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        restartGame();
    }

    public void restartGame() { // khởi động lại game
        food.init();
        snake.init();
        scores = 0;
        alive = true;
        if (timer != null) timer.stop();
        System.gc();
    }

    public void startGame(){
        if (isPause()) timer.start();
    }

    public void pause(){
        if (!isPause()) timer.stop();
    }

    public void pauseOrResumeGame(){ // dừng game hoặc tiếp tục
        if (timer.isRunning()){
            timer.stop();
        }
        else {
            timer.start();
        }
    }

    public boolean isPause(){
        return !timer.isRunning();
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) { // vẽ rắn, thức ăn
        if (alive) {
            snake.draw(g);
            food.draw(g);
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) { // gameOver
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 25);
        FontMetrics metr = getFontMetrics(small);
        String diemSo = "Điểm số: " + scores;

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 20);
        g.drawString(diemSo, (B_WIDTH - metr.stringWidth(diemSo)) / 2, B_HEIGHT / 2 + 20);
    }

    private void checkFood() { // khi rắn ăn mồi
        while (food.getFloatX() == snake.getFloatX() && food.getFloadY() == snake.getFloadY()) {

            snake.getListPoints().add(0, new Point()); // thêm độ dài rắn

            scores += 5; // cộng 5 điểm
            food.init(); // đặt mồi mới

            int delay = timer.getDelay();
            if (delay > 40) timer.setDelay(delay - 5);// tăng tốc độ di chuyển khi ăn được mồi
        }
    }

    private void checkCollision() {
        Point head = snake.getHead(); // lấy điểm đầu tiên của rắn

        // Tự ăn mình thì chết
        for (int i = 0; i < snake.getListPoints().size() - 1; i++) {
            Point point = snake.getListPoints().get(i);
            if (head.getFloatX() == point.getFloatX() && head.getFloadY() == point.getFloadY()) {
                alive = false;
                break;
            }
        }

        // đụng tường thì quay lại
        if (head.getFloadY() >= B_HEIGHT) {
            head.setFloadY(0);
        }

        if (head.getFloadY() < 0) {
            head.setFloadY(B_HEIGHT);
        }

        if (head.getFloatX() >= B_WIDTH) {
            head.setFloatX(0);
        }

        if (head.getFloatX() < 0) {
            head.setFloatX(B_WIDTH);
        }

        // nếu chết thì dừng game
        if (!alive) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // thread cua timer ( ranchayfull)
        if (alive) {
            checkFood();
            checkCollision();
            snake.move();
        }
        repaint();
//        System.out.println(snake + "<>" + food);
    }
}

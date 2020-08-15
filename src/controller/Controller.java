package controller;

import model.GameModel;
import model.Snake;
import observer.ObserverReset;
import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller extends JPanel implements ObserverReset {
    private GamePanel gamePanel;
    private GameModel model;
    private JPanel content;

    ImageIcon iSnake;
    ImageIcon iGioiThieu;
    ImageIcon iTroGiup;

    TAdapter adapter;

    // MENU
    JButton batDau = new JButton("Bắt đầu");
    JButton gioiThieu = new JButton("Giới thiệu");
    JButton troGiup = new JButton("Trợ giúp");
    JButton quayLai = new JButton("Quay Lại");
    JButton dong = new JButton("Đóng");
    JLabel image = new JLabel();


    public Controller(GamePanel gamePanel,  JPanel content, GameModel model){
        super();
        this.gamePanel = gamePanel;
        this.model = model;
        this.content = content;

        adapter = new TAdapter(model.getSnake());
        gamePanel.addKeyListener(adapter);

        loadImage();
        initUI();
        setEventHandle();
    }

    private void loadImage(){
        iSnake = new ImageIcon(getClass().getResource("/image/snake.jpg"));
        iGioiThieu = new ImageIcon(getClass().getResource("/image/gioithieu.jpg"));
        iTroGiup = new ImageIcon(getClass().getResource("/image/trogiup.jpg"));
    }

    private void initUI() {
        setLayout(new FlowLayout()); // layout

        add(batDau);
        add(gioiThieu);
        add(troGiup);
        add(quayLai);
        add(dong);

        JPanel imagePanel = new JPanel();
        image.setIcon(iSnake);
        imagePanel.add(image);

        CardLayout cardLayout = new CardLayout();
        content.setLayout(cardLayout);

        content.add(imagePanel, "image");
        content.add(gamePanel, "game");



    }

    private  void setEventHandle(){ // dat su kien

        batDau.addKeyListener(adapter);

        CardLayout cardLayout = (CardLayout) content.getLayout();
        batDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(content, "game");
                if (gamePanel.isAlive()) {
                    gamePanel.pauseOrResumeGame();
                    if (gamePanel.isPause())
                        batDau.setText("Tiếp tục");
                    else batDau.setText("Dừng");
                } else {
                    model.notifyObservers();
                    batDau.setText("Bắt đầu");
                }
            }
        });

        gioiThieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.pause();
                cardLayout.show(content, "image");
                image.setIcon(iGioiThieu);
            }
        });

        troGiup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.pause();
                cardLayout.show(content, "image");
                image.setIcon(iTroGiup);
            }
        });

        quayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.pause();
                cardLayout.show(content, "image");
                image.setIcon(iSnake);
            }
        });

        dong.addActionListener(e -> System.exit(0));
    }

    @Override
    public void update() {
        if (!gamePanel.isAlive()) batDau.setText("Bắt đầu");
    }

    private class TAdapter extends KeyAdapter {
        private final Snake snake;

        public TAdapter(Snake snake) {
            this.snake = snake;
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            int direction = snake.getDirection();

            if ((key == KeyEvent.VK_LEFT) && (direction != Snake.DIR_RIGHT)) {
                snake.setDirection(Snake.DIR_LEFT);
            }

            if ((key == KeyEvent.VK_RIGHT) && (direction != Snake.DIR_LEFT)) {
                snake.setDirection(Snake.DIR_RIGHT);
            }

            if ((key == KeyEvent.VK_UP) && (direction != Snake.DIR_DOWN)) {
                snake.setDirection(Snake.DIR_UP);
            }

            if ((key == KeyEvent.VK_DOWN) && (direction != Snake.DIR_UP)) {
                snake.setDirection(Snake.DIR_DOWN);
            }

            if ((key == KeyEvent.VK_ESCAPE)) { // ESC bắt đầu lại
                gamePanel.restartGame();
            }

        }
    }
}



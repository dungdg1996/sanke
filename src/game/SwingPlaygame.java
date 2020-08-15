package game;
import controller.Controller;
import view.GamePanel;
import model.Food;
import model.GameModel;
import model.Snake;

import javax.swing.*;
import java.awt.*;

public class SwingPlaygame extends JFrame {

    private final Controller controller;

    JPanel content;

    public SwingPlaygame() {
        Snake snake = new Snake();
        Food food = new Food();
        GameModel model = new GameModel(snake, food);
        GamePanel gamePanel = new GamePanel(snake, food);
        content = new JPanel(new CardLayout());
        controller = new Controller(gamePanel, content, model);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(controller, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);
    }

    public void creatGUI(){
    	initUI();
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args){
            SwingUtilities.invokeLater(() ->{
                new SwingPlaygame().creatGUI();
            });
    }
}



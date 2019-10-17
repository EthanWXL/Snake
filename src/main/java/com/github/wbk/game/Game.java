package com.github.wbk.game;

import com.github.wbk.controller.Controller;
import com.github.wbk.snake.entities.Food;
import com.github.wbk.snake.entities.Ground;
import com.github.wbk.snake.entities.Snake;
import com.github.wbk.snake.view.GamePanel;
import com.github.wbk.util.Global;
import java.awt.BorderLayout;
import javax.swing.JFrame;


public class Game {

    public static void main(String[] args) {
        Snake snake = new Snake();
        Food food = new Food();
        Ground ground = new Ground();
        GamePanel gamepanel = new GamePanel();
        Controller controller = new Controller(snake, food, ground, gamepanel);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamepanel.setSize(Global.WIDTH * Global.CELL_SIZE,
                Global.HEIGHT * Global.CELL_SIZE);

        frame.setSize(Global.WIDTH * Global.CELL_SIZE + 10,
                Global.HEIGHT * Global.CELL_SIZE + 35);
        frame.add(gamepanel, BorderLayout.CENTER);

        frame.addKeyListener(controller);
        gamepanel.addKeyListener(controller);
        snake.addSnakeListener(controller);

        frame.setVisible(true);

        controller.newGame();
    }

}

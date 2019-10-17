package com.github.wbk.controller;

import com.github.wbk.listener.SnakeListener;
import com.github.wbk.snake.entities.Food;
import com.github.wbk.snake.entities.Ground;
import com.github.wbk.snake.entities.Snake;
import com.github.wbk.snake.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Controller extends KeyAdapter implements SnakeListener {

    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamepanel;

    public Controller(Snake snake, Food food, Ground ground, GamePanel gamepanel) {
        super();
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamepanel = gamepanel;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Snake.RIGHT);
                break;
                default:
        }
    }

    @Override
    public void snakeMoved(Snake snake) {
        // TODO Auto-generated method stub

        if (food.isSnakeEatFood(snake)) {
            snake.eatFood();
            food.newFood(ground.getPoint());
        }

        if (ground.isSnakeEatRock(snake)) {
            snake.die();
        }

        if (snake.isEatBody()) {
            snake.die();
        }
        gamepanel.display(snake, food, ground);
    }


    public void newGame() {
        snake.start();
        food.newFood(ground.getPoint());
    }


}

package com.github.wbk.snake.view;

import com.github.wbk.snake.entities.Food;
import com.github.wbk.snake.entities.Ground;
import com.github.wbk.snake.entities.Snake;
import com.github.wbk.util.Global;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class GamePanel extends JPanel {

    private Snake snake;
    private Food food;
    private Ground ground;

    public void display(Snake snake, Food food, Ground ground) {
        System.out.println("GamePanel's display");
        this.food = food;
        this.ground = ground;
        this.snake = snake;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        //������ʾ
        g.setColor(new Color(0xcfcfcfcf));
        g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);

        if (snake != null && food != null && ground != null) {
            this.food.drawMe(g);
            this.ground.drawMe(g);
            this.snake.drawMe(g);
        }
    }
}

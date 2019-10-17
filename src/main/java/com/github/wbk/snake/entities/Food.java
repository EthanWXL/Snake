package com.github.wbk.snake.entities;

import com.github.wbk.util.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Food extends Point {

    public void newFood(Point p) {
        this.setLocation(p);
    }

    public boolean isSnakeEatFood(Snake snake) {
        System.out.println("Food's isSnakeEatFood");
        return this.equals(snake.getHead());
    }

    public void drawMe(Graphics g) {
        System.out.println("Food's drawMe");
        g.setColor(Color.blue);
        g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
    }
}

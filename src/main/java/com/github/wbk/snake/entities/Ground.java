package com.github.wbk.snake.entities;

import com.github.wbk.util.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Ground {

    private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];

    public Ground() {
        for (int x = 0; x < Global.WIDTH; x++) {
            rocks[x][0] = 1;
            rocks[x][Global.HEIGHT - 1] = 1;
        }
        for (int y = 0; y < Global.HEIGHT; y++) {

            rocks[0][y] = 1;
            rocks[Global.HEIGHT - 1][y] = 1;
        }

    }

    public Point getPoint() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(Global.WIDTH - 1);
            y = random.nextInt(Global.HEIGHT - 1);
        }
        while (rocks[x][y] == 1);
        return new Point(x, y);
    }

    public boolean isSnakeEatRock(Snake snake) {
        System.out.println("Ground's isSnakeEatRock");

        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1 &&
                        x == snake.getHead().x &&
                        y == snake.getHead().y) {
                    return true;
                }
            }
        }

        return false;
    }

    public void drawMe(Graphics g) {
        System.out.println("Ground's drawMe");

        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1) {
                    g.setColor(Color.darkGray);
                    g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
                            Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
    }
}

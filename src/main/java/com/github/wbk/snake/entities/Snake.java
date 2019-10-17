package com.github.wbk.snake.entities;

import com.github.wbk.listener.SnakeListener;
import com.github.wbk.util.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Snake {

    public static final int UP = -1;
    public static final int DOWN = 1;
    public static final int LEFT = -2;
    public static final int RIGHT = 2;

    private int oldDirection, newDirection;
    private Point oldTail;
    private boolean life;
    private int speed;


    private LinkedList<Point> body = new LinkedList<>();

    private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

    public Snake() {
        init();
    }

    public void init() {
        int x = Global.HEIGHT / 2;
        int y = Global.WIDTH / 2;

        for (int i = 0; i < 3; i++) {
            body.addLast(new Point(x--, y));
        }
        speed = 1000;
        oldDirection = newDirection = RIGHT;
        life = true;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public void move() {
        System.out.println("Snake's move");

        if (!(oldDirection + newDirection == 0)) {
            oldDirection = newDirection;
        }


        oldTail = body.removeLast();


        int x = body.getFirst().x;
        int y = body.getFirst().y;
        switch (oldDirection) {
            case UP:
                y--;
                if (y < 0) {
                    y = Global.HEIGHT - 1;
                }
                break;
            case DOWN:
                y++;
                if (y > Global.HEIGHT) {
                    y = 0;
                }
                break;
            case LEFT:
                x--;
                if (x < 0) {
                    x = Global.WIDTH - 1;
                }
                break;
            case RIGHT:
                x++;
                if (x > Global.WIDTH) {
                    x = 0;
                }
                break;
                default:

        }

        Point newHead = new Point(x, y);
        body.addFirst(newHead);


    }


    public void changeDirection(int direction) {
        System.out.println("Snake's changeDirection");
        this.newDirection = direction;
    }

    public void eatFood() {
        System.out.println("Snake's eatFood");

        body.addLast(oldTail);
    }

    public boolean isEatBody() {
        System.out.println("Snake's isEatBody");

        for (int i = 1; i < body.size(); i++) {
            if (this.getHead().equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void drawMe(Graphics g) {
        System.out.println("snake's drawMe");

        g.setColor(Color.blue);
        for (Point p : body) {
            g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE,
                    Global.CELL_SIZE, Global.CELL_SIZE, true);
        }
    }

    public void die() {
        life = false;
    }

    //ʹ���߳���ʵ���������ƶ�������
    private class SnakeDriver implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (life) {
                move();
                for (SnakeListener l : listeners) {
                    l.snakeMoved(Snake.this);
                }

                try {
                    Thread.sleep(speed);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //�����߳�
    public void start() {
        new Thread(new SnakeDriver()).start();
    }

    //�����¼�����
    public void addSnakeListener(SnakeListener l) {
        if (l != null) {
            this.listeners.add(l);
        }
    }
}

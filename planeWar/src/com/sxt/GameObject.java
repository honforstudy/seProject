package com.sxt;

import java.awt.*;

public abstract class GameObject {

    Image img;
    //游戲元素的横座標
    int x;
    //游戲元素的緃座標
    int y;
    //游戲元素的寛
    int width;
    //游戲元素的高
    int height;
    //速度
    double speed;
    //引入主界面
    GameWin frame;

    public GameObject(){

    }

    public GameObject(String img, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.frame = frame;
    }

    public GameObject(String img, int x, int y, double speed, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.frame = frame;
    }

    public GameObject(String img, int x, int y, int width, int height, double speed, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }
    //繼承元素繪制自己的方法
    public abstract void paintSelf(Graphics g);

    //獲取當前游戲元素的矩形,是為碰撞檢測而寫
    public abstract Rectangle getRec();
}

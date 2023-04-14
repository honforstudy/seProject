package com.sxt;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * §Ú¤è¼u°«¾÷—æœº
 */
public class PlaneObj extends GameObject{
    public PlaneObj() {
        super();
    }

    public PlaneObj(String img, GameWin frame) {
        super(img, frame);
    }

    public PlaneObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    public PlaneObj(String img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //²K¥[¹«¼Ð¨Æ,­¸¾÷¸òÀH¹«¼Ð²¾°Ê
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //­¸¾÷®y¼Ð¬°¹«®y¼Ð
                x = e.getX() - 11;
                y = e.getY() - 16;
            }
        });
        //§Ú¤è­¸¾÷»P¼Ä¤èBossªº¸I¼²ÀË´ú
        if (this.frame.bossObj != null){
            if (this.getRec().intersects(this.frame.bossObj.getRec())){
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 3;
            }
        }

        //§Ú¤è­¸¾÷»P¼Ä¤è¤l¼uªº¸I¼²ÀË´ú
        for (BulletObj bulletObj:this.frame.bulletObjList){
            if (this.getRec().intersects(bulletObj.getRec())){
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 3;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,20,30);
    }
}

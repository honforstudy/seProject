package com.sxt;

import java.awt.*;

/**
 * ¼Ä¤èŒæ–¹boss
 */
public class BossObj extends GameObject{

    //boss¥Í©R­È
    int life = 10;

    public BossObj() {
        super();
    }

    public BossObj(String img, GameWin frame) {
        super(img, frame);
    }

    public BossObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //±±¨îboss¹B¤è¦V
        if (x >= 500){
            speed = -5;
        }
        if (x <= 0){
            speed = 5;
        }
        x += speed;
        //boss»P§Ú¤è¤l¼u¶i¦æ¸I¼²ÀË´ú
        for (ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){
                //§Ú¤è¤l¼u¥´¤¤boss
                shellObj.x = -100;
                shellObj.y = 100;
                this.frame.removeList.add(shellObj);
                life--;
            }
            if (life <= 0){
                //boss°}¤`,´åÀ¸³Ó§Qœåˆ©
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 4;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,80,76);
    }
}

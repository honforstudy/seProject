package com.sxt;

import java.awt.*;

/**
 *�Ĥ�l�u
 */
public class BulletObj extends GameObject{
    public BulletObj() {
        super();
    }

    public BulletObj(String img, GameWin frame) {
        super(img, frame);
    }

    public BulletObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        y += speed;
        //�V�ɪ��Ĥ�l�u�R���除
        if (y > 600){
            this.x = -200;
            this.y = 200;
            this.frame.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,15,15);
    }
}
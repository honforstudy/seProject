package com.sxt;

import java.awt.*;

/**
 * 我方炮彈
 */
public class ShellObj extends GameObject {

    public ShellObj() {
        super();
    }

    public ShellObj(String img, GameWin frame) {
        super(img, frame);
    }

    public ShellObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //子彈移動
        y -= speed;
        //我方子彈越界消失,消失位置為-100,100
        if (y <= 0){
            this.x = -100;
            this.y = 100;
            this.frame.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,15,15);
    }
}

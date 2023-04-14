package com.sxt;

import java.awt.*;

/**
 * 背景的實體類
 */
public class BgObj extends GameObject {

	//得分
    int score = 0;

    public BgObj() {
        super();
    }

    public BgObj(String img, GameWin frame) {
        super(img, frame);
    }

    public BgObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //控制背景循環
        if (y >= 0){
            y = -2000;
        }
        //背景移動
        y += speed;

        //計分面皮的編寫
        //改變字體頻色����
        g.setColor(Color.green);
        //修改字體
        g.setFont(new Font("仿宋",Font.BOLD,40));
        //寫到窗口��
        g.drawString(score + " 分",30,100);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}

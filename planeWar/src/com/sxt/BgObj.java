package com.sxt;

import java.awt.*;

/**
 * ≠I¥∫™∫πÍ≈È√˛
 */
public class BgObj extends GameObject {

	//±o§¿
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
        //±±®Ó≠I¥∫¥`¿Ù
        if (y >= 0){
            y = -2000;
        }
        //≠I¥∫≤æ∞ 
        y += speed;

        //≠p§¿≠±•÷™∫Ωsºg
        //ßÔ≈‹¶r≈È¿W¶‚úËâ≤
        g.setColor(Color.green);
        //≠◊ßÔ¶r≈È
        g.setFont(new Font("•Èß∫",Font.BOLD,40));
        //ºg®Ïµ°§fè£
        g.drawString(score + " §¿",30,100);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}

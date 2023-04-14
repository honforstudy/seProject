package com.sxt;

import java.awt.*;

/**
 * �I����������
 */
public class BgObj extends GameObject {

	//�o��
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
        //����I���`��
        if (y >= 0){
            y = -2000;
        }
        //�I������
        y += speed;

        //�p�����֪��s�g
        //���ܦr���W��色
        g.setColor(Color.green);
        //�ק�r��
        g.setFont(new Font("�駺",Font.BOLD,40));
        //�g�쵡�f��
        g.drawString(score + " ��",30,100);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}

package com.sxt;

import java.awt.*;

/**
 *�Ĥ�԰�����
 */
public class EnemyObj extends GameObject{
    //�Ĥ�԰����ͦ���x����
    int x = (int)(Math.random()*12) * 50;
    //�԰������ʳt��
    int speed = 5;
    public EnemyObj() {
        super();
    }

    public EnemyObj(String img, GameWin frame) {
        super(img, frame);
    }

    public EnemyObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        y += speed;
        //�V�ɪ��Ĥ譸���R���除
        if (y > 600){
            //�Ĥ譸�����ܮy�� -150 150
            this.x = -150;
            this.y = 150;
            this.frame.removeList.add(this);
        }
        //�Ĥ譸�P�ڤ譸���I��,��������
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            this.frame.state = 3;
            this.frame.explode_x = this.frame.planeObj.x -11;
            this.frame.explode_y = this.frame.planeObj.y -16;
        }
        //�P�C�@�o�ڤ謶�u�i��I���˴�
        for(ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){

                ExplodeObj explodeObj = new ExplodeObj(this.x,this.y);
                //�K�[�z���ĪG��
                this.frame.explodeObjList.add(explodeObj);
                //�R���z���ĪG�ϛ�
                this.frame.removeList.add(explodeObj);
                //�ڤ�l�u�R���e���ܮy�Ь�-100 100   �Ĥ譸���ܮy��-150 150
                shellObj.x = -100;
                shellObj.y = 100;
                this.x = -150;
                this.y = 150;
                this.frame.removeList.add(shellObj);
                this.frame.removeList.add(this);
                this.frame.bgObj.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,46,35);
    }
}

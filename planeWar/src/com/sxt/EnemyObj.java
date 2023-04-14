package com.sxt;

import java.awt.*;

/**
 *¼Ä¤è¾Ô°«¾÷œº
 */
public class EnemyObj extends GameObject{
    //¼Ä¤è¾Ô°«¾÷¥Í¦¨®Éx§¤¼Ð
    int x = (int)(Math.random()*12) * 50;
    //¾Ô°«¾÷²¾°Ê³t«×
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
        //¶V¬Éªº¼Ä¤è­¸¾÷§R°£ é™¤
        if (y > 600){
            //¼Ä¤è­¸¾÷§ïÅÜ®y¼Ð -150 150
            this.x = -150;
            this.y = 150;
            this.frame.removeList.add(this);
        }
        //¼Ä¤è­¸»P§Ú¤è­¸¾÷¸I¼²,´åÀ¸µ²§ô
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            this.frame.state = 3;
            this.frame.explode_x = this.frame.planeObj.x -11;
            this.frame.explode_y = this.frame.planeObj.y -16;
        }
        //»P¨C¤@µo§Ú¤è¬¶¼u¶i¦æ¸I¼²ÀË´ú
        for(ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){

                ExplodeObj explodeObj = new ExplodeObj(this.x,this.y);
                //²K¥[Ãz¬µ®ÄªG¹Ï
                this.frame.explodeObjList.add(explodeObj);
                //§R°£Ãz¬µ®ÄªG¹Ï›¾
                this.frame.removeList.add(explodeObj);
                //§Ú¤è¤l¼u§R°£«e§ïÅÜ®y¼Ð¬°-100 100   ¼Ä¤è­¸§ïÅÜ®y¼Ð-150 150
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

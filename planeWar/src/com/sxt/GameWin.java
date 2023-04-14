package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    /**定義兩緩存圖片 */
    Image offScreenImage = null;
    //游戲重繪次數
    int count = 1;
    //記錄敵方飛數量
    int enemyCount = 0;
    //游戲狀態 0未開始 1運行中 2暫停 3失敗 4成功
    int state = 0;
    int MY_WIDTH = 800;
    int MY_HEIGHT = 800;
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/space.jpg");
    //爆炸效果圖
    Image explode = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //爆炸效果圖座標
    int explode_x = -300;
    int explode_y = 300;
    //定義大集合
    List<GameObject> objectList = new ArrayList<>();
    //被刪除物體集合
    List<GameObject> removeList = new ArrayList<>();
    //我方子彈集合
    List<ShellObj> shellObjList = new ArrayList<>();
    //敵方子彈集合
    List<BulletObj> bulletObjList = new ArrayList<>();
    //敵方戰鬥機集合
    List<EnemyObj> enemyObjList = new ArrayList<>();
    //爆炸對象集合
    List<ExplodeObj> explodeObjList = new ArrayList<>();
    //背景的實體類
    BgObj bgObj = new BgObj("imgs/space.jpg",0,-2000,2,this);
    //我方戰鬥機
    PlaneObj planeObj = new PlaneObj("imgs/plane1.png",290,550,0,this);
    //敵方boss
    BossObj bossObj = null;
    //窗口的啟動方法
    public void launch(){
        //窗口是否可見
        setVisible(true);
        //窗口大小
        setSize(MY_WIDTH,MY_HEIGHT);
        //窗口的位置
        setLocationRelativeTo(null);
        //窗口的標題
        setTitle("飛機大戰");

        //將游戲物體添加到大集合
        objectList.add(bgObj);
        objectList.add(planeObj);

        //為窗口添加開始鼠標事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠標左鍵代號是1
                if (e.getButton() == 1){
                    state = 1;
                    repaint();
                }
            }
        });
        //為晳停添加一個鍵盤事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //空格鍵被按下
               if (e.getKeyCode() == 32){
                    switch (state){
                        //運行改為暫停
                        case 1:
                            state = 2;
                            break;
                            //暫停改為運行
                        case 2:
                            state = 1;
                            break;
                    }
               }
            }
        });

        while (true){
            if (state == 1){
                createObj();
                repaint();
            }

            try {
                //綫程休眼眠 1秒=1000毫秒
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        /** 創建和容器一樣大小的image圖片 */
        if(offScreenImage ==null){
            offScreenImage=this.createImage(MY_WIDTH, MY_HEIGHT);
        }
        /** 獲得該片的畫布*/   
        Graphics gImage= offScreenImage.getGraphics();
        /** 填充整個畫布*/
        gImage.fillRect(0, 0, MY_WIDTH, MY_HEIGHT);
        //游戲未開始
        if (state == 0){
            gImage.drawImage(bg,0,0,null);
            //改變畫筆的頻色
            gImage.setColor(Color.RED);
            //改變文字大小和樣式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //添加文字
            gImage.drawString("點擊游戲開始",150,300);
        }
        if (state == 1){
            //爆炸對添加到大集合
            objectList.addAll(explodeObjList);

            //繪制所有游戲物體
            for (GameObject object : objectList){
                object.paintSelf(gImage);
            }
            //繪制完後將要刪除的素進行處理
            objectList.removeAll(removeList);
        }
        //失敗變示內容
        if (state == 3){
            //改變畫筆的頻色
            gImage.setColor(Color.red);
            //改變文字大小和樣式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //添加文字
            gImage.drawString("笑死",180,300);
        }
        //通關顯示內容
        if (state == 4){
            //改變畫筆的頻色
            gImage.setColor(Color.green);
            //改變文字大小和樣式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //添加文字
            gImage.drawString("游戲通關",150,300);
        }
        gImage.drawImage(explode,explode_x,explode_y,null);
        /** 將緩沖區繪制好的圖形整個繪制到容器的畫布中*/
        g.drawImage(offScreenImage, 0, 0, null);
        //count自增
        count++;
        //本來係寫"大集合長度"
        System.out.println("total array length" + objectList.size());
    }

    //添加小彈或敵機
    public void createObj(){
        //控制我方子彈生成速度
        if (count % 20 == 0){
            shellObjList.add(new ShellObj("imgs/bulletGreen.png",planeObj.x + 3,planeObj.y - 10,10,this));
            objectList.add(shellObjList.get(shellObjList.size() - 1));
        }
        //敵方子彈生成速度
        if (count % 10 == 0 && bossObj != null){
            bulletObjList.add(new BulletObj("imgs/bulletYellow.png",bossObj.x + 45,bossObj.y + 76,10,this));
            objectList.add(bulletObjList.get(bulletObjList.size() - 1));
        }
        //生成敵方戰鬥機
        if (count % 15 == 0) {
            enemyObjList.add(new EnemyObj("imgs/enemy.png",this));
            objectList.add(enemyObjList.get(enemyObjList.size() - 1));
            enemyCount++;
        }
        if (enemyCount > 100){
            if (bossObj == null){
                bossObj = new BossObj("imgs/boss.png",250,30,5,this);
                objectList.add(bossObj);
            }
        }
    }
    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}

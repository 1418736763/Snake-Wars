package snakeWars.domain;

import snakeWars.Data;
import snakeWars.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class Snake {
    //蛇的长度
    private int length;
    //运动速度
    private int speed;
    //头的方向  R：右  L：左  U：上  D：下
    String direction;

    int [] snakeX = new int[600];//蛇的坐标 X
    int [] snakeY = new int[600];//蛇的坐标 Y

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int[] snakeX) {
        this.snakeX = snakeX;
    }

    public int[] getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int[] snakeY) {
        this.snakeY = snakeY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    //构造函数初始化
    public Snake() {
        this.snakeX[0] = 100;this.snakeY[0] = 100;//头部坐标
        this.snakeX[1] = 75;this.snakeY[1] = 100;//第一个身体坐标
        this.snakeX[2] = 50;this.snakeY[2] = 100;//第二个身体坐标
        this.direction = "R";
    }

    //小蛇出生  初始化位置和长度
    public void birth(){
        length = 3;
        direction = "R";
        speed = 200;
        snakeX[0] = 100;snakeY[0] = 100;//头部坐标
        snakeX[1] = 75;snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;snakeY[2] = 100;//第二个身体坐标
    }

    //显示小蛇
    public void snakeShow(GamePanel gamePanel,Graphics g){

        //改变蛇头部方向 并显示
        if (direction.equals("R")){
            Data.right.paintIcon(gamePanel,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("L")){
            Data.left.paintIcon(gamePanel,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("U")){
            Data.up.paintIcon(gamePanel,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("D")){
            Data.down.paintIcon(gamePanel,g,snakeX[0],snakeY[0]);
        }

        //显示蛇身体
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(gamePanel,g,snakeX[i],snakeY[i]);//蛇的长度通过length来控制
        }
    }

    public void snakeMove(GamePanel gamePanel, Timer timer){
            for (int i = length-1; i > 0 ; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //通过控制方向让头部移动
            if (direction.equals("R")){
                snakeX[0] = snakeX[0] + 25; //头部运动
                // 边界判断
            } else if(direction.equals("L")){
                snakeX[0] = snakeX[0] - 25;
            } else if(direction.equals("U")){
                snakeY[0] = snakeY[0] - 25;
            } else if(direction.equals("D")){
                snakeY[0] = snakeY[0] + 25;
            }
            //刷新界面
            gamePanel.repaint();

            //碰壁死亡
            hitWallDied(gamePanel);

        //timer.start();
    }


    //吃食物后，长度加一
    public void addLength(){
        length++;
        //解决长度加1后，左上方闪烁问题
        snakeX[length-1] = -100;
        snakeY[length-1] = -100;
    }

    //碰壁死亡
    public void hitWallDied(GamePanel gamePanel){
        if (snakeX[0]<25 || snakeX[0]>865 || snakeY[0]<75 || snakeY[0]>665){
            gamePanel.isFail = true;
        }
    }

    //加速
    public void speedUpByGrade(int grade){
        speed = 200 - 20 * (grade - 1);
    }
}

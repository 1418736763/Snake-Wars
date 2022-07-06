package snakeWars.domain;

import snakeWars.Data;
import snakeWars.GamePanel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;
import java.util.Random;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class Food {
    private int foodX;
    private int foodY;
    Random random = new Random();


    public Food() {
        foodX = 25 + 25 * random.nextInt(32);
        foodY = 75 + 25 * random.nextInt(22);
    }

    public int getFoodX() {
        return foodX;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    //生成食物
    public void editFood(){
        foodX = 25 + 25 * random.nextInt(33);
        foodY = 75 + 25 * random.nextInt(22);
    }

    //食物被吃音效
    public void eatFoodMusic(){
        Data.eatAc.play();
    }
}

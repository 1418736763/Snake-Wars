package snakeWars;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
//存放外部数据
public class Data {
    //界面头部的图片  URL：定位图片地址     ImageIcon：图片
    public static URL logoURL = Data.class.getResource("/statics/logo.png");
    public static ImageIcon logo = new ImageIcon(logoURL);

    //头部：
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    //身体：
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    //食物
    public static URL foodURL = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
    //背景 草地
    public static URL bgURL = Data.class.getResource("/statics/草地.jpeg");
    public static ImageIcon bg = new ImageIcon(bgURL);
    //背景 草
    public static URL bg1URL = Data.class.getResource("/statics/草bg.jpeg");
    public static ImageIcon bg1 = new ImageIcon(bg1URL);
    //背景 菜单
    public static URL homePageURL = Data.class.getResource("/statics/homePage.jpeg");
    public static ImageIcon homePage = new ImageIcon(homePageURL);
    //用户
    public static URL userURL = Data.class.getResource("/statics/user.png");
    public static ImageIcon user = new ImageIcon(userURL);

    //音效
    //吃食物音效
    public static URL eatMusicURL = Data.class.getResource("/statics/eat_music.WAV");
    public static AudioClip eatAc = Applet.newAudioClip(eatMusicURL);
    //游戏结束音效
    public static URL overMusicURL = Data.class.getResource("/statics/game_over.WAV");
    public static AudioClip overAc = Applet.newAudioClip(overMusicURL);




}

package snakeWars;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class StartGames{
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        //1、绘制一个静态窗口
        JFrame gameFrame = new JFrame("贪吃蛇大作战");
        JFrame homeFrame = new JFrame("贪吃蛇大作战");
        JFrame loginFrame = new JFrame("登录");
        JFrame registerFrame = new JFrame("注册");
        JFrame ruleFrame = new JFrame("规则详细");
        //设置界面的大小
        gameFrame.setBounds(300,80,918,740);
        homeFrame.setBounds(300,80,918,740);
        loginFrame.setBounds(530,270,500,300);
        registerFrame.setBounds(530,270,500,300);
        ruleFrame.setBounds(300,80,918,740);
        //设置窗口大小不可改变
        gameFrame.setResizable(false);
        homeFrame.setResizable(false);
        loginFrame.setResizable(false);
        registerFrame.setResizable(false);
        ruleFrame.setResizable(false);
        //设置关闭事件
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ruleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //关闭当前页面
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //2、面版 JPanel 可以加入到JFrame
        gameFrame.add(new GamePanel(ac,gameFrame,homeFrame));
        homeFrame.add(new HomePanel(homeFrame,gameFrame,ruleFrame));
        loginFrame.add(new LoginPanel(loginFrame,homeFrame,registerFrame,ac));
        registerFrame.add(new RegisterPanel(registerFrame,ac));
        ruleFrame.add(new RulePanel(homeFrame,ruleFrame));

        //让窗口显示出来
        loginFrame.setVisible(true);
    }
}

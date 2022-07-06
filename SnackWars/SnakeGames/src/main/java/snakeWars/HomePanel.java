package snakeWars;

import snakeWars.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class HomePanel extends JPanel {
    JFrame homeFrame;
    JFrame gameFrame;
    JFrame ruleFrame;

    HomePanel (JFrame homeFrame,JFrame gameFrame,JFrame ruleFrame){
        this.homeFrame = homeFrame;
        this.gameFrame = gameFrame;
        this.ruleFrame = ruleFrame;
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Data.homePage.getImage(),0,0,910,700,this);
        g.drawImage(Data.logo.getImage(),160,90,584,142,this);
    }

    void init(){
        //设置流式布局
        this.setLayout(new FlowLayout());
        //添加组件
        Box vBox = Box.createVerticalBox();

        //开始游戏按钮
        JButton actionBtn = new JButton("开始游戏");
        actionBtn.setFont(new Font("宋体",1,30));
        actionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(false);
                gameFrame.setVisible(true);


            }
        });

        //退出游戏
        JButton logoutBtn = new JButton("退出游戏");
        logoutBtn.setFont(new Font("宋体",1,30));
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //规则详细
        JButton ruleBtn = new JButton("规则详细");
        ruleBtn.setFont(new Font("宋体",1,30));
        ruleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(false);
                ruleFrame.setVisible(true);
            }
        });


        vBox.add(Box.createVerticalStrut(310));
        vBox.add(actionBtn);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(ruleBtn);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(logoutBtn);
        this.add(vBox);
    }
}

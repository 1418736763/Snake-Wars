package snakeWars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class RulePanel extends JPanel {
    Frame homeFrame;
    Frame ruleFrame;
    RulePanel(Frame homeFrame,Frame ruleFrame){
        this.ruleFrame = ruleFrame;
        this.homeFrame = homeFrame;
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Data.homePage.getImage(),0,0,910,700,this);
        g.drawImage(Data.logo.getImage(),160,90,584,142,this);
        g.setFont(new Font("宋体",1,30));
        g.drawString("游戏规则：",160,270);
    }

    void init(){
        //返回主菜单 按钮
        JButton backBtn = new JButton("返回主菜单");
        backBtn.setFont(new Font("宋体",1,20));
        Box hBox = Box.createHorizontalBox();
        Box vBox = Box.createVerticalBox();
        hBox.add(Box.createHorizontalStrut(700));
        hBox.add(backBtn);
        vBox.add(Box.createVerticalStrut(12));
        vBox.add(hBox);
        this.add(vBox);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ruleFrame.setVisible(false);
                homeFrame.setVisible(true);
                //单击后失去焦点  解决返回菜单后再次点击开始游戏焦点在返回菜单按钮上
                backBtn.setFocusable(false);
            }
        });
    }
}

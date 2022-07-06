package snakeWars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import snakeWars.domain.User;
import snakeWars.mapper.UserMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

/**
 * @author liuxiaoyu
 * @version 1.0
 */

public class LoginPanel extends JPanel implements KeyListener {
    ApplicationContext ac;

    public static String userName;
    //保存当前对象
    Object that = this;

    UserMapper userMapper;
    Frame loginFrame;
    Frame homeFrame;
    Frame registerFrame;

    //实现回车登录
    JButton loginBtn;
    JTextField pField;
    JTextField uField;

    LoginPanel(Frame loginFrame, Frame homeFrame, Frame registerFrame, ApplicationContext ac){
        this.ac = ac;
        this.loginFrame = loginFrame;
        this.homeFrame = homeFrame;
        this.registerFrame = registerFrame;
        //初始化
        init();
        //获取键盘的监听事件
        //打开之后 焦点在用户名框
        uField.setFocusable(true);
        //给窗口和用户名框 密码框 添加事件
        this.addKeyListener(this);
        pField.addKeyListener(this);
        uField.addKeyListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Data.bg1.getImage(),0,0,490,300,this);
        g.drawImage(Data.logo.getImage(),140,15,200,50,this);
    }

    public void init(){
        userMapper = (UserMapper) ac.getBean("userMapper");


        //设置流式布局
        this.setLayout(new FlowLayout());
        //添加组件
        Box vBox = Box.createVerticalBox();


        //用户名框
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        uLabel.setFont(new Font("微软雅黑",20,21));
        uField = new JTextField(20);
        //在布局中添加组件，并使用占位符
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(10));
        uBox.add(uField);

        //密码框
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密   码：");
        pLabel.setFont(new Font("微软雅黑",20,21));
        //JTextField pField = new JTextField(20);
        pField = new JPasswordField(20);
        //在布局中添加组件，并使用占位符
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(11));
        pBox.add(pField);

        //登录按钮
        loginBtn = new JButton("登录");
        //loginBtn.setName("");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginAct = uField.getText();
                String loginPwd = pField.getText();
                //表单验证
                if ("".equals(loginAct) || loginAct == null && "".equals(loginPwd) || loginPwd == null){
                    JOptionPane.showMessageDialog((Component) that,"请输入用户名和密码");
                    return;
                }else{
                    if ("".equals(loginAct) || loginAct == null){
                        JOptionPane.showMessageDialog((Component) that,"请输入用户名");
                        return;
                    }
                    if ("".equals(loginPwd) || loginPwd == null){
                        JOptionPane.showMessageDialog((Component) that,"请输入密码");
                        return;
                    }
                }
                //查询数据
                User user = userMapper.selectUserByLoginAct(loginAct);


                if (user!=null){
                    if (user.getLoginPwd().equals(loginPwd)){
                        JOptionPane.showMessageDialog((Component) that,"登陆成功");
                        //静态变量 传递数据到游戏界面
                        userName = user.getLoginAct();
                        loginFrame.setVisible(false);
                        homeFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog((Component) that,"用户名或密码错误，请重新登陆陆");
                    }
                }else {
                    JOptionPane.showMessageDialog((Component) that,"用户名或密码错误，请重新登陆陆");
                }

            }
        });
        //注册按钮
        JButton registerBtn = new JButton("注册");
        registerBtn.setName("registerBtn");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.setVisible(true);
            }
        });

        Box btnBox = Box.createHorizontalBox();
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(80));
        btnBox.add(registerBtn);

        vBox.add(Box.createVerticalStrut(80));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(btnBox);
        this.add(vBox);

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCoke = e.getKeyCode();
        //实现回车登录
        if (keyCoke==KeyEvent.VK_ENTER){
            loginBtn.doClick();
        }

    }




    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}

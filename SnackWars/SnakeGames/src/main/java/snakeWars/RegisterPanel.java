package snakeWars;

import javafx.scene.input.KeyCode;
import org.apache.poi.ss.formula.functions.Even;
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

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class RegisterPanel extends JPanel implements KeyListener {
    ApplicationContext ac;

    UserMapper userMapper;
    Frame registerFrame;

    //实现回车注册
    JButton registerBtn;

    JTextField uField;
    JTextField pField;

    RegisterPanel(Frame registerFrame,ApplicationContext ac){
        this.ac = ac;
        this.registerFrame = registerFrame;
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

        //保存当前对象
        Object that = this;

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
        pField = new JPasswordField(20);
        //在布局中添加组件，并使用占位符
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(11));
        pBox.add(pField);

        //注册按钮
        registerBtn = new JButton("注册");
        registerBtn.setName("registerBtn");
        registerBtn.addActionListener(new ActionListener() {
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

                //注册表单验证
                String regExp = "^[A-Za-z0-9]+$";
                if (!loginAct.matches(regExp)){
                    JOptionPane.showMessageDialog((Component) that,"用户名只能由字母和数字组成");
                    return;
                }
                if (!loginPwd.matches(regExp)){
                    JOptionPane.showMessageDialog((Component) that,"密码只能由字母和数字组成");
                    return;
                }

                User user = new User();
                user.setLoginAct(loginAct);
                user.setLoginPwd(loginPwd);

                //添加数据
                try {
                    int ret = userMapper.insertUser(user);
                    if (ret > 0){
                        JOptionPane.showMessageDialog((Component) that,"注册成功");
                        registerFrame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog((Component) that,"系统忙，请稍后重试...");
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog((Component) that,"系统忙，请稍后重试...");
                }

            }
        });


        Box btnBox = Box.createHorizontalBox();
        btnBox.add(registerBtn);

        vBox.add(Box.createVerticalStrut(80));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(btnBox);
        this.add(vBox);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER){
            registerBtn.doClick();
        }
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}

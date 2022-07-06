package snakeWars;

import org.springframework.context.ApplicationContext;
import snakeWars.domain.Food;
import snakeWars.domain.Snake;
import snakeWars.mapper.UserMapper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * @author liuxiaoyu
 * @version 1.0
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    Frame gameFrame;
    Frame homeFrame;
    UserMapper userMapper;
    //创建小蛇对象
    Snake snake;
    //创建食物对象
    Food food = new Food();

    //分数
    int score;
    //关卡数
    int grade;

    //游戏是否开始
    public boolean isStart = false;
    //游戏是否失败
    public boolean isFail = false;

    //定时器
    Timer timer;

    //构造器
    public GamePanel(ApplicationContext ac,Frame gameFrame,Frame homeFrame){
        this.gameFrame = gameFrame;
        this.homeFrame = homeFrame;
        userMapper = (UserMapper) ac.getBean("userMapper");
        //initLogin();
        //初始化小蛇对象
        snake = new Snake();
        //初始化定时器
        timer = new Timer(200,this);
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);

    }

    //初始化游戏界面
    public void init(){
        //初始化小蛇位置和长度
        snake.birth();
        //初始化分数
        score = 0;
        //初始化关卡数
        grade = 1;
        //启动定时器
        timer.start();
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
                isStart = false;
                gameFrame.setVisible(false);
                homeFrame.setVisible(true);
                //单击后失去焦点  解决返回菜单后再次点击开始游戏焦点在返回菜单按钮上
                backBtn.setFocusable(false);
            }
        });
    }

    /**
     * 画板：画界面，画蛇
     * @param g 画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        //设置背景的颜色
        //this.setBackground(Color.white);
        //设置背景图片
        g.drawImage(Data.homePage.getImage(),0,0,910,700,this);

        //绘制头部的logo
        g.drawImage(Data.logo.getImage(),300,0,300,70,this);
        //绘制游戏的区域
        g.setColor(new Color(23, 210, 234));
        g.fillRect(25,75,850,600);
        g.setColor(new Color(7, 7, 7));

        g.drawImage(Data.bg.getImage(),25,75,850,600,this);

        g.drawLine(25,74,876,74);
        g.drawLine(25,675,876,675);
        g.drawLine(25,74,25,675);
        g.drawLine(876,74,876,675);
        //展示界面
        show(g);
        //生成食物
        Data.food.paintIcon(this,g,food.getFoodX(),food.getFoodY());
        //显示用户
        showUser(g);
        //显示分数
        showScore(g);
        //结束界面
        over(g);
    }



    //展示界面
    public void show(Graphics g){

        //游戏提示：是否开始
        if(isStart == false){
            //画一个文字，string
            g.setColor(new Color(18, 193, 246)); //设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40)); //设置字体
            g.drawString("按下空格开始游戏",300,350);
        }
        //画食物
        Data.food.paintIcon(this,g,food.getFoodX(),food.getFoodY());
        //失败提醒
//        if(isFail == true){
//            //画一个文字，string
//            g.setColor(Color.red); //设置画笔的颜色
//            g.setFont(new Font("微软雅黑",Font.BOLD,40)); //设置字体
//            g.drawString("游戏失败，按下空格重新开始",200,350);
//        }
        //显示小蛇
        snake.snakeShow(this,g);
    }

    //监听器，接收键盘的输入
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键是哪个键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){  //如果按下的是空格键，启动 或者 暂停
            if (isFail){
                init();
                isFail = false;
                timer.start();
            } else {
                isStart = !isStart;
            }
            repaint(); //刷新界面 执行 paintComponent() 方法
        }
        //ESC键退出游戏
        if (keyCode == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        //键盘控制方向
        if (!"R".equals(snake.getDirection()) && keyCode==KeyEvent.VK_LEFT || keyCode==KeyEvent.VK_A){
            snake.setDirection("L");
        }else if (!"L".equals(snake.getDirection()) && keyCode==KeyEvent.VK_RIGHT || keyCode==KeyEvent.VK_D){
            snake.setDirection("R");
        }else if (!"D".equals(snake.getDirection()) && keyCode==KeyEvent.VK_UP || keyCode==KeyEvent.VK_W){
            snake.setDirection("U");
        }else if (!"U".equals(snake.getDirection()) && keyCode==KeyEvent.VK_DOWN || keyCode==KeyEvent.VK_S){
            snake.setDirection("D");
        }
    }

    //定时器，监听时间  执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态,并且游戏没有结束
        if (isStart && isFail==false){
            //身体右移
            snake.snakeMove(this,timer);

            //吃食物判断  如果小蛇的头和坐标重合，吃掉 重新生成
            if (snake.getSnakeX()[0]==food.getFoodX() && snake.getSnakeY()[0]==food.getFoodY()){
                //音效
                food.eatFoodMusic();
                //长度+1
                snake.addLength();
                //分数+10
                score += 10;
                //关卡数 70分为一关
                grade = score / 100 + 1;
                //随关卡数加速
                snake.speedUpByGrade(grade);
                timer.setDelay(snake.getSpeed());
                //重新生成食物坐标
                food.editFood();
                //解决食物与身体重叠
//                for (int i = 0; i < snake.getLength(); i++) {
//                    if (food.getFoodX()==snake.getSnakeX()[i] && food.getFoodY()==snake.getSnakeY()[i]){
//                        while (food.getFoodX()!=snake.getSnakeX()[i] && food.getFoodY()!=snake.getSnakeY()[i]){
//                            food.editFood();
//                        }
//                    }
//                }

            }

            //结束判断
            for (int i = 1; i < snake.getLength(); i++) {
                if (snake.getSnakeX()[0]==snake.getSnakeX()[i] && snake.getSnakeY()[0]==snake.getSnakeY()[i]){
                    isFail = true;
                }
            }
        }
    }

    //显示用户名
    public void showUser(Graphics g){
        g.drawImage(Data.user.getImage(),25,10,50,55,this);
        g.setColor(Color.pink);
        g.setFont(new Font("宋体",Font.BOLD,30));
        g.drawString(":" + LoginPanel.userName,70,45);
    }
    //显示分数
    public void showScore(Graphics g){
        g.setColor(Color.pink);
        g.setFont(new Font("宋体",Font.BOLD,18));
        g.drawString("长度：" + snake.getLength(),750,104);
        g.drawString("分数：" + score,750,125);
        g.drawString("关卡：" + grade,750,147);
    }

    //结束界面
    public void over(Graphics g){
        if (isFail){
            g.setColor(Color.black);
            g.fillRect(0,0,918,740);
            g.setColor(Color.red);
            g.setFont(new Font("宋体",Font.BOLD,60));
            g.drawString("GAME OVER",300,300);
            g.setColor(Color.white);
            g.setFont(new Font("宋体",Font.BOLD,40));
            g.drawString("长度：" + snake.getLength(),370,350);
            g.drawString("分数：" + score,370,395);
            g.drawString("关卡：" + grade,370,440);
            g.setFont(new Font("宋体",Font.BOLD,30));
            g.drawString("空格键重新开始",340,550);
            Data.overAc.play();
            timer.stop();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }


}

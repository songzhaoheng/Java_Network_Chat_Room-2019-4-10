package com.net;

import javax.swing.*;

public class AlterClient extends JFrame {

    public JTextField userName;//用户名
    public JPasswordField passWord;//密码
    public JPasswordField passWord2;//确认密码

    public AlterClient(){
        JPanel jPanel = new JPanel();//主面板
        jPanel.setLayout(null);//设置布局方式（null关闭自动布局）

        //用户名
        JLabel userLabel = new JLabel("用户名：");//生成一个标签
        userLabel.setBounds(70, 60, 80, 20);//设置位置 大小
        jPanel.add(userLabel);//面板加入标签
        userName = new JTextField();
        userName.setBounds(135, 60, 200, 20);
        jPanel.add(userName);

        //密码
        JLabel passLabel = new JLabel("原密码：");//生成一个标签
        passLabel.setBounds(70, 100, 80, 20);
        jPanel.add(passLabel);//面板加入标签
        passWord = new JPasswordField();
        passWord.setBounds(135, 100, 200, 20);
        jPanel.add(passWord);

        //确认密码
        JLabel pass2Label = new JLabel("新密码：");//生成一个标签
        pass2Label.setBounds(70, 140, 80, 20);
        jPanel.add(pass2Label);//面板加入标签
        passWord2 = new JPasswordField();
        passWord2.setBounds(135, 140, 200, 20);
        jPanel.add(passWord2);


        //按钮
        JButton submit = new JButton("修改密码");//按钮
        submit.setBounds(150, 190, 100, 30);
        jPanel.add(submit);
        submit.addActionListener(new AlterAction(this));//当点击的时候执行loginAction


        this.setTitle("Hern聊天室修改密码");
        this.setResizable(false);//不能改变窗口大小
        this.add(jPanel);//窗口加入面板
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 300);
        this.setLocationRelativeTo(null);//放在屏幕中间
    }
    public static void main(String[] args) {
        new AlterClient();

    }
}

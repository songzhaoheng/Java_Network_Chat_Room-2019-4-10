package com.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginClient extends JFrame{//登录界面

	public JTextField userName;
	public JPasswordField passWord;
	public LoginClient(){

		JPanel jPanel = new JPanel();//主面板
		jPanel.setLayout(null);//设置布局方式（null关闭自动布局）

		JLabel userLabel = new JLabel("用户名：");//生成一个标签
		userLabel.setBounds(70, 60, 80, 20);//设置位置 大小
		jPanel.add(userLabel);//面板加入标签
		userName = new JTextField();
		userName.setBounds(135, 60, 200, 20);
		jPanel.add(userName);

		JLabel passLabel = new JLabel("密    码：");//生成一个标签
		passLabel.setBounds(70, 100, 80, 20);
		jPanel.add(passLabel);//面板加入标签
		passWord = new JPasswordField();
		passWord.setBounds(135, 100, 200, 20);
		jPanel.add(passWord);

		JButton submit = new JButton("登录");//按钮
		submit.setBounds(120, 150, 90, 30);
		jPanel.add(submit);
		submit.addActionListener(new LoginAction(this));//当点击的时候执行loginAction

		JButton regist = new JButton("注册");//按钮
		regist.setBounds(220, 150, 90, 30);
		jPanel.add(regist);
		regist.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginClient.this.dispose();;
				new RegistClient();
			}

		});//当点击的时候执行loginAction

		JButton alter = new JButton("修改密码");//按钮
		alter.setBounds(180, 200, 90, 30);
		jPanel.add(alter);
		alter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginClient.this.dispose();
				new AlterClient();
			}

		});//当点击的时候执行loginAction

		this.setTitle("Hern聊天室登录");
		this.setResizable(false);//不能改变窗口大小
		this.add(jPanel);//窗口加入面板
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);//放在屏幕中间

	}

	public static void main(String[] args) {
		new LoginClient();

	}


}

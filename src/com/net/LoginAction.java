package com.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mapper.LoginInterface;
import com.mapper.SqlSessionFactoryGet;
import com.userInfo.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;

public class LoginAction implements ActionListener{

	public LoginClient loginClient;

	public boolean isLogin = false;//标志是否登录成功
	Socket socket = null;

	public LoginAction(LoginClient loginClient) {
		this.loginClient = loginClient;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String userName=loginClient.userName.getText();
		String userPass=loginClient.passWord.getText();

		//判断用户名和密码是否为空
		if (userName.equals("")){
			JOptionPane.showMessageDialog(loginClient, "用户不能为空！");
		}else if (userPass.equals("")){
			JOptionPane.showMessageDialog(loginClient, "密码不能为空！");
		}

		//获取SqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = null;

		//获取SQLSession对象
		SqlSession session = null;

		try {

			sqlSessionFactory = new SqlSessionFactoryGet().getSqlSessionFactory();

			session = sqlSessionFactory.openSession();



		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try{
			//获取接口实现类对象
			LoginInterface loginInterface = session.getMapper(LoginInterface.class);

			UserInfo userInfo = loginInterface.getUserInfoByUsername(userName);

			if (userInfo == null){
				JOptionPane.showMessageDialog(loginClient, "用户名或密码错误！");
			}


			System.out.println(userInfo);

			//证明该用户存在
			if (userInfo.getUserid() != null || userInfo.getUserid() != 0){

				//判断密码是否一致
				if (userInfo.getUserpass().equals(userPass)){

					//登录成功
					isLogin = true;

				}
			}

			//登录成功
			if(isLogin== true){
				try {
					socket = new Socket("127.0.0.1",8888);//向服务器发送请求    172.16.31.143
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(loginClient, "连接服务器错误");
				}
				loginClient.dispose();
				new Chatclient(loginClient.userName.getText(),socket);

			}else{// 失败
				JOptionPane.showMessageDialog(loginClient, "用户名或密码错误！");
			}
		}finally {
			//关闭数据库连接
			session.close();
		}


	}

}

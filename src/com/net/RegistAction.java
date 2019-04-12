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

import com.mapper.RegisteInterface;
import com.mapper.SqlSessionFactoryGet;
import com.share.UserInfoDecide;
import com.userInfo.UserInfo;
import com.userInfo.UserName;
import com.userInfo.UserPassName;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class RegistAction implements ActionListener{

	public RegistClient registClient;

	Socket socket = null;
	public RegistAction(RegistClient registClient) {
		this.registClient = registClient;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isRegist = false;//标志是否登录成功
		String userName=registClient.userName.getText();
		String userPass=registClient.passWord.getText();
		String userPass2=registClient.passWord2.getText();

		//判断用户名、密码是否为空
		if (userName.equals("") || userPass.equals("") || userPass2.equals("")){
			JOptionPane.showMessageDialog(registClient, "用户名或密码不能为空！");
			isRegist = true;
		}else if(!userPass.equals(userPass2)){
			JOptionPane.showMessageDialog(registClient, "两次密码输入不一致！");
			isRegist = true;
		}else {
			SqlSessionFactory sqlSessionFactory = null;

			SqlSession session = null;

			try {
				sqlSessionFactory = new SqlSessionFactoryGet().getSqlSessionFactory() ;
				session = sqlSessionFactory.openSession();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try{

				RegisteInterface registeInterface = session.getMapper(RegisteInterface.class);

				System.out.println("------------------------------------");
				UserInfo userInfo = registeInterface.getUserName(userName);
				System.out.println("------------------------------------");
				if(userInfo == null || userInfo.getUserid() == null ||userInfo.getUserid() == 0){

					UserInfo userInfo2 = new UserInfo(null,userName,userPass);
					boolean flag = registeInterface.insertUserInfo(userInfo2);
					//判断是否插入成功
					if(flag){
						session.commit();
						JOptionPane.showMessageDialog(registClient, "注册成功！");
						registClient.dispose();
						new LoginClient();
					}

				}else if (userName.equals(userInfo.getUsername())){
					//用户名已存在
					isRegist = true;
					JOptionPane.showMessageDialog(registClient, "用户名已存在！");
				}



			}finally {
				session.close();
			}
		}

	}

}

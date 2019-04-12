package com.net;

import com.mapper.AlterInterface;
import com.mapper.RegisteInterface;
import com.mapper.SqlSessionFactoryGet;
import com.userInfo.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class AlterAction implements ActionListener {

    public AlterClient alterClient;

    Socket socket = null;
    public AlterAction(AlterClient alterClient) {
        this.alterClient = alterClient;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isRegist = false;//标志是否登录成功
        String userName=alterClient.userName.getText();
        String userPass=alterClient.passWord.getText();
        String userPass2=alterClient.passWord2.getText();

        //判断用户名、密码是否为空
        if (userName.equals("") || userPass.equals("") || userPass2.equals("")){

            JOptionPane.showMessageDialog(alterClient, "用户名或密码不能为空！");
            isRegist = true;

        }else if(userPass.equals(userPass2)){
            JOptionPane.showMessageDialog(alterClient, "原密码和新密码一致！");
            isRegist = true;
        }else {
            SqlSessionFactory sqlSessionFactory = null;

            SqlSession session = null;

            try {
                sqlSessionFactory = new SqlSessionFactoryGet().getSqlSessionFactory();
                session = sqlSessionFactory.openSession();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try{

                AlterInterface alterInterface = session.getMapper(AlterInterface.class);

                System.out.println("------------------------------------");
                UserInfo userInfo = alterInterface.selectByName(userName);
                System.out.println("------------------------------------");

                if (userInfo == null || userInfo.getUsername() == null){
                    JOptionPane.showMessageDialog(alterClient, "该用户不存在！");
                }else {
                    //判断输入的用户和密码是否一致
                    if (userInfo.getUserpass().equals(userPass)){

                        boolean flag = alterInterface.updateByPass(userName,userPass2);
                        //判断是否更新成功
                        if(flag){
                            session.commit();
                            JOptionPane.showMessageDialog(alterClient, "修改成功！");
                            alterClient.dispose();
                            new LoginClient();
                        }

                    }else {
                        JOptionPane.showMessageDialog(alterClient, "您输入的原密码不正确");
                    }
                }

            }finally {
                session.close();
            }
        }

    }

}

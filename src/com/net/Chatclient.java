package com.net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chatclient extends JFrame{

	public JTextArea chatArea;//群聊消息区域
	public JTextArea sendArea;//发送区域
	String username;
	Socket socket;
	public Chatclient(String username,Socket socket) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.socket = socket;
		//聊天区域设置
		chatArea = new JTextArea();
		chatArea.setLineWrap(true);//设置换行方式(true 自动换行)
		JScrollPane chatScrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//给聊天区域添加滚动条
		chatArea.setEditable(false);//设置聊天区域不可修改

		//编辑区域设置
		sendArea = new JTextArea();
		sendArea.setLineWrap(true);//设置换行方式(true 自动换行)
		JScrollPane sendScrollPane = new JScrollPane(sendArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//给编辑区域添加滚动条
		sendArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					send();
				}
			}
		});

		//添加分割线
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,chatScrollPane,sendScrollPane);
		splitPane.setDividerLocation(250);
		this.add(splitPane,BorderLayout.CENTER);//居中放置

		//添加按钮
		JPanel bPane = new JPanel();//创建按钮面板
		JButton button = new JButton("发送");
		button.addActionListener(new ActionListener() {//发送监听

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = sendArea.getText();
				if(str.equals("")){
					JOptionPane.showMessageDialog(Chatclient.this, "发送内容不能为空！");
				}else{
					send();
				}
			}
		});

		JLabel userLabel = new JLabel("用户名："+username);//生成一个标签
		bPane.add(userLabel);//面板加入标签
		bPane.add(button);
		this.add(bPane,BorderLayout.SOUTH);
		this.setSize(300, 450);
		this.setTitle("Hern聊天室");
		this.setResizable(false);//不能改变窗口大小
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//放在屏幕中间
		//启动线程，从服务器读取数据，显示在聊天框中
		ClientThread clientThread = new ClientThread(socket, chatArea);
		clientThread.start();
	}

	public void send(){
		String str = sendArea.getText();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String time=format.format(new Date());
		String message = time+"\n"+username+":"+str;
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println(message);
			pw.flush();//刷新缓冲区
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sendArea.setText("");

	}
}

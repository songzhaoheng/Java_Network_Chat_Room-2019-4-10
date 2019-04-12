package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//服务器
public class ChatServer {

	List<Socket> sockets = new ArrayList<>();
	public ChatServer() {
		// TODO Auto-generated constructor stub
		try {
			ServerSocket serverSocket = new ServerSocket(8888);//创建一个服务器端，（8888）服务器端口号
			System.out.println("已在监听8888端口");
			while(true){
				Socket  socket = serverSocket.accept();//接收
				sockets.add(socket);
				String ip = socket.getInetAddress().getHostAddress();//获取客户端ip地址
				System.out.println("新用户进入!ip是"+ip);
				Thread thread = new Thread(new ServerRunner(sockets,socket));
				thread.start();



			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ChatServer();
	}
}

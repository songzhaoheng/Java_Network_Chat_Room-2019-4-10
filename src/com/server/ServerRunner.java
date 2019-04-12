package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

//服务器的线程类
public class ServerRunner implements Runnable{

	List<Socket> socketList=null;//代表所有连接
	Socket currentSocket;//代表当前连接
	public ServerRunner(List<Socket> socketList,Socket currentSocket) {
		this.socketList = socketList;
		this.currentSocket = currentSocket;
	}
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));//字符读取（输入流进行读取）
			String message = null;
			while((message = br.readLine())!=null){//readLine读取输入流的一行
				System.out.println(message);
				//向客户端写入信息
				for(Socket s:socketList){
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));//获取字节输出流
					pw.println(message);//往输出流写入message
					pw.flush();//刷新字符缓冲区
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

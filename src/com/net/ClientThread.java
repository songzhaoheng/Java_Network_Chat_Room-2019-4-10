package com.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ClientThread extends Thread{

	public Socket socket;
	public JTextArea chatArea;

	public ClientThread(Socket socket,JTextArea chatArea) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.chatArea =chatArea;
	}

	public void run(){
		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//字符读取（输入流进行读取）
			String message = null;
			while((message = br.readLine())!=null){//readLine读取输入流的一行
				chatArea.append(message+"\n");
				chatArea.setSelectionStart(chatArea.getText().length());//让滚动条跟着信息走

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


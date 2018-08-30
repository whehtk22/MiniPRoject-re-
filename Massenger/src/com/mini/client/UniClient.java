package com.mini.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class UniClient {
	private int port = 50000;
	private String ip = "192.168.0.3";
	private Socket socket;
	private DataInputStream datain;
	private DataOutputStream dataout;
	private int chatPort;

	public int getChatPort() {
		return chatPort;
	}
	void setConnection() throws IOException {
		InetAddress inet = InetAddress.getByName(ip);
		socket = new Socket(inet,port);
	}
	void receivePort() throws IOException {
		datain = new DataInputStream(socket.getInputStream());
		System.out.println(datain.readInt());
		this.chatPort =datain.readInt();
	}
	void sendRoomName() throws IOException {
		dataout = new DataOutputStream(socket.getOutputStream());
		String chatName = JOptionPane.showInputDialog("채팅방 이름을 입력하세요.");
		byte[] name = new byte[1024];
		name = chatName.getBytes();
		dataout.write(name);
	}
	void closeConnection() throws IOException {
		socket.close();
	}
	public void receiveRoomPort() {
		try {
		setConnection();
		receivePort();
		sendRoomName();
		closeConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//	public static void main(String[] args) throws Exception{
	//		UniClient uni = new UniClient();
	//		uni.setConnection();
	//		uni.receivePort();
	//		uni.sendRoomName();
	//		uni.closeConnection();
	//	}


}

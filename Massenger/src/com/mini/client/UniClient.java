package com.mini.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class UniClient {
	private static final int port = 50000;
	private static final String ip = "192.168.0.3";
	private Socket socket;
	private DataInputStream datain;
	private DataOutputStream dataout;
	void setConnection() throws IOException {
		InetAddress inet = InetAddress.getByName(ip);
		socket = new Socket(inet,port);
	}
	void receivePort() throws IOException {
		datain = new DataInputStream(socket.getInputStream());
		System.out.println(datain.readInt());
		
	}
	void sendRoomName() throws IOException {
		dataout = new DataOutputStream(socket.getOutputStream());
		String chatName = JOptionPane.showInputDialog("ä�ù� �̸��� �Է��ϼ���.");
		byte[] name = new byte[1024];
		name = chatName.getBytes();
		dataout.write(name);
	}
	void closeConnection() throws IOException {
		socket.close();
	}
	public static void main(String[] args) throws Exception{
		UniClient uni = new UniClient();
		uni.setConnection();
		uni.receivePort();
		uni.sendRoomName();
		uni.closeConnection();
	}
	

}

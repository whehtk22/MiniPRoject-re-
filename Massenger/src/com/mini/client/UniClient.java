package com.mini.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class UniClient {
	private static final int port = 50000;
	private static final String ip = "192.168.0.3";
	private Socket socket;
	private DataInputStream datain;
	void setConnection() throws IOException {
		InetAddress inet = InetAddress.getByName(ip);
		socket = new Socket(inet,port);
	}
	void receivePort() throws IOException {
		datain = new DataInputStream(socket.getInputStream());
		System.out.println(datain.readInt());
		
	}
	void closeConnection() throws IOException {
		socket.close();
	}
	public static void main(String[] args) throws Exception{
		UniClient uni = new UniClient();
		uni.setConnection();
		uni.receivePort();
		uni.closeConnection();
	}
	

}

package com.mini.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UniServer {
	private static final int port = 50000;
	private static final String ip = "192.168.0.3";
	private ServerSocket server;
	private Socket socket;
	private DataOutputStream dataout;
	void setConnection() throws IOException {
		server = new ServerSocket(port);
		socket = server.accept();
		System.out.println("사용자 접속 완료");
	}
	
	void sendPort() throws IOException {
		int chatPort = ((int)(Math.random()*30000)+20000);
		System.out.println(chatPort);
		dataout = new DataOutputStream(socket.getOutputStream());
		dataout.writeInt(chatPort);
	}
	void closeConnection() throws IOException {
		socket.close();
	}
	public static void main(String[] args) throws IOException {
		UniServer server = new UniServer();
		server.setConnection();
		server.sendPort();
		server.closeConnection();
	}
}

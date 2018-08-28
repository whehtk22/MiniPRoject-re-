package com.mini.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_Main {
	private int port;
	private String ip;
	private InetAddress inet;
	private static Socket socket;
	
	public static Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Client_Main(int port, String ip) {
		this.port = port;
		this.ip = ip;
		try {
			this.inet = InetAddress.getByName(ip);
			socket = new Socket(inet, port);
		} catch (IOException e) {
			System.err.println("서버접속하는데 문제가 발생");
			e.printStackTrace();
		}
	}
	
	
}
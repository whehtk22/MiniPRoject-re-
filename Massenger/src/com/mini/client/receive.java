package com.mini.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;

public class receive extends Thread{
	private Socket socket;
	private DataInputStream ds;
	private byte[]data = new byte[1024];
	public receive(Socket socket) {
		this.socket = socket;
		try {
			ds = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	@Override
//	public void run() {
//		super.run();
//		try {
////			int result =ds.read(data, arg1, arg2)();
////			if(result!=0) {
//				
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
}

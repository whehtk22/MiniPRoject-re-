package com.mini.Test999;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerTest2 {

	public static void main(String[] args) throws IOException {
		System.out.println("����");
		int port = 50000;
		ServerSocket server = new ServerSocket(port);
		Socket socket = socket = server.accept();
		System.out.println("����� ���� �Ϸ�");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("���ڿ� �ޱ� �Ϸ�");
		String line;
		Data_DB db = new Data_DB();
		while(true) {
		line = br.readLine();
		if(line==null)break;
		db.textDb(line);
		}
		System.out.println(line);
		socket.close();
		server.close();
		
		Frame frame = new Frame();
			System.out.println("���� ���� �Ϸ�.");

	}

}

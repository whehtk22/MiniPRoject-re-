package com.mini.Test999;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class ServerTest {

	public static void main(String[] args) throws Exception{
		System.out.println("시작");
		int port = 50000;
		ServerSocket server = new ServerSocket(port);
		Socket socket = socket = server.accept();
		System.out.println("사용자 접속 완료");
		
//		ImageInputStream in = ImageIO.createImageInputStream(socket.getInputStream());
		System.out.println(socket.getChannel());
		System.out.println(socket.getInputStream().available());
		BufferedImage bf = ImageIO.read(socket.getInputStream());
		System.out.println("이미지 받기 완료");
		System.out.println(bf.getType());
		System.out.println(bf.getHeight());
		System.out.println(bf.getWidth());
		Data_DB db = new Data_DB();
		System.out.println("db받기");
		db.imageDb(bf);
		socket.close();
		server.close();
		Frame frame = new Frame();
		frame.icon(bf);
		frame.display();
			System.out.println("서버 종료 완료.");
	}
}



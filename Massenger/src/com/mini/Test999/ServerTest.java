package com.mini.Test999;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class ServerTest {

	public static void main(String[] args) throws Exception{
		System.out.println("����");
		int port = 50000;
		ServerSocket server = new ServerSocket(port);
		Socket socket = socket = server.accept();
		System.out.println("����� ���� �Ϸ�");
		
//		ImageInputStream in = ImageIO.createImageInputStream(socket.getInputStream());
		System.out.println(socket.getChannel());
		System.out.println(socket.getInputStream().available());
		BufferedImage bf = ImageIO.read(socket.getInputStream());
		System.out.println("�̹��� �ޱ� �Ϸ�");
		System.out.println(bf.getType());
		System.out.println(bf.getHeight());
		System.out.println(bf.getWidth());
		Data_DB db = new Data_DB();
		System.out.println("db�ޱ�");
		db.imageDb(bf);
		socket.close();
		server.close();
		Frame frame = new Frame();
		frame.icon(bf);
		frame.display();
			System.out.println("���� ���� �Ϸ�.");
	}
}



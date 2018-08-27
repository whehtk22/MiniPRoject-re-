package com.mini.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import com.itbank.test.Frame;

public class Server {
	
	private ServerSocket server;
	private int port = 50000;
	private Socket socket;
	
	void set() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("서버 생성 준비 실패");
		}
	}
	public void accept() {
		try {
			socket = server.accept();
			System.out.println("사용자 접속 완료");
		} catch (IOException e) {
			System.out.println("사용자 접속 안함.");
		}
		
	}
	public void receiveByte() throws IOException {
		byte[] buffer = new byte[1024];//크기는 나중에 변경 가능
		int size = socket.getInputStream().read();//받은 데이터의 크기
		String text = new String(buffer,0,size);//받은 데이터를 스트링으로 변환하는 과정
		//데이터를 저장할 곳 미정
	}
	public void receiveStr() {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(true) {
			String line = in.readLine();
			//데이터를 저장할 곳 미정
		}
	}
	public void receiveObj() {
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());//예외처리 아직 안함
		List<String>list = (List<String>)in.readObject();//아직 어떤 오브젝트인지는 정해야 함
		
	}
	public void receiveImg() throws IOException {
		BufferedImage bf;
		try {
			bf = ImageIO.read(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("이미지 받기 완료");
		System.out.println(bf.getType());
		System.out.println(bf.getHeight());
		System.out.println(bf.getWidth());
		socket.close();
		server.close();
		
		//Frame frame = new Frame();	같은 패키지 안에 있는 프레임을 생성
		//frame.icon(bf);
		//frame.display();
			System.out.println("서버 종료 완료.");
		
	}
	public void exit() throws IOException {
		socket.close();
		server.close();
		System.out.println("서버 종료 완료.");
	}
}

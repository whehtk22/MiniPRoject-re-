package com.mini.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiServer {
	private String ip = "230.230.230.230";// 채팅 멀티캐스트 아이피
	private int port;
	private String text;// 채팅 창에서의 내용

	public MultiServer(int port) {// 채팅방을 만들기 위해서 포트번호로 생성자를 만든다.(서버측)
		this.port = port;
	}

	// ms.leaveGroup(inet);
	void receive() throws Exception {
		InetAddress inet = InetAddress.getByName(ip);
		// MulticastSocket 생성
		MulticastSocket ms = new MulticastSocket(this.port);
		// 채널에 참가
		ms.joinGroup(inet);
		DatagramPacket dp;
		while (true) {
			byte[] data = new byte[1024];
			DatagramSocket dt = new DatagramSocket();
			dt.setBroadcast(true);
			dp = new DatagramPacket(data, data.length);
			ms.receive(dp);
			text = new String(dp.getData(), 0, dp.getLength());
			System.out.println(text);
		}
	}

	/**
	 * 
	 * @return 유저가 입력한 채팅글 내용을 입력하는 것.
	 */
	public String getText() {
		return text;
	}
/**
 * 
 * @param text 유저가 입력한 채팅글
 */
	public void setText(String text) {
		this.text = text;
	}
/**
 * 
 * @param msg 유저들에게 전송하는 메소드
 */
	void broadcast(String msg) {
		try {

			InetAddress group = InetAddress.getByName(ip);
			MulticastSocket multicast = new MulticastSocket();
			byte[] byt = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(byt, byt.length, group, this.port);
			multicast.send(packet);
		} catch (Exception e) {

		}
	}

}
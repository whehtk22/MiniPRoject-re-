package com.mini.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiServer {
	private String ip = "230.230.230.230";// ä�� ��Ƽĳ��Ʈ ������
	private int port;
	private String text;// ä�� â������ ����

	public MultiServer(int port) {// ä�ù��� ����� ���ؼ� ��Ʈ��ȣ�� �����ڸ� �����.(������)
		this.port = port;
	}

	// ms.leaveGroup(inet);
	void receive() throws Exception {
		InetAddress inet = InetAddress.getByName(ip);
		// MulticastSocket ����
		MulticastSocket ms = new MulticastSocket(this.port);
		// ä�ο� ����
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
	 * @return ������ �Է��� ä�ñ� ������ �Է��ϴ� ��.
	 */
	public String getText() {
		return text;
	}
/**
 * 
 * @param text ������ �Է��� ä�ñ�
 */
	public void setText(String text) {
		this.text = text;
	}
/**
 * 
 * @param msg �����鿡�� �����ϴ� �޼ҵ�
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
package com.mini.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiServer {
	private String ip = "230.230.230.230";//ä�� ��Ƽĳ��Ʈ ������
	private int port;
	private String text;//ä�� â������ ����
	private int number;
	// ����
	// ä�� Ż��
	// ms.leaveGroup(inet);
	void receive() throws Exception {
		int port = 50000;
		InetAddress inet = InetAddress.getByName(ip);
		// MulticastSocket ����
		MulticastSocket ms = new MulticastSocket(port);
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

	void broadcast(String msg) {
		try {

			InetAddress group = InetAddress.getByName(ip);
			MulticastSocket multicast = new MulticastSocket();
			System.out.println(msg);
			byte[] byt = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(byt, byt.length, group, 50000);
			multicast.send(packet);
			if (msg.equals("����"))
				multicast.close();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) throws IOException {
		MultiServer multi = new MultiServer();
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					multi.receive();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		Thread r = new Thread() {
			@Override
			public void run() {
				multi.broadcast(multi.text);
			}
		};
		r.start();
	}
}

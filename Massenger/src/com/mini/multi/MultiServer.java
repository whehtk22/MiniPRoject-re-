package com.mini.multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiServer {
	public static void main(String[] args) throws IOException {
		String ip = "192.168.0.3";
		int port = 50000;
		InetAddress inet = InetAddress.getByName(ip);
		//MulticastSocket ����
		MulticastSocket ms = new MulticastSocket(port);
		
		//ä�ο� ����
		ms.joinGroup(inet);
		//����
		byte[] data = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(data,data.length);
		ms.receive(dp);
		String text = new String(dp.getData(),0,dp.getLength());
		System.out.println("text = "+text);
		
		//ä�� Ż��
		ms.leaveGroup(inet);
	}
}

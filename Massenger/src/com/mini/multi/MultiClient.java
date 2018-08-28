package com.mini.multi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClient {
	public static void main(String[] args) throws Exception{
		String ip = "192.168.0.3";//받을 컴퓨터의 주소를 입력
		int port = 50000;
		
		InetAddress inet = InetAddress.getByName(ip);
		MulticastSocket ms = new MulticastSocket(port);
		
		ms.joinGroup(inet);
		
		String text = "임의로 전송할 값";
		byte[] data = text.getBytes();
		DatagramPacket dp = 
				new DatagramPacket(data,data.length,inet,port);
		//보낼 쪽은 네개의 항목을 입력해 줘야 함
		ms.send(dp);//데이터그램을 보냄
//		ms.leaveGroup(inet);
//		ms.close();
	}
}

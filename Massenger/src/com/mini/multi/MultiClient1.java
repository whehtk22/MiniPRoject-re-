package com.mini.multi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClient1 {
	public static void main(String[] args) throws Exception{
		String ip = "230.230.230.230";//받을 컴퓨터의 주소를 입력
		int port = 46000;
		
		InetAddress inet = InetAddress.getByName(ip);
		MulticastSocket ms = new MulticastSocket(port);
		
		ms.joinGroup(inet);
		
		String text = "강성진";
		byte[] data = text.getBytes();
		DatagramPacket dp = 
				new DatagramPacket(data,data.length,inet,port);
		//보낼 쪽은 네개의 항목을 입력해 줘야 함
		ms.send(dp);//데이터그램을 보냄
//		ms.leaveGroup(inet);
//		ms.close();
	}
}

package com.mini.multi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClient1 {
	public static void main(String[] args) throws Exception{
		String ip = "230.230.230.230";//���� ��ǻ���� �ּҸ� �Է�
		int port = 46000;
		
		InetAddress inet = InetAddress.getByName(ip);
		MulticastSocket ms = new MulticastSocket(port);
		
		ms.joinGroup(inet);
		
		String text = "������";
		byte[] data = text.getBytes();
		DatagramPacket dp = 
				new DatagramPacket(data,data.length,inet,port);
		//���� ���� �װ��� �׸��� �Է��� ��� ��
		ms.send(dp);//�����ͱ׷��� ����
//		ms.leaveGroup(inet);
//		ms.close();
	}
}

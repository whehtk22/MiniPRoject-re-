package com.mini.Test999;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
	public static void main(String[] args) {
		MultiSend ms = new MultiSend();
		ms.setDaemon(true);
		ms.start();
		
		try {
			InetAddress group = InetAddress.getByName("230.230.230.230");
			MulticastSocket multicast = new MulticastSocket(50000);
			multicast.joinGroup(group);
			
//			StringBuffer sb = new StringBuffer();
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			
			while(true) {
				multicast.receive(packet);
				System.out.println(new String(packet.getData(),0,packet.getLength()));
//				sb.append(new String(buffer,0,buffer.length));
//				System.out.println(sb);
				if(new String(buffer).equals("Á¾·á")) {
					multicast.close();
				}
			}

		}catch(Exception e) {

		}
	}
}

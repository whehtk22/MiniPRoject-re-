package com.mini.Test999;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JOptionPane;

public class MultiSend extends Thread{
	private boolean flage= true;
	@Override
	public void run() {
		super.run();
		while(flage) {
			try{
				String str =JOptionPane.showInputDialog("문자입력");
				InetAddress group = InetAddress.getByName("230.230.230.230");
				MulticastSocket multicast = new MulticastSocket(50000);
				multicast.joinGroup(group);
				byte[] buffer =str.getBytes();
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length,group,50000);
				multicast.send(packet);
				System.out.println(packet.hashCode());
//				System.out.println(packet.);
				if(new String(buffer).equals("종료")) {
					multicast.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}

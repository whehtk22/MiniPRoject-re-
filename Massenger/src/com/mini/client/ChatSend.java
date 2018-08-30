package com.mini.client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JOptionPane;

public class ChatSend {
	private Client_cennection cm;
	
	public ChatSend(Client_cennection cm) {
		this.cm=cm;
	}
	public void sendMessage(String str) {
		try{
			
			byte[] buffer =str.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, cm.getJg().group, cm.getJg().charPort);
			cm.getJg().multicast.send(packet);
			System.out.println(packet.hashCode());
			//				System.out.println(packet.);
			if(new String(buffer).equals("Á¾·á")) {
				cm.getJg().multicast.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

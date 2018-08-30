package com.mini.client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import com.mini.client.*;

public class ChatReceiver extends Thread{
	private Client_cennection cm;
	private String mmm;
	public String getMmm() {
		return mmm;
	}
	public ChatReceiver(Client_cennection cm) {
		this.cm=cm;
	}
	@Override
	public void run() {
		super.run();
		try {
			
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			while(true) {
					cm.getJg().multicast.receive(packet);
//					if(packet.getData()!=null) {
						mmm = new String(packet.getData(),0,packet.getLength());
						System.out.println(mmm);
						//showMessage(mmm);
//					}
					if(new String(buffer).equals("Á¾·á")) {
						cm.getJg().multicast.close();
					}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//	public String showMessage(String str) {
	//		
	//	}

}

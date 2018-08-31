package com.mini.client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import com.mini.client.*;

public class ChatReceiver extends Thread{
	private Client_cennection cm;
	private String receiveMessage="";
	public String getReceiveMessage() {
		return receiveMessage;
	}
	public ChatReceiver(Client_cennection cm) {
		this.cm=cm;
	}
	@Override
	public void run() {
		try {

			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			
			while(true) {
				cm.getJg().multicast.receive(packet);
				//					if(packet.getData()!=null) {
				String testCode = new String(packet.getData(),0,packet.getLength());

				//애초에 들어온 값이 없다면 그냥 ""를 보낸다. 그리고 받는 쪽에서 ""이면 textarea에 추가를 하지않는다.
				if(!testCode.equals("")&&testCode!=null) {
					receiveMessage = testCode;
					System.out.println("스레드에서 받은값:"+receiveMessage);
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

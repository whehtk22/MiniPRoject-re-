package com.mini.client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

import com.mini.client.*;

public class ChatReceiver_re extends Thread{
	private Client_cennection cm;
	private String receiveMessage="";
	private byte[] buffer = new byte[1024];
	private JTextArea chat;
	private DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

	public ChatReceiver_re(Client_cennection cm,JTextArea chat) {
		this.cm=cm;
		this.chat=chat;
	}

	public String getReceiveMessage() {
		return receiveMessage;
	}

	@Override
	public void run() {
		while(true) {
			try {
				cm.getJg().multicast.receive(packet);
				String testCode = new String(packet.getData(),0,packet.getLength());
				
				if(!testCode.equals("")&&testCode!=null) {
					receiveMessage = testCode;
					System.out.println("스레드에서 받은값:"+receiveMessage);
				}
				//textArea를 전달해주고 여기서 그걸 때려박는다.
				chat.append(receiveMessage+"\n");

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//	public void 
}
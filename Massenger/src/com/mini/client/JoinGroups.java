package com.mini.client;

import java.net.InetAddress;
import java.net.MulticastSocket;

public class JoinGroups {
	public InetAddress group;
	public  MulticastSocket multicast;
	public int charPort;
	public JoinGroups(int chatPort) {
		try {
			this.group = InetAddress.getByName("230.230.230.230");
			this.multicast = new MulticastSocket(chatPort);
			this.charPort=chatPort;
		}catch(Exception e) {
			e.printStackTrace();
		}

	}


}

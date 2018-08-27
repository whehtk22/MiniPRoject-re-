package chating.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_Main {
	private int port;
	private String ip;
	private InetAddress inet;
	private Socket socket;
	
	
	public Client_Main(int port, String ip) {
		this.port = port;
		this.ip = ip;
		try {
			this.inet = InetAddress.getByName(ip);
			socket = new Socket(inet, port);
		} catch (IOException e) {
			System.err.println("IP정보를 가져오지못함");
			e.printStackTrace();
		}
	}
	
	
}

package com.mini.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class send extends Thread{
	private Socket socket;
	
	private ImageOutputStream stream;
//	private DataOutputStream ds = new 

	public send(Socket socket, File file) {
		this.socket = socket;
		try {
			this.stream = new FileImageOutputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

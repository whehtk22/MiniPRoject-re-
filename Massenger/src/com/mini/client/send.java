package com.mini.client;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class send extends Thread{
	private int port=50000;
	private String ip="192.168.0.3";
	private InetAddress inet;
	private Socket socket;
	private boolean flag= true;

	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public send() {
		try {
			this.inet = InetAddress.getByName(this.ip);
			this.socket = new Socket(inet, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Override
//	public void run() {
//		try {
//			while(flag) {
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}


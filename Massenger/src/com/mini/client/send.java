package com.mini.client;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class send extends Thread{
	private Socket socket;
	private ImageOutputStream stream;
//	private DataOutputStream ds = new 

	public send(Socket socket) {
		this.socket = socket;
		try {
			OutputStream outStream=socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public void sendImg(JFileChooser fc, int imgFile) {
		
		if(imgFile==0) {
			File f = fc.getSelectedFile();
			try {
				String filename = f.getName();
				String extension = filename.substring(filename.lastIndexOf(".")+1);

				BufferedImage bi  = ImageIO.read(f);
				OutputStream outStream=socket.getOutputStream();
				ImageIO.write(bi, extension, outStream);
				outStream.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public JFileChooser showFilePage() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setFileFilter(new FileNameExtensionFilter("PNG파일","png"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG파일","jpg"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF파일","gif"));
		return fc;
	}
	
	
}

package com.mini.client;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Data_class {
	private Socket socket;
	private ImageOutputStream stream;
	
	public Data_class(Socket socket) {
		this.socket = socket;
	}
	public JFileChooser chooseFile() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setFileFilter(new FileNameExtensionFilter("PNG이미지","png"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG이미지","jpg"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF이미지","gif"));
		return fc;
	}
	public boolean sendIngFile(File file) {
		String filename = file.getName();
		String extension = filename.substring(filename.lastIndexOf(".")+1);

		BufferedImage bi;
		boolean ox =false;
		try {
			bi = ImageIO.read(file);
			OutputStream outStream=socket.getOutputStream();
			ox =ImageIO.write(bi, extension, outStream);
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ox;
	}
	public void sendMessage(String message) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
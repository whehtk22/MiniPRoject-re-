package com.mini.client;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mini.client.*;
import com.mini.gui.Dial;
import com.mini.gui.Gui3;

public class Data_class2 {
	private Socket socket = Client_Main.getSocket();
	private ImageOutputStream stream;
	
	public void sendImg() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setFileFilter(new FileNameExtensionFilter("PNG이미지","png"));
		fc.setFileFilter(new FileNameExtensionFilter("JPEG이미지","jpg"));
		fc.setFileFilter(new FileNameExtensionFilter("GIF이미지","gif"));
		int imgFile = fc.showOpenDialog(fc);
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
	public void sendStr(String str) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())));
			out.println(str);
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
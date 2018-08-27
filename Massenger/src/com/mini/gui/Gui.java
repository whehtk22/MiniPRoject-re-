package com.mini.gui;

import java.awt.*;
import com.mini.client.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mini.client.send;

class Gui extends JFrame{
	private int port=50000;
	private String ip="192.168.0.3";
	private InetAddress inet;
	private Socket socket;


	private JPanel con = new JPanel();
	private JTextArea text = new JTextArea(); 
	private JLabel imgLabel = new JLabel();
	private JButton summit = new JButton("보내기");
	private JButton file = new JButton("파일");
	private JLabel status = new JLabel("상태표시",JLabel.CENTER);
	private JPanel testPanel = new JPanel();

	public void display() {
		this.setContentPane(con);
		con.setLayout(new BorderLayout());
		con.add(status,BorderLayout.NORTH);
		//	con.add(imgLabel, BorderLayout.CENTER);
		con.add(text, BorderLayout.CENTER);
		con.add(testPanel, BorderLayout.SOUTH);
		testPanel.add(summit);
		testPanel.add(file);
		//		con.add(summit, BorderLayout.SOUTH);

	}

	public void event() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		/*
		 * 파일 보내기 현재는 이미지만 가능하다.
		 */
		file.addActionListener(e->{
			JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
			fc.setFileFilter(new FileNameExtensionFilter("PNG이미지","png"));
			fc.setFileFilter(new FileNameExtensionFilter("JPEG이미지","jpg"));
			fc.setFileFilter(new FileNameExtensionFilter("GIF이미지","gif"));
			int imgFile = fc.showOpenDialog(con);
			if(imgFile==0) {
				File f = fc.getSelectedFile();
				try {
					socket = new Socket(InetAddress.getByName(this.ip),port);
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

		});
		/*
		 * 
		 */
		summit.addActionListener(e->{
			try {
				socket = new Socket(InetAddress.getByName(this.ip),port);
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
				out.println(text.getText());
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	public void menu() {

	}
	public Gui() {
		this.display();
		this.event();
		this.menu();

		this.setTitle("messenger");
		this.setLocationByPlatform(true);
		this.setSize(400, 400);
		this.setResizable(false);
		this.setVisible(true);
	}

}
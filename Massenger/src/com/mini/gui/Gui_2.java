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

class Gui_2 extends JFrame{
	private JPanel con = new JPanel();
	private JTextArea text = new JTextArea(); 
	private JLabel imgLabel = new JLabel();
	private JButton summit = new JButton("보내기");
	private JButton file = new JButton("파일");
	private JLabel status = new JLabel("상태표시",JLabel.CENTER);
	private JPanel testPanel = new JPanel();
	private send s;
	public void display() {
		this.setContentPane(con);
		con.setLayout(new BorderLayout());
		con.add(status,BorderLayout.NORTH);
		con.add(text, BorderLayout.CENTER);
		con.add(testPanel, BorderLayout.SOUTH);
		testPanel.add(summit);
		testPanel.add(file);

	}

	public void event() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		WindowListener w = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					s.getSocket().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		};
		
		file.addActionListener(e->{
			s = new send();
			Data_Class dc = new Data_Class(s.getSocket());
			int imgFile = dc.chooseFile().showOpenDialog(con);
			if(imgFile==0) {
				File f = dc.chooseFile().getSelectedFile();
				boolean ox =dc.sendIngFile(f);
				System.out.println(ox);
			}

		});
		
		summit.addActionListener(e->{
			s = new send();
			Data_Class dc = new Data_Class(s.getSocket());
			dc.sendMessage(text.getText());
		});
		
	}
	public void menu() {

	}
	public Gui_2() {
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
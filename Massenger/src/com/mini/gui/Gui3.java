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

import com.mini.client.*;

public class Gui3 extends JFrame{
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
			Data_class2 dc = new Data_class2();
			dc.sendImg();
		});
		/*
		 * 문자열 전송
		 */
		summit.addActionListener(e->{
			Data_class2 dc = new Data_class2();
			dc.sendStr(text.getText());
		});
		
	}
	public void menu() {

	}
	public Gui3() {
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